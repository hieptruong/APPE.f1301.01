package ch.hslu.appe.fs1301.data;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iRepository;

import com.google.inject.Inject;

public abstract class BaseRepository<T> implements iRepository<T> {

	protected Class<T> fGenericClass;
	protected iAPPEEntityManager fEntityManager;

	@Inject
	@SuppressWarnings("unchecked")
	public BaseRepository(iAPPEEntityManager entityManager) {
		this.fEntityManager = entityManager;
		
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		fGenericClass = (Class<T>) parameterizedType.getActualTypeArguments()[0];
	}
		
	protected List<T> executeQuery(String query) {
		return fEntityManager.executeQuery(query.toString(), fGenericClass);
	}
	
	protected List<T> executeQuery(StringBuilder queryBuilder) {
		return executeQuery(queryBuilder.toString());
	}
	
	@Override
	public T getById(int id) {
		return fEntityManager.getEntityObject(fGenericClass, id);		
	}
	
	@Override
	public void deleteObject(T object) {
		fEntityManager.deleteEntityObject(object);
	}
	
	@Override
	public void persistObject(T object){
		fEntityManager.persist(object);
	}
	
	@Override
	public void saveObject(T object) {
		fEntityManager.saveEntityObject(object);
	}
}
