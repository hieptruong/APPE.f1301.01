package ch.hslu.appe.fs1301.data.shared;

import java.util.List;


/**
 * @author Thomas Bomatter
 * iAPPEEntityManager defines interface for database access
 */
public interface iAPPEEntityManager extends iTransaction {
	
	public<T> T getEntityObject(Class<T> entityType, int id);
	
	public<T> T createEntityObject(Class<T> entityType) throws InstantiationException, IllegalAccessException;
	
	public void saveEntityObject(Object object);
	
	public void deleteEntityObject(Object obj);
	
	public void persist(Object obj);
	
	public<T> List<T> executeQuery(String query, Class<T> entityType);

	public void executeInsertQuery(String query);

	public void executeProcedure(String name, Object... params);
}
