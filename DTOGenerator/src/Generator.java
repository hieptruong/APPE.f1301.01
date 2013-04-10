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
	private final String ENTITY_PACKAGE = "ch.hslu.appe.fs1301.data.shared.";
	
	public void generate(String[] entities, String srcPath, String destPath) {
		
		for (String entity : entities) {
					
			List<Field> fields = retrieveClassFields(srcPath, entity);
						
			try {				
				BufferedWriter writer = createClassFile(destPath, entity);
				
				writer.write("package ch.hslu.appe.fs1301.business.dto;\n");
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
				
			} catch (IOException e) {			
				System.out.println(e.getMessage());
			}
		}			
	}

	private void writeSetter(String[] entities, BufferedWriter writer, Field field)
			throws IOException {
		if (field.getGenericType() != null) {
			boolean usePrefix = checkPrefixNeeded(entities, field.getGenericType());
			
			writer.write(String.format("\tpublic void set%s(%s<%s> %s) {\n", field.getNameWithCapitalFirstLetter(), field.getType(), usePrefix ? PREFIX + field.getGenericType() : field.getGenericType(), field.getName()));
			writer.write(String.format("\t\t%s = %s;\n", field.getAttributeName(), field.getName()));
			writer.write("\t}\n\n");
		} else {
			writer.write(String.format("\tpublic void set%s(%s %s) {\n", field.getNameWithCapitalFirstLetter(), checkPrefixNeeded(entities, field.getType()) ? PREFIX + field.getType() : field.getType(), field.getName()));
			writer.write(String.format("\t\t%s = %s;\n", field.getAttributeName(), field.getName()));
			writer.write("\t}\n\n");
		}
	}

	private void writeGetter(String[] entities, BufferedWriter writer, Field field)
			throws IOException {
		if (field.getGenericType() != null) {
			boolean usePrefix = checkPrefixNeeded(entities, field.getGenericType());
			
			writer.write(String.format("\tpublic %s<%s> get%s() {\n", checkPrefixNeeded(entities, field.getType()) ? PREFIX + field.getType() : field.getType(), usePrefix ? PREFIX + field.getGenericType() : field.getGenericType(), field.getNameWithCapitalFirstLetter()));
			writer.write(String.format("\t\treturn %s;\n", field.getAttributeName()));
			writer.write("\t}\n\n");
		} else {
			writer.write(String.format("\tpublic %s get%s() {\n", checkPrefixNeeded(entities, field.getType()) ? PREFIX + field.getType() : field.getType(), field.getNameWithCapitalFirstLetter()));
			writer.write(String.format("\t\treturn %s;\n", field.getAttributeName()));
			writer.write("\t}\n\n");
		}
	}

	private void writeConstructor(String entity, BufferedWriter writer, List<Field> fields, String[] entities)
			throws IOException {
		writer.write("\tpublic " + PREFIX + entity + "() {\n\t\t\n\t}\n\n");
		
		writer.write("\tpublic " + PREFIX + entity + "(" + entity + " " + entity.toLowerCase() + ") {\n");
		for (Field field : fields) {
			if (field.getType().equals("List")) {
				
			} else if (checkPrefixNeeded(entities, field.getType())) {
				writer.write(String.format("\t\t%s = new %s(%s.get%s());\n", field.getAttributeName(), PREFIX + field.getType(), entity.toLowerCase(), field.getNameWithCapitalFirstLetter()));
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
				
				writer.write(String.format("\t%s %s<%s> %s;\n", field.getModifier(), field.getType(), usePrefix ? PREFIX + field.getGenericType() : field.getGenericType(), field.getAttributeName()));
			} else {
				writer.write(String.format("\t%s %s %s;\n", field.getModifier(), checkPrefixNeeded(entities, field.getType()) ? PREFIX + field.getType() : field.getType(), field.getAttributeName()));
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
				break;
			}
		}
		
		List<String> addedImports = new ArrayList<String>();
		writer.write("import " + ENTITY_PACKAGE + creatingEntity + ";\n");
		addedImports.add(creatingEntity);
		
		for (Field field : fields) {
			for (String entity : entities) {
				if (addedImports.contains(entity)) continue;
				
				if (field.getType().equals(entity)) {
					writer.write("import " + ENTITY_PACKAGE + entity + ";\n");
					addedImports.add(entity);
				} else if (field.getGenericType() != null) {
					if (field.getGenericType().equals(entity)) {
						writer.write("import " + ENTITY_PACKAGE + entity + ";\n");
						addedImports.add(entity);
					}
				}				
			}
		}
		
		writer.write("\n");
	}

	private BufferedWriter createClassFile(String destPath, String entity) throws IOException {
		File file = new File(destPath + PREFIX + entity + ".java");
		if (file.exists()) file.delete();
		file.getParentFile().mkdirs();
		file.createNewFile();
				
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		return writer;
	}

	private List<Field> retrieveClassFields(String srcPath, String entity) {
		File srcFile = new File(srcPath + entity + ".java");
		List<Field> classFields = new ArrayList<Field>();
		
		try {
			Scanner scanner = new Scanner(srcFile);
		
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
		}
		
		return classFields;
	}
}
