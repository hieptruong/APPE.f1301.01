package ch.hslu.appe.fs1301.business;

import java.util.List;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;

public class ProductAPI implements iProductAPI {

	private iProductRepository fProductRepository;

	@Inject
	public ProductAPI(iProductRepository productRepository) {
		fProductRepository = productRepository;
	}

	@Override
	public List<DTOProdukt> getAllProducts() {
		return DTOConverter.convertProdukt(fProductRepository.getAllProducts());
	}
}
