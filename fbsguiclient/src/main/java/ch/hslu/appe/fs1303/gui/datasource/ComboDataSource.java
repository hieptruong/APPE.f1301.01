package ch.hslu.appe.fs1303.gui.datasource;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ComboDataSource {
	
	private int fId;
	private String fValue;

	public ComboDataSource(int id, String value) {
		setId(id);
		setValue(value);		
	}
	
	public static List<ComboDataSource> getDataSourceForClass(Class<?> clazz) {
		List<ComboDataSource> datasource = new ArrayList<ComboDataSource>();
		
		for (Field field : clazz.getDeclaredFields()) {
			if (field.getType().equals(int.class)) {
				try {
					datasource.add(new ComboDataSource((Integer)field.get(null), field.getName()));
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} 
		
		return datasource;
	}

	public int getId() {
		return fId;
	}

	public void setId(int id) {
		fId = id;
	}

	public String getValue() {
		return fValue;
	}

	public void setValue(String value) {
		fValue = value;
	}
}
