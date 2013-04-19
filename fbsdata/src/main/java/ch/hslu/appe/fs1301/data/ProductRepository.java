package ch.hslu.appe.fs1301.data;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;

import com.google.inject.Inject;

public class ProductRepository extends BaseRepository<Produkt> implements iProductRepository {

	@Inject
	public ProductRepository(iAPPEEntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Produkt> getAllProducts() {
		final String Query = "SELECT p FROM Produkt p";

		return executeQuery(Query);
	}

}
