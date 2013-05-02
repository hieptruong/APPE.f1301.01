package ch.hslu.appe.fs1303.gui.models.quicksearch;

import java.util.ArrayList;
import java.util.List;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;

import com.google.inject.Inject;

public class ProductQuickSearchModel extends QuickSearchModel<DTOProdukt> {

	@Inject
	iProductAPI personAPI;
	private List<DTOProdukt> fAllProducts;
	
	public ProductQuickSearchModel() {
		super();
		try {
			fAllProducts = personAPI.getAllProducts();
		} catch (AccessDeniedException e) {
			fAllProducts = new ArrayList<DTOProdukt>();
		}
	}
	
	@Override
	public List<DTOProdukt> search(String filter) {
		List<DTOProdukt> results = new ArrayList<DTOProdukt>();
		 for (DTOProdukt dtoProdukt : fAllProducts) {
			 if (dtoProdukt.getBezeichnung().toLowerCase().contains(filter.toLowerCase()))
				 results.add(dtoProdukt);			
		 }
		 return results;
	}

}
