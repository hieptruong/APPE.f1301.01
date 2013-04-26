package ch.hslu.appe.fs1301.business;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.eq;
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
import ch.hslu.appe.fs1301.business.shared.OrderSource;
import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.data.shared.iOrderPositionRepository;
import ch.hslu.appe.fs1301.data.shared.iOrderRepository;
import ch.hslu.appe.fs1301.data.shared.iPersonRepository;
import ch.hslu.appe.fs1301.data.shared.iTransaction;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;
import ch.hslu.appe.fs1301.data.shared.entity.Bestellung;
import ch.hslu.appe.fs1301.data.shared.entity.Person;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;

public class OrderAPITest {
	private OrderAPI fTestee;
	private iTransaction fTransactionMock;
	private iInternalSessionAPI fSessionAPIMock;
	private iOrderRepository fOrderRepositoryMock;
	private iOrderPositionRepository fPositionRepositoryMock;
	private iPersonRepository fPersonRepositoryMock;
	
	@Before
	public void setUp() {
		fTransactionMock = PowerMock.createMock(iTransaction.class);
		fSessionAPIMock = PowerMock.createMock(iInternalSessionAPI.class);
		fOrderRepositoryMock = PowerMock.createMock(iOrderRepository.class);
		fPositionRepositoryMock = PowerMock.createMock(iOrderPositionRepository.class);
		fPersonRepositoryMock = PowerMock.createMock(iPersonRepository.class);
		fTestee = new OrderAPI(fTransactionMock, fSessionAPIMock, fOrderRepositoryMock, fPositionRepositoryMock, fPersonRepositoryMock);
	}

	@After
	public void cleanUp() {
		PowerMock.niceReplayAndVerify();
	}
	
	@Test(expected = AccessDeniedException.class)
	public void needsSysUserOrAdmin_OnGetOrders() throws AccessDeniedException {
		final int ExpectedRole = UserRole.SYSUSER | UserRole.ADMIN;
		
		//Check against role
		expect(fSessionAPIMock.hasRole(ExpectedRole)).andReturn(false);
		PowerMock.replayAll();
		
		fTestee.getOrders();
	}
	
	@Test
	public void returnsAnEmptyList_OnGetOrders_WhenAnIllegalArgumentExceptionIsThrown() throws AccessDeniedException {
		setupCheckRoleIrrelevant();
		expect(fOrderRepositoryMock.getOrdersById()).andThrow(new IllegalArgumentException());
		PowerMock.replayAll();
		
		List<DTOBestellung> resultList = fTestee.getOrders();
		assertThat(resultList).isEmpty();
	}
	
	@Test
	public void returnsListAsDTO_OnGetOrders() throws AccessDeniedException{
		setupCheckRoleIrrelevant();
		
		List<Bestellung> jpaList = createOrdersById(1, 2);
		expect(fOrderRepositoryMock.getOrdersById(1, 2)).andReturn(jpaList);
		PowerMock.replayAll();
		
		List<DTOBestellung> resultList = fTestee.getOrders(1, 2);
		
		
		for(int i = 0; i < jpaList.size(); i++) {
			assertThat(resultList.get(i).getId()).isSameAs(jpaList.get(i).getId());
		}
	}

	@Test(expected = AccessDeniedException.class)
	public void needsSysUserOrAdmin_OnGetOrderPositions() throws AccessDeniedException {
		final int ExpectedRole = UserRole.SYSUSER | UserRole.ADMIN;
		
		//Check against role
		expect(fSessionAPIMock.hasRole(ExpectedRole)).andReturn(false);
		PowerMock.replayAll();
		
		fTestee.getOrderPositions();
	}
	
	@Test
	public void returnsAnEmptyList_OnGetOrderPositions_WhenAnIllegalArgumentExceptionIsThrown() throws AccessDeniedException {
		setupCheckRoleIrrelevant();
		expect(fPositionRepositoryMock.getOrderPositionsById()).andThrow(new IllegalArgumentException());
		PowerMock.replayAll();
		
		List<DTOBestellposition> resultList = fTestee.getOrderPositions();
		assertThat(resultList).isEmpty();
	}
	
