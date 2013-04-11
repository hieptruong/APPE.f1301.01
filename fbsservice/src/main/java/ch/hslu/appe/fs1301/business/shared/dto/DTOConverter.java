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
* Thu Apr 11 23:17:20 CEST 2013
*/
public class DTOConverter {

	public static List<DTOPerson> convertPerson(List<Person> personList) {
		List<DTOPerson> dtoPersonList = new ArrayList<DTOPerson>();
		for(Person person : personList) {
			DTOPerson dtoPerson = new DTOPerson(person);
			dtoPersonList.add(dtoPerson);
		}
		return dtoPersonList;
	}

	public static List<DTOProdukt> convertProdukt(List<Produkt> produktList) {
		List<DTOProdukt> dtoProduktList = new ArrayList<DTOProdukt>();
		for(Produkt produkt : produktList) {
			DTOProdukt dtoProdukt = new DTOProdukt(produkt);
			dtoProduktList.add(dtoProdukt);
		}
		return dtoProduktList;
	}

	public static List<DTORechnung> convertRechnung(List<Rechnung> rechnungList) {
		List<DTORechnung> dtoRechnungList = new ArrayList<DTORechnung>();
		for(Rechnung rechnung : rechnungList) {
			DTORechnung dtoRechnung = new DTORechnung(rechnung);
			dtoRechnungList.add(dtoRechnung);
		}
		return dtoRechnungList;
	}

	public static List<DTOKorrespondenz> convertKorrespondenz(List<Korrespondenz> korrespondenzList) {
		List<DTOKorrespondenz> dtoKorrespondenzList = new ArrayList<DTOKorrespondenz>();
		for(Korrespondenz korrespondenz : korrespondenzList) {
			DTOKorrespondenz dtoKorrespondenz = new DTOKorrespondenz(korrespondenz);
			dtoKorrespondenzList.add(dtoKorrespondenz);
		}
		return dtoKorrespondenzList;
	}

	public static List<DTOKorrespondenzTemplate> convertKorrespondenzTemplate(List<KorrespondenzTemplate> korrespondenztemplateList) {
		List<DTOKorrespondenzTemplate> dtoKorrespondenzTemplateList = new ArrayList<DTOKorrespondenzTemplate>();
		for(KorrespondenzTemplate korrespondenztemplate : korrespondenztemplateList) {
			DTOKorrespondenzTemplate dtoKorrespondenzTemplate = new DTOKorrespondenzTemplate(korrespondenztemplate);
			dtoKorrespondenzTemplateList.add(dtoKorrespondenzTemplate);
		}
		return dtoKorrespondenzTemplateList;
	}

	public static List<DTOBestellung> convertBestellung(List<Bestellung> bestellungList) {
		List<DTOBestellung> dtoBestellungList = new ArrayList<DTOBestellung>();
		for(Bestellung bestellung : bestellungList) {
			DTOBestellung dtoBestellung = new DTOBestellung(bestellung);
			dtoBestellungList.add(dtoBestellung);
		}
		return dtoBestellungList;
	}

	public static List<DTOBestellposition> convertBestellposition(List<Bestellposition> bestellpositionList) {
		List<DTOBestellposition> dtoBestellpositionList = new ArrayList<DTOBestellposition>();
		for(Bestellposition bestellposition : bestellpositionList) {
			DTOBestellposition dtoBestellposition = new DTOBestellposition(bestellposition);
			dtoBestellpositionList.add(dtoBestellposition);
		}
		return dtoBestellpositionList;
	}

	public static List<DTOZentrallagerBestellung> convertZentrallagerBestellung(List<ZentrallagerBestellung> zentrallagerbestellungList) {
		List<DTOZentrallagerBestellung> dtoZentrallagerBestellungList = new ArrayList<DTOZentrallagerBestellung>();
		for(ZentrallagerBestellung zentrallagerbestellung : zentrallagerbestellungList) {
			DTOZentrallagerBestellung dtoZentrallagerBestellung = new DTOZentrallagerBestellung(zentrallagerbestellung);
			dtoZentrallagerBestellungList.add(dtoZentrallagerBestellung);
		}
		return dtoZentrallagerBestellungList;
	}
}