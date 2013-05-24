package ch.hslu.appe.fs1301.business;

import java.util.Date;
import java.util.List;

import ch.hslu.appe.fs1301.business.shared.Ticket;
import ch.hslu.appe.fs1301.business.shared.iStockAPI;
import ch.hslu.appe.fs1301.data.shared.entity.Produkt;
import ch.hslu.appe.stock.StockException;

public interface iInternalStockAPI extends iStockAPI {
	/**
	 * Order Products from the Stock
	 * @param tickets Map containing tickets and delivery Date
	 * @return The delivery Date
	 */
	public Date finalizeOrder(List<Ticket> tickets) throws StockException;
	
	/**
	 * Reserves a Produkt in the Stock
	 * @param produkt The Produkt
	 * @param anzahl Amount
	 * @return Pair containing tickets and delivery Date
	 */
	public Ticket reserveItem(Produkt produkt, int anzahl) throws StockException;
	
	/**
	 * Frees all produkts that have been reserved by the tickets
	 * @param tickets The Tickets
	 */
	public void cancelReservedTickets(List<Ticket> tickets);
}