	@Test
	public void returnsListAsDTO_OnGetOrderPositions() throws AccessDeniedException{
		setupCheckRoleIrrelevant();
		
		List<Bestellposition> jpaList = createOrderPositionsById(1, 2);
		expect(fPositionRepositoryMock.getOrderPositionsById(1, 2)).andReturn(jpaList);
		PowerMock.replayAll();
		
		List<DTOBestellposition> resultList = fTestee.getOrderPositions(1, 2);
		
		
		for(int i = 0; i < jpaList.size(); i++) {
			assertThat(resultList.get(i).getId()).isSameAs(jpaList.get(i).getId());
		}
	}
	

	@Test(expected = AccessDeniedException.class)
	public void needsSysUserOrAdmin_OnCreateNewOrder() throws AccessDeniedException {
		final int ExpectedRole = UserRole.SYSUSER | UserRole.ADMIN;
		
		//Check against role
		expect(fSessionAPIMock.hasRole(ExpectedRole)).andReturn(false);
		PowerMock.replayAll();
		
		fTestee.createNewOrder(1, 1, null);
	}
	
	@Test
	public void returnsNull_OnCreateNewOrder_WhenAnExceptionOccurs() throws AccessDeniedException{
		setupCheckRoleIrrelevant();
		setupBeginTransaction();
		//Provoke an error due to not beeing able to convert
		expect(fPersonRepositoryMock.getById(EasyMock.anyInt())).andReturn(null);
		expect(fSessionAPIMock.getAuthenticatedUser()).andReturn(null);
		fOrderRepositoryMock.persistObject(isA(Bestellung.class));
		
		//Needs a rollback
		setupRollbackTransaction();
		PowerMock.replayAll();
		
		DTOBestellung dtoBestellung = fTestee.createNewOrder(1, 1, null);
		
		assertThat(dtoBestellung).isNull();
	}
	
	@Test
	public void returnsDTO_OnCreateNewOrder() throws AccessDeniedException {
		final int CustomerId = 123;
		final int ExpectedId = 2345;
		final int Source = OrderSource.FAX;
		
		Capture<Bestellung> cap = new Capture<Bestellung>();
		Person personMock = new Person();
		Person userMock = new Person();
		Bestellung bestellung = new Bestellung();
		bestellung.setId(ExpectedId);
		bestellung.setPerson1(personMock);
		bestellung.setPerson2(userMock);
		
		setupCheckRoleIrrelevant();
		setupBeginTransaction();
		expect(fPersonRepositoryMock.getById(eq(CustomerId))).andReturn(personMock);
		expect(fSessionAPIMock.getAuthenticatedUser()).andReturn(userMock);
		fOrderRepositoryMock.persistObject(capture(cap));
		expect(fOrderRepositoryMock.getById(EasyMock.anyInt())).andReturn(bestellung);
		fTransactionMock.commitTransaction();
		PowerMock.replayAll();
		
		DTOBestellung dtoBestellung = fTestee.createNewOrder(CustomerId, Source, new ArrayList<DTOBestellposition>());
		
		assertThat(cap.getValue().getPerson1()).isSameAs(personMock);
		assertThat(cap.getValue().getPerson2()).isSameAs(userMock);
		assertThat(cap.getValue().getQuelle()).isEqualTo(Source);
		assertThat(dtoBestellung).isNotNull();
		assertThat(dtoBestellung.getId()).isEqualTo(ExpectedId);
	}
	
