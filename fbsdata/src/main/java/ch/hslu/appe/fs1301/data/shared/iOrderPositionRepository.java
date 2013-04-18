package ch.hslu.appe.fs1301.data.shared;

import java.util.List;

import ch.hslu.appe.fs1301.data.shared.entity.Bestellposition;

public interface iOrderPositionRepository {
	/**
	 * 
	 * @param ids
	 * @return
	 * @throws IllegalArgumentException
	 */
	public List<Bestellposition> getOrderPositionsById(int... ids) throws IllegalArgumentException;
}
