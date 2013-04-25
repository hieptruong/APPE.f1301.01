package ch.hslu.appe.fs1301.data;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;


/**
 * @author Thomas Bomatter
 * APPEEntityManager handles access to database
 */
public class APPEEntityManager implements iAPPEEntityManager  {
	
	private EntityManagerFactory fEntityManagerFactory;
	private EntityManager fEntityManager;
	
	public APPEEntityManager() {
		fEntityManagerFactory = Persistence.createEntityManagerFactory("fbsdata");
		fEntityManager = fEntityManagerFactory.createEntityManager();
	}
	
	public<T> T getEntityObject(Class<T> entityType, int id) {
		return fEntityManager.find(entityType, id);
	}
	
	public<T> T createEntityObject(Class<T> entityType) throws InstantiationException, IllegalAccessException {
		return entityType.newInstance();
	}
	
	public void saveEntityObject(Object object) {
		beginTransaction();
		fEntityManager.persist(object);
		commitTransaction();
	}
	
	public void deleteEntityObject(Object obj) {
		beginTransaction();
		fEntityManager.remove(obj);
		commitTransaction();
	}
	
	public void beginTransaction() {
		fEntityManager.getTransaction().begin();
	}
	
	public void commitTransaction() {
		fEntityManager.getTransaction().commit();
	}
	
	public void rollbackTransaction() {
		fEntityManager.getTransaction().rollback();
	}
	
	public void executeInsertQuery(String query) {
		fEntityManager.createNativeQuery(query).executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public<T> List<T> executeQuery(String query, Class<T> entityType) {
		Query createQuery = fEntityManager.createQuery(query);
		return (List<T>)createQuery.getResultList();
	}

	@Override
	public void persist(Object obj) {
		fEntityManager.persist(obj);
		fEntityManager.flush();
	}
}
