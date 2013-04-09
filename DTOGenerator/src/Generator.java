import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;


public class Generator {

	private final String PREFIX = "DTO";
	
	public void generate(String[] entities, String srcPath, String destPath) {
		
		for (String entity : entities) {
			File srcFile = new File(srcPath + entity + ".java");			
			
			try {
				Scanner scanner = new Scanner(srcFile);				
			
				File file = new File(destPath + PREFIX + entity + ".java");
				if (file.exists()) file.delete();
				file.getParentFile().mkdirs();
				file.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				
				writer.write("package ch.hslu.appe.fs1301.business.dto;\n");
				writer.write("\n");
				writer.write("import java.util.Date;\n");
				writer.write("import java.util.List;\n");
				writer.write("\n");
				writer.write("/**\n");
				writer.write("* Auto-Generated DTOs\n");
				writer.write("* " + new Date().toString() + "\n");
				writer.write("*/\n");
				writer.write("public class " + PREFIX + entity + " {\n");
								
				// Remove all line until the class starts
				while (true) {					
					String line = scanner.nextLine();
					if (line.contains("public class"))
						break;
				}				
				
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					
					if (line.contains("serialVersionUID")) continue;
					
					// We don't want the JPA stuff
					if (line.contains("@")) continue;
					
					// Don't need comments
					if (line.trim().startsWith("//")) continue;
					
					for (String clazz : entities) {
						if (line.contains("<" + clazz + ">")) {
							line= line.replaceAll("<" + clazz + ">", "<" + PREFIX + clazz + ">");							
						}
						 
						if (line.contains(" " + clazz + " ")) {
							line= line.replaceAll(" " + clazz + " ", " " + PREFIX + clazz + " ");
						}
						
						if (line.contains("(" + clazz + " ")) {
							line= line.replaceAll("\\(" + clazz + " ", "\\(" + PREFIX + clazz + " ");
						}
						
						if (line.contains(" " + clazz + "()")) {
							line= line.replaceAll(" " + clazz + "\\(\\)", " " + PREFIX + clazz + "\\(\\)");
						}
					}
					
					writer.write(line + "\n");
				}
				
				writer.flush();
				writer.close();
				
			} catch (IOException e) {			
				System.out.println(e.getMessage());
			}
		}			
	}
}
