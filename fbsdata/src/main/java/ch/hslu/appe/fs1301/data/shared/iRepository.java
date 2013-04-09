package ch.hslu.appe.fs1301.data.shared;

/**
 * @author Thomas Bomatter
 * iRepository
 */
public interface iRepository<T> {
	public T getById(int id);
	
	public void saveObject(T object);
}
