package ch.hslu.appe.fs1301.business;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOConverter;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;

import com.google.inject.Inject;

public class ProductAPI extends BaseAPI implements iProductAPI {

	private iProductRepository fProductRepository;

	@Inject
	public ProductAPI(iProductRepository productRepository, iTransaction transaction, iInternalSessionAPI sessionAPI) {
		super(transaction, sessionAPI);
		fProductRepository = productRepository;
	}
	
	@Override
	public DTOProdukt saveProduct(DTOProdukt product) throws AccessDeniedException {
		checkRole(UserRole.SYSUSER);
		
		Produkt entity = product.getId() != 0 ? fProductRepository.getById(product.getId()) : null;
		if (entity == null) {
			entity = DTOProdukt.createNewProduktFromDTO(product);
		} else {
			DTOProdukt.updateProduktFromDTO(entity, product);
		}
		
		fProductRepository.saveObject(entity);
		DTOProdukt newProduct = new DTOProdukt(entity);
		return newProduct;
	}

	@Override
	public List<DTOProdukt> getAllProducts() {
		return DTOConverter.convertProdukt(fProductRepository.getAllProducts());
	}

	@Override
	public DTOProdukt getProductById(int id) {
		Produkt product = fProductRepository.getById(id);
		return product != null ? new DTOProdukt(product) : null;
	}
}
