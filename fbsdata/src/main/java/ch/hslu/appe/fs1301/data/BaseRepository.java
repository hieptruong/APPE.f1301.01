package ch.hslu.appe.fs1301.data;

import java.lang.reflect.ParameterizedType;

import ch.hslu.appe.fs1301.data.shared.iAPPEEntityManager;
import ch.hslu.appe.fs1301.data.shared.iRepository;

import com.google.inject.Inject;

public abstract class BaseRepository<T> implements iRepository<T> {

	protected iAPPEEntityManager fEntityManager;

	@Inject
	public BaseRepository(iAPPEEntityManager entityManager) {
		this.fEntityManager = entityManager;		
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public T getById(int id) {
		ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
		return fEntityManager.getEntityObject(clazz, id);		
	}
	
	@Override
	public void saveObject(T object) {
		fEntityManager.saveEntityObject(object);
	}
}
