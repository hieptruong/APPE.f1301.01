
public class Main {

	private static final String[] entityClasses = { "Person", 
													"Produkt",
													"Rechnung", 
													"Korrespondenz", 
													"KorrespondenzTemplate", 
													"Bestellung", 
													"Bestellposition", 
													"ZentrallagerBestellung" };
	
	private static final String entityClassPath = "/Users/Grodien/Documents/HSLU/APPE.f1301.01/fbsdata/src/main/java/ch/hslu/appe/fs1301/data/shared/entity/";
	private static final String DTODestinationPath = "/Users/Grodien/Documents/HSLU/APPE.f1301.01/fbsservice/src/main/java/ch/hslu/appe/fs1301/business/shared/dto/";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Generator generator = new Generator();
		
		generator.generate(entityClasses, entityClassPath, DTODestinationPath);
	}
}
