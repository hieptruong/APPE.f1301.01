package ch.hslu.appe.fs1301.data.shared;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.ZentrallagerBestellung;

/**
 * Handels all calls to the entity Produkt.
 * @author Sandro Bollhalder
 */
public interface iStockRepository extends iRepository<ZentrallagerBestellung>{

	public void confirmOrderReceivedFromStock(int id);

	public List<ZentrallagerBestellung> getAll();
}