	@Test
	public void returnsNull_OnCreateNewOrder_WhenAPositionCanNotBeOrdered() throws AccessDeniedException {
		List<DTOBestellposition> positions = new ArrayList<DTOBestellposition>();
		DTOBestellposition position = new DTOBestellposition();
		position.setAnzahl(10);
		position.setProdukt(1);
		position.setStueckpreis(50);
		positions.add(position);
		
		setupCheckRoleIrrelevant();
		setupBeginTransaction();
		expect(fPersonRepositoryMock.getById(EasyMock.anyInt())).andReturn(null);
		expect(fSessionAPIMock.getAuthenticatedUser()).andReturn(null);
		fOrderRepositoryMock.persistObject(isA(Bestellung.class));
		
		//Return false => not accepted by repo, abort order
		expect(fPositionRepositoryMock.orderProduct(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt())).
			andReturn(false);
		setupRollbackTransaction();
		PowerMock.replayAll();
		
		DTOBestellung dtoBestellung = fTestee.createNewOrder(1, 1, positions);
		assertThat(dtoBestellung).isNull();
	}
	
	@Test
	public void ordersAllPosition_OnCreateNewOrder() throws AccessDeniedException {
		DTOBestellposition expectedPositionOne = createOrderPosition(1,2,3,4);
		DTOBestellposition expectedPositionTwo = createOrderPosition(5, 6, 7, 8);
		List<DTOBestellposition> positions = new ArrayList<DTOBestellposition>();
		positions.add(expectedPositionOne);
		positions.add(expectedPositionTwo);
		
		Bestellung bestellung = new Bestellung();
		bestellung.setPerson1(new Person());
		bestellung.setPerson2(new Person());
		
		setupCheckRoleIrrelevant();
		setupBeginTransaction();
		expect(fPersonRepositoryMock.getById(EasyMock.anyInt())).andReturn(null);
		expect(fSessionAPIMock.getAuthenticatedUser()).andReturn(null);
		fOrderRepositoryMock.persistObject(isA(Bestellung.class));
		expect(fOrderRepositoryMock.getById(EasyMock.anyInt())).andReturn(bestellung);
		
		//expect two calls to position repo to order the products
		expect(fPositionRepositoryMock.orderProduct(0, expectedPositionOne.getProdukt(), expectedPositionOne.getAnzahl(), expectedPositionOne.getStueckpreis())).andReturn(true);
		expect(fPositionRepositoryMock.orderProduct(0, expectedPositionTwo.getProdukt(), expectedPositionTwo.getAnzahl(), expectedPositionTwo.getStueckpreis())).andReturn(true);
		fTransactionMock.commitTransaction();
		PowerMock.replayAll();
		
		DTOBestellung dtoBestellung = fTestee.createNewOrder(1, 1, positions);
		assertThat(dtoBestellung).isNotNull();
	}
	
	private DTOBestellposition createOrderPosition(int id, int productId, int count, int price) {
		DTOBestellposition position = new DTOBestellposition();
		position.setId(id);
		position.setProdukt(productId);
		position.setAnzahl(count);
		position.setStueckpreis(price);
		return position;
	}

	private void setupRollbackTransaction() {
		fTransactionMock.rollbackTransaction();
	}
	
	private void setupBeginTransaction() {
		fTransactionMock.beginTransaction();
	}

	private List<Bestellposition> createOrderPositionsById(int... ids) {
		List<Bestellposition> list = new ArrayList<Bestellposition>(ids.length);
		for(int id : ids) {
			Bestellposition bestellPosition = new Bestellposition();
			bestellPosition.setId(id);
			bestellPosition.setBestellung(new Bestellung());
			bestellPosition.setProdukt(new Produkt());
			list.add(bestellPosition);
		}
		return list;
	}
	
	private List<Bestellung> createOrdersById(int... ids) {
		List<Bestellung> list = new ArrayList<Bestellung>(ids.length);
		for(int id : ids) {
			Bestellung bestellung = new Bestellung();
			bestellung.setId(id);
			bestellung.setPerson1(new Person());
			bestellung.setPerson2(new Person());
			list.add(bestellung);
		}
		return list;		
	}

	private void setupCheckRoleIrrelevant() {
		expect(fSessionAPIMock.hasRole(EasyMock.anyInt())).andReturn(true);
	}
}
