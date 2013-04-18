package ch.hslu.appe.fs1301.data.shared;

/**
 * @author Thomas Bomatter
 * iRepository
 */
public interface iRepository<T> {
	/**
	 * Gets an entity by id.
	 * @param id The id.
	 * @return The entity.
	 */
	public T getById(int id);
	/**
	 * Delets an object in the database with transaction!
	 * @param object The object to delete.
	 */
	public void deleteObject(T object);
	/**
	 * Persists an object to the database without transactions.
	 * @param object The object to persist.
	 */
	public void persistObject(T object);
	/**
	 * Saves an object to the database with transaction!
	 * @param object The object to save.
	 */
	public void saveObject(T object);
}
