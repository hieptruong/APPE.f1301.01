package ch.hslu.appe.fs1301.data.shared;

/**
 * Enables transaction management.
 * @author Sandro Bollhalder
 */
public interface iTransaction {
	/**
	 * Starts a new transaction.
	 */
	public void beginTransaction();
	/**
	 * Commits the current transaction.
	 */
	public void commitTransaction();
	/**
	 * Rolls back the current transaction.
	 */
	public void rollbackTransaction();
}
