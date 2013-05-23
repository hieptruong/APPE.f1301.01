package ch.hslu.appe.fs1301.data.shared;

import java.util.Date;
import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

/**
 * Handels all calls to the entity Produkt.
 * @author Sandro Bollhalder
 */
public interface iStockRepository extends iRepository<ZentrallagerBestellung>{

	public void OrderFromStock(int productId, int count, Date deliveryDate);
	
	public void ConfirmOrderReceivedFromStock(int id);

	public List<ZentrallagerBestellung> getAll();
}
