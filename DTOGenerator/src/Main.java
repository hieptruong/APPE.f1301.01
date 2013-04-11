
public class Main {

	private static final String[] entityClasses = { "Person", 
													"Produkt",
													"Rechnung", 
													"Korrespondenz", 
													"KorrespondenzTemplate", 
													"Bestellung", 
													"Bestellposition", 
													"ZentrallagerBestellung" };
	
	private static final String entityClassPath = "/../../fbsdata/src/main/java/ch/hslu/appe/fs1301/data/shared/entity/";
	private static final String DTODestinationPath = "/../../fbsservice/src/main/java/ch/hslu/appe/fs1301/business/shared/dto/";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Generator generator = new Generator();
		
		generator.generate(entityClasses, entityClassPath, DTODestinationPath);
	}
}
