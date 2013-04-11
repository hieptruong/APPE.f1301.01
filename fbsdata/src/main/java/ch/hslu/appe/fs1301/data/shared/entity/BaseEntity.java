package ch.hslu.appe.fs1301.data.shared.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Thomas Bomatter
 * BaseEntity for all Entities. The Constructors initializes all Lists so no NPEs.
 * This is needed because JPA somehow does not create those, and NPEs occure if "add**" is called.
 */
public abstract class BaseEntity {
	public BaseEntity() {
		for (Field f : getClass().getDeclaredFields()) {			
			if (f.getType().equals(List.class)) {
				try {
					String methodName = "set" + f.getName().substring(0, 1).toUpperCase() + f.getName().substring(1);
					Method method = getClass().getMethod(methodName, List.class);
					method.invoke(this, ArrayList.class.newInstance());
				} catch (Exception e) {	}
			}
		}
	}
}
