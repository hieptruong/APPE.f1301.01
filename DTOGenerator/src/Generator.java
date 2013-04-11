import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Generator {

	private final String PREFIX = "DTO";
	private final String ENTITY_PACKAGE = "ch.hslu.appe.fs1301.data.shared.entity.";
	private String currentPath;
	
	public Generator(){
		currentPath = getClass().getClassLoader().getResource(".").getPath();
	}
	
	public void generate(String[] entities, String srcPath, String destPath) {
		try {
			BufferedWriter dtoWriter = createClassFile(currentPath + destPath + "DTOConverter.java");
			dtoWriter.write("package ch.hslu.appe.fs1301.business.shared.dto;\n\n");
			writeConvertorImports(dtoWriter, entities);
			writeClassDescription(dtoWriter);
			dtoWriter.write("public class DTOConverter {\n");				
			
			for (String entity : entities) {
						
				List<Field> fields = retrieveClassFields(srcPath, entity);
							
				try {				
					generateDTO(entities, destPath, entity, fields);
					generateNewConverterMethod(dtoWriter, entity);
				} catch (IOException e) {			
					System.out.println(e.getMessage());
				}
			}
			
			dtoWriter.write("}");
			dtoWriter.flush();
			dtoWriter.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void writeConvertorImports(BufferedWriter writer, String[] entities) throws IOException {
		writer.write("import java.util.List;\n");
		writer.write("import java.util.ArrayList;\n");
		for(String entity : entities) {
			writer.write("import " + ENTITY_PACKAGE + entity + ";\n");
		}
		writer.write("\n");
	}

	private void generateNewConverterMethod(BufferedWriter writer, String entity) throws IOException {
		String entityListName = entity.toLowerCase() + "List";
		String dtoListName = PREFIX.toLowerCase() + entity + "List";
		String dtoClass = PREFIX + entity;
		
		writer.write("\n");
		writer.write("\tpublic static List<" + dtoClass + "> convert" + entity + "(List<" + entity + "> " + entityListName + ") {\n");
		writer.write("\t\tList<" + dtoClass + "> " + dtoListName + " = new ArrayList<" + dtoClass + ">();\n");
		writer.write("\t\tfor(" + entity + " " + entity.toLowerCase() + " : " + entityListName + ")\n");
		writer.write("\t\t\t" + dtoListName + ".add(new " + dtoClass + "(" + entity.toLowerCase() + "));\n");
		writer.write("\t\treturn " + dtoListName + ";\n");
		writer.write("\t}\n");
	}
	
	private void generateDTO(String[] entities, String destPath, String entity,	List<Field> fields) throws IOException {
		BufferedWriter writer = createClassFile(destPath, entity);
		
		writer.write("package ch.hslu.appe.fs1301.business.shared.dto;\n");
		writer.write("\n");				
		
		writeImports(fields, writer, entities, entity);								
		
		writeClassDescription(writer);
		
		writer.write("public class " + PREFIX + entity + " {\n");
		
		writeField(entities, fields, writer);

		writeConstructor(entity, writer, fields, entities);
		
		for (Field field : fields) {
			writeGetter(entities, writer, field);	
			
			writeSetter(entities, writer, field);	
		}
		
		writer.write("}");
		
		writer.flush();
		writer.close();
	}

	private void writeSetter(String[] entities, BufferedWriter writer, Field field)
			throws IOException {
		if (field.getGenericType() != null) {
			boolean usePrefix = checkPrefixNeeded(entities, field.getGenericType());
			
			writer.write(String.format("\tpublic void set%s(%s<%s> %s) {\n", field.getNameWithCapitalFirstLetter(), field.getType(), usePrefix ? "Integer" : field.getGenericType(), field.getName()));
			writer.write(String.format("\t\t%s = %s;\n", field.getAttributeName(), field.getName()));
			writer.write("\t}\n\n");
		} else {
			writer.write(String.format("\tpublic void set%s(%s %s) {\n", field.getNameWithCapitalFirstLetter(), checkPrefixNeeded(entities, field.getType()) ? "int" : field.getType(), field.getName()));
			writer.write(String.format("\t\t%s = %s;\n", field.getAttributeName(), field.getName()));
			writer.write("\t}\n\n");
		}
	}

	private void writeGetter(String[] entities, BufferedWriter writer, Field field)
			throws IOException {
		if (field.getGenericType() != null) {
			boolean usePrefix = checkPrefixNeeded(entities, field.getGenericType());
			
			writer.write(String.format("\tpublic %s<%s> get%s() {\n", checkPrefixNeeded(entities, field.getType()) ? PREFIX + field.getType() : field.getType(), usePrefix ? "Integer" : field.getGenericType(), field.getNameWithCapitalFirstLetter()));
			writer.write(String.format("\t\treturn %s;\n", field.getAttributeName()));
			writer.write("\t}\n\n");
		} else {
			writer.write(String.format("\tpublic %s get%s() {\n", checkPrefixNeeded(entities, field.getType()) ? "int" : field.getType(), field.getNameWithCapitalFirstLetter()));
			writer.write(String.format("\t\treturn %s;\n", field.getAttributeName()));
			writer.write("\t}\n\n");
		}
	}

	private void writeConstructor(String entity, BufferedWriter writer, List<Field> fields, String[] entities)
			throws IOException {
		writer.write("\tpublic " + PREFIX + entity + "() {\n");
		for (Field field : fields) {
			if (field.getType().equals("List")) {
				writer.write(String.format("\t\t%s = new ArrayList<%s>();\n", field.getAttributeName(), checkPrefixNeeded(entities, field.getGenericType()) ? "Integer" : field.getGenericType()));
			}
		}
		writer.write("\t}\n\n");
		
		writer.write("\tpublic " + PREFIX + entity + "(" + entity + " " + entity.toLowerCase() + ") {\n");
		writer.write("\t\tthis();\n");
		for (Field field : fields) {
			if (field.getType().equals("List") && checkPrefixNeeded(entities, field.getGenericType())) {
				writer.write(String.format("\t\tfor (%s %s : %s.get%s()) {\n", field.getGenericType(), field.getGenericType().toLowerCase(), entity.toLowerCase(), field.getNameWithCapitalFirstLetter()));
				writer.write(String.format("\t\t\t%s.add(%s.getId());\n", field.getAttributeName(), field.getGenericType().toLowerCase()));
				writer.write("\t\t}\n");
			} else if (checkPrefixNeeded(entities, field.getType())) {
				writer.write(String.format("\t\t%s = %s.get%s().getId();\n", field.getAttributeName(), entity.toLowerCase(), field.getNameWithCapitalFirstLetter()));
			} else {
				writer.write(String.format("\t\t%s = %s.get%s();\n", field.getAttributeName(), entity.toLowerCase(), field.getNameWithCapitalFirstLetter()));
			}
		}
		writer.write("\t}\n\n");
	}

	private void writeField(String[] entities, List<Field> fields,
			BufferedWriter writer) throws IOException {
		for (Field field : fields) {
			if (field.getGenericType() != null) {
				boolean usePrefix = checkPrefixNeeded(entities, field.getGenericType());
				
				writer.write(String.format("\t%s %s<%s> %s;\n", field.getModifier(), field.getType(), usePrefix ? "Integer" : field.getGenericType(), field.getAttributeName()));
			} else {
				writer.write(String.format("\t%s %s %s;\n", field.getModifier(), checkPrefixNeeded(entities, field.getType()) ? "int" : field.getType(), field.getAttributeName()));
			}					
		}
		
		writer.write("\n");
	}

	private boolean checkPrefixNeeded(String[] entities, String value) {
		boolean usePrefix = false;
		for (String clazz : entities) {
			if (clazz.equals(value)) {
				usePrefix = true;
			}
		}
		return usePrefix;
	}

	private void writeClassDescription(BufferedWriter writer) throws IOException {
		writer.write("/**\n");
		writer.write("* Auto-Generated DTOs\n");
		writer.write("* " + new Date().toString() + "\n");
		writer.write("*/\n");
	}

	private void writeImports(List<Field> fields, BufferedWriter writer, String[] entities, String creatingEntity)
			throws IOException {
		for (Field field : fields) {
			if (field.getType().equals("Date")) {
				writer.write("import java.util.Date;\n");
				break;
			}
		}
		
		for (Field field : fields) {
			if (field.getType().equals("List")) {
				writer.write("import java.util.List;\n");
				writer.write("import java.util.ArrayList;\n");
				break;
			}
		}
		
		List<String> addedImports = new ArrayList<String>();
		writer.write("import " + ENTITY_PACKAGE + creatingEntity + ";\n");
		addedImports.add(creatingEntity);
		
		for (Field field : fields) {
			for (String entity : entities) {
				if (addedImports.contains(entity)) continue;
				
				if (field.getGenericType() != null) {
					if (field.getGenericType().equals(entity)) {
						writer.write("import " + ENTITY_PACKAGE + entity + ";\n");
						addedImports.add(entity);
					}
				}				
			}
		}
		
		writer.write("\n");
	}

	private BufferedWriter createClassFile(String destPath) throws IOException {
		File file = new File(destPath);
		if (file.exists()) file.delete();
		file.getParentFile().mkdirs();
		file.createNewFile();
				
		return new BufferedWriter(new FileWriter(file));
	}
	private BufferedWriter createClassFile(String destPath, String entity) throws IOException {
		return createClassFile(currentPath + destPath + PREFIX + entity + ".java");
	}

	private List<Field> retrieveClassFields(String srcPath, String entity) {
		File srcFile = new File(currentPath + srcPath + entity + ".java");
		List<Field> classFields = new ArrayList<Field>();
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(srcFile);
		
			// Remove all line until the class starts
			while (scanner.hasNextLine()) {					
				String line = scanner.nextLine();
				if (line.contains("public class"))
					break;
			}	
			
			while (scanner.hasNextLine()) {					
				String line = scanner.nextLine();
				
				//We arrived at the constuctor
				if (line.contains("public " + entity + "()")) 
					break;
				
				if (!line.trim().isEmpty()) {
					String[] components = line.split(" ");
					if (components.length == 3) {
						Field field = new Field(components[0].trim(), components[1], components[2].substring(0, components[2].length() - 1));
						if (components[1].contains("<")) {
							String[] typeDeclarations = components[1].split("<");
							field.setType(typeDeclarations[0]);
							field.setGenericType(typeDeclarations[1].substring(0, typeDeclarations[1].length() - 1));
						}
						
						classFields.add(field);
					}
				}
			}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (scanner != null)
				scanner.close();
		}
		
		return classFields;
	}
}
