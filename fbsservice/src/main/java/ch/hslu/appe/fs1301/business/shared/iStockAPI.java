package ch.hslu.appe.fs1301.business.shared;

import java.util.List;

import ch.hslu.appe.fs1301.business.shared.dto.DTOZentrallagerBestellung;

public interface iStockAPI {
	public List<DTOZentrallagerBestellung> getOpenOrders() throws AccessDeniedException;
	public void confirmOrderReceivedFromStock(int id) throws AccessDeniedException;
}
