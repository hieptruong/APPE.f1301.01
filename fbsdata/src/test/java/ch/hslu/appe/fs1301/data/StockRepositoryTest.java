package ch.hslu.appe.fs1301.data;

import static org.fest.assertions.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;
import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

public class StockRepositoryTest {
	private StockRepository fTestee;
	private static iAPPEEntityManager fEntityManager;
	private static Produkt fProdukt;
	private static List<ZentrallagerBestellung> fCreatedOrders;
	
	@BeforeClass
	public static void setUpDatabase() {
		fCreatedOrders = new ArrayList<ZentrallagerBestellung>();
		fEntityManager = new APPEEntityManager();
		fEntityManager.beginTransaction();
		createAndSaveProduct();
		fCreatedOrders.add(createAndSaveZentrallagerBestellung());
		fCreatedOrders.add(createAndSaveZentrallagerBestellung());
		fCreatedOrders.add(createAndSaveZentrallagerBestellung());
	}
	
	@AfterClass
	public static void tearDownDatabase() {
		fEntityManager.rollbackTransaction();
	}
	
	@Before
	public void setUp() {
		fTestee = new StockRepository(fEntityManager);
	}
	
	@Test
	public void returnsAllOrders() {
		List<ZentrallagerBestellung> allProducts = fTestee.getAll();
		
		for(ZentrallagerBestellung product : fCreatedOrders)
		{
			assertThat(allProducts).contains(product);
		}
	}
	
	@Test
	public void ConfirmsStockOrder() {
		fTestee.ConfirmOrderReceivedFromStock(fCreatedOrders.get(0).getId());		
	}

	private static void createAndSaveProduct() {
		fProdukt = new Produkt();
		fProdukt.setBezeichnung("Test");
		fProdukt.setLagerbestand(100);
		fProdukt.setPreis(50);
		fProdukt.setMinimalMenge(5);
		fEntityManager.persist(fProdukt);
	}
	
	private static ZentrallagerBestellung createAndSaveZentrallagerBestellung() {
		ZentrallagerBestellung stockOrder = new ZentrallagerBestellung();
		
		stockOrder.setProdukt(fProdukt);
		setIrrelevantValues(stockOrder);
		fEntityManager.persist(stockOrder);
		return stockOrder;
	}

	private static void setIrrelevantValues(ZentrallagerBestellung stockOrder) {
		stockOrder.setAnzahl(50);
		stockOrder.setLiefertermin(new Date());
	}
}
