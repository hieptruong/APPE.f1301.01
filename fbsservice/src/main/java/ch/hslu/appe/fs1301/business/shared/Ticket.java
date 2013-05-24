package ch.hslu.appe.fs1301.business.shared;

import java.util.Date;

public class Ticket {
	
	private Date fDeliveryDate;
	private Long fTicket;

	public Ticket(Long ticket, Date deliveryDate) {
		setTicket(ticket);
		setDeliveryDate(deliveryDate);
		
	}

	public Date getDeliveryDate() {
		return fDeliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		fDeliveryDate = deliveryDate;
	}

	public Long getTicket() {
		return fTicket;
	}

	public void setTicket(Long ticket) {
		fTicket = ticket;
	}
}
