package ch.hslu.appe.fs1301.business;

import ch.hslu.appe.fs1301.business.shared.iSessionAPI;
import ch.hslu.appe.fs1301.data.shared.entity.Person;

interface iInternalSessionAPI extends iSessionAPI {
	/**
	 * Gets the authenticated user.
	 * @return The authenticated user.
	 */
	Person getAuthenticatedUser();
}
