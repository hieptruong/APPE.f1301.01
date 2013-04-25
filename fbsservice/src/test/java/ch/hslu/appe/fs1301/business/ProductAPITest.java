package ch.hslu.appe.fs1301.business;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.powermock.api.easymock.PowerMock;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1301.data.shared.iProductRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;

public class ProductAPITest {
	private iProductAPI fTestee;
	private iProductRepository fProductRepositoryMock;
	private iInternalSessionAPI fSessionMock;
	private iTransaction fTransactionMock;
	
	@Before
	public void setUp() {	
		fProductRepositoryMock = PowerMock.createMock(iProductRepository.class);
		fSessionMock = PowerMock.createMock(iInternalSessionAPI.class);
		fTransactionMock = PowerMock.createMock(iTransaction.class);
		fTestee = new ProductAPI(fProductRepositoryMock, fTransactionMock, fSessionMock);		
	}
	
	@After
	public void cleanUp() {
		PowerMock.niceReplayAndVerify();
	}
	
	@Test(expected = AccessDeniedException.class)
	public void needsSysUserOrAdmin_OnGetProductById() throws AccessDeniedException {
		final int ExpectedRole = UserRole.SYSUSER | UserRole.ADMIN;
		
		//Check against role
		expect(fSessionMock.hasRole(ExpectedRole)).andReturn(false);
		PowerMock.replayAll();
		
		fTestee.getProductById(0);
	}
	
	@Test
	public void returnsProdukt_OnGetProductById() throws AccessDeniedException {
		final int ExpectedId = 10;
		setupCheckRoleIrrelevant();
		
		Produkt returnedProduct = new Produkt();
		returnedProduct.setId(ExpectedId);
		
		expect(fProductRepositoryMock.getById(eq(ExpectedId))).andReturn(returnedProduct);
		PowerMock.replayAll();
		
		DTOProdukt result = fTestee.getProductById(ExpectedId);
		assertThat(result.getId()).isSameAs(ExpectedId);
	}
	
	@Test
	public void returnsNull_OnGetProduktById_WhenNullIsReturnedByRepo() throws AccessDeniedException {
		final int Id = 10;
		setupCheckRoleIrrelevant();
		
		expect(fProductRepositoryMock.getById(eq(Id))).andReturn(null);
		PowerMock.replayAll();
		
		DTOProdukt result = fTestee.getProductById(Id);
		assertThat(result).isNull();
	}
	
	@Test(expected = AccessDeniedException.class)
	public void needsSysUserOrAdmin_OnGetAllProducts() throws AccessDeniedException {
		final int ExpectedRole = UserRole.SYSUSER | UserRole.ADMIN;
		
		//Check against role
		expect(fSessionMock.hasRole(ExpectedRole)).andReturn(false);
		PowerMock.replayAll();
		
		fTestee.getAllProducts();
	}
	
	@Test
	public void returnsListAsDTO_OnGetAllProducts() throws AccessDeniedException{
		setupCheckRoleIrrelevant();
		
		List<Produkt> jpaList = createProductsById(1, 2);
		setupReturnValueOfGetAllProducts(jpaList);
		PowerMock.replayAll();
		
		List<DTOProdukt> resultList = fTestee.getAllProducts();
		
		
		for(int i = 0; i < jpaList.size(); i++) {
			assertThat(resultList.get(i).getId()).isSameAs(jpaList.get(i).getId());
		}
	}
	
	@Test(expected = AccessDeniedException.class)
	public void needsSysUserOrAdmin_OnSaveProduct() throws AccessDeniedException {
		final int ExpectedRole = UserRole.SYSUSER | UserRole.ADMIN;
		
		//Check against role
		expect(fSessionMock.hasRole(ExpectedRole)).andReturn(false);
		PowerMock.replayAll();
		
		fTestee.saveProduct(null);
	}
	
	@Test
	public void createsANewProduct_OnSaveProduct_WhenIdIsZero() throws AccessDeniedException {
		final int expectedId = 0;
		setupCheckRoleIrrelevant();
		
		DTOProdukt expectedProduct = new DTOProdukt();
		expectedProduct.setId(expectedId);
		expectedProduct.setBezeichnung("Name");
		Capture<Produkt> cap = new Capture<Produkt>();
		fProductRepositoryMock.saveObject(capture(cap));
		PowerMock.replayAll();
		
		DTOProdukt result = fTestee.saveProduct(expectedProduct);
		
		assertThat(cap.getValue().getBezeichnung()).isEqualTo(expectedProduct.getBezeichnung());
		assertThat(result.getBezeichnung()).isEqualTo(expectedProduct.getBezeichnung());
	}

	@Test
	public void createsANewProduct_OnSaveProduct_WhenIdIsNull() throws AccessDeniedException {
		setupCheckRoleIrrelevant();
		
		DTOProdukt expectedProduct = new DTOProdukt();
		expectedProduct.setBezeichnung("Name");
		Capture<Produkt> cap = new Capture<Produkt>();
		fProductRepositoryMock.saveObject(capture(cap));
		PowerMock.replayAll();
		
		DTOProdukt result = fTestee.saveProduct(expectedProduct);
		
		assertThat(cap.getValue().getBezeichnung()).isEqualTo(expectedProduct.getBezeichnung());
		assertThat(result.getBezeichnung()).isEqualTo(expectedProduct.getBezeichnung());
	}

	@Test
	public void updatesProduct_OnSaveProduct_WhenIdIsKnown() throws AccessDeniedException {
		final int ExpectedId = 10;
		
		setupCheckRoleIrrelevant();
		
		DTOProdukt expectedProduct = new DTOProdukt();
		expectedProduct.setId(ExpectedId);
		expectedProduct.setBezeichnung("UserName");
		
		Produkt returnedProduct = new Produkt();		
		expect(fProductRepositoryMock.getById(ExpectedId)).andReturn(returnedProduct);
		
		Capture<Produkt> cap = new Capture<Produkt>();
		fProductRepositoryMock.saveObject(capture(cap));
		PowerMock.replayAll();
		
		DTOProdukt result = fTestee.saveProduct(expectedProduct);
		
		assertThat(cap.getValue().getBezeichnung()).isEqualTo(expectedProduct.getBezeichnung());
		assertThat(result.getBezeichnung()).isEqualTo(expectedProduct.getBezeichnung());
	}
	
	private void setupCheckRoleIrrelevant() {
		expect(fSessionMock.hasRole(EasyMock.anyInt())).andReturn(true);
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
