package ch.hslu.appe.fs1301.business;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.data.shared.iTransaction;

public abstract class BaseAPI {

	protected iInternalSessionAPI fSessionAPI;
	protected iTransaction fTransaction;

	protected BaseAPI(iTransaction transaction, iInternalSessionAPI sessionAPI){
		fTransaction = transaction;
		fSessionAPI = sessionAPI;
	}
	
	protected void checkRole(int roleNeeded) throws AccessDeniedException {
		if (fSessionAPI.hasRole(roleNeeded) == false)
		{
			throw new AccessDeniedException();
		}
	}
}
