package ch.hslu.appe.fs1301.business;

import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;

public class ProductAPITest {
	private iProductAPI fTestee;
	private iProductRepository fProductRepositoryMock;
	
	@Before
	public void setUp() {	
		fProductRepositoryMock = PowerMock.createMock(iProductRepository.class);
		fTestee = new ProductAPI(fProductRepositoryMock);		
	}
	
	@Test
	public void returnsListAsDTO_OnGetAllProducts(){
		List<Produkt> jpaList = createProductsById(1, 2);
		setupReturnValueOfGetAllProducts(jpaList);
		PowerMock.replayAll();
		
		List<DTOProdukt> resultList = fTestee.getAllProducts();
		
		
		for(int i = 0; i < jpaList.size(); i++) {
			assertThat(resultList.get(i).getId()).isSameAs(jpaList.get(i).getId());
		}
	}
	
	private List<Produkt> createProductsById(int... ids){
		List<Produkt> list = new ArrayList<Produkt>(ids.length);
		for(int id : ids) {
			Produkt produkt = new Produkt();
			produkt.setId(id);
			list.add(produkt);
		}
		return list;		
	}
	
	private void setupReturnValueOfGetAllProducts(List<Produkt> products) {
		expect(fProductRepositoryMock.getAllProducts()).andReturn(products);
	}
}
