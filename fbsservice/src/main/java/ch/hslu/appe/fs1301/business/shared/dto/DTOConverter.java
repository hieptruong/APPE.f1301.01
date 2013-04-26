package ch.hslu.appe.fs1301.business.shared.dto;

import java.util.List;
import java.util.ArrayList;
import ch.hslu.appe.fs1301.data.shared.entity.Person;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;
import ch.hslu.appe.fs1301.data.shared.entity.Rechnung;
import ch.hslu.appe.fs1301.data.shared.entity.Korrespondenz;
import ch.hslu.appe.fs1301.data.shared.entity.KorrespondenzTemplate;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

/**
* Auto-Generated DTOs
* Thu Apr 25 13:49:03 CEST 2013
*/
public class DTOConverter {

	public static List<DTOPerson> convertPerson(List<Person> personList) {
		List<DTOPerson> dtoPersonList = new ArrayList<DTOPerson>();
		for(Person person : personList)
			dtoPersonList.add(new DTOPerson(person));
		return dtoPersonList;
	}

	public static List<DTOProdukt> convertProdukt(List<Produkt> produktList) {
		List<DTOProdukt> dtoProduktList = new ArrayList<DTOProdukt>();
		for(Produkt produkt : produktList)
			dtoProduktList.add(new DTOProdukt(produkt));
		return dtoProduktList;
	}

	public static List<DTORechnung> convertRechnung(List<Rechnung> rechnungList) {
		List<DTORechnung> dtoRechnungList = new ArrayList<DTORechnung>();
		for(Rechnung rechnung : rechnungList)
			dtoRechnungList.add(new DTORechnung(rechnung));
		return dtoRechnungList;
	}

	public static List<DTOKorrespondenz> convertKorrespondenz(List<Korrespondenz> korrespondenzList) {
		List<DTOKorrespondenz> dtoKorrespondenzList = new ArrayList<DTOKorrespondenz>();
		for(Korrespondenz korrespondenz : korrespondenzList)
			dtoKorrespondenzList.add(new DTOKorrespondenz(korrespondenz));
		return dtoKorrespondenzList;
	}

	public static List<DTOKorrespondenzTemplate> convertKorrespondenzTemplate(List<KorrespondenzTemplate> korrespondenztemplateList) {
		List<DTOKorrespondenzTemplate> dtoKorrespondenzTemplateList = new ArrayList<DTOKorrespondenzTemplate>();
		for(KorrespondenzTemplate korrespondenztemplate : korrespondenztemplateList)
			dtoKorrespondenzTemplateList.add(new DTOKorrespondenzTemplate(korrespondenztemplate));
		return dtoKorrespondenzTemplateList;
	}

	public static List<DTOBestellung> convertBestellung(List<Bestellung> bestellungList) {
		List<DTOBestellung> dtoBestellungList = new ArrayList<DTOBestellung>();
		for(Bestellung bestellung : bestellungList)
			dtoBestellungList.add(new DTOBestellung(bestellung));
		return dtoBestellungList;
	}

	public static List<DTOBestellposition> convertBestellposition(List<Bestellposition> bestellpositionList) {
		List<DTOBestellposition> dtoBestellpositionList = new ArrayList<DTOBestellposition>();
		for(Bestellposition bestellposition : bestellpositionList)
			dtoBestellpositionList.add(new DTOBestellposition(bestellposition));
		return dtoBestellpositionList;
	}

	public static List<DTOZentrallagerBestellung> convertZentrallagerBestellung(List<ZentrallagerBestellung> zentrallagerbestellungList) {
		List<DTOZentrallagerBestellung> dtoZentrallagerBestellungList = new ArrayList<DTOZentrallagerBestellung>();
		for(ZentrallagerBestellung zentrallagerbestellung : zentrallagerbestellungList)
			dtoZentrallagerBestellungList.add(new DTOZentrallagerBestellung(zentrallagerbestellung));
		return dtoZentrallagerBestellungList;
	}
}