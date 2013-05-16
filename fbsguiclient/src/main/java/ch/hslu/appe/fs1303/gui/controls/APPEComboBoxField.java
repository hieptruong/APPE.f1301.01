package ch.hslu.appe.fs1303.gui.controls;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.datasource.ComboDataSource;

class ComboBoxCreator {
	public static Combo createCombo(Composite parent, int style) {
		return new Combo(parent, SWT.DROP_DOWN);
	}
}

public class APPEComboBoxField extends APPEControl<Integer, Combo> {

	private List<ComboDataSource> fDataSource;
	
	public APPEComboBoxField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
	}

	@Override
	public Combo createControl(Composite parent, FormToolkit toolkit, int style) {
		Combo combo = ComboBoxCreator.createCombo(parent, style);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		toolkit.adapt(combo);
		return combo;
	}

	@Override
	public void addModifyListener(ModifyListener listener) {
		fControl.addModifyListener(listener);
	}

	@Override
	public void removeModifyListener(ModifyListener listener) {
		fControl.removeModifyListener(listener);
	}

	@Override
	public String getControlValue() {
		int selectionIndex = fControl.getSelectionIndex();
		if (selectionIndex < 0) return null;
		
		return fDataSource.get(selectionIndex).getValue();
	}

	@Override
	public Class<?> getFieldClass() {
		return Integer.class;
	}

	@Override
	public void setControlValue(String value) {
		if (value != null) {
			for (int i = 0; i < fDataSource.size(); i++) {
				if (fDataSource.get(i).getValue().equals(value)) {
					fControl.select(i);
				}
			}
		}
	}

	@Override
	public Integer getValueForModel(String value) {
		for (ComboDataSource source : fDataSource) {
			if (source.getValue().equals(value)) {
				return source.getId();
			}
		}
		
		return null;
	}

	@Override
	public String getDisplayValue(Integer value) {
		if (value == null) return null;
		
		for (ComboDataSource source : fDataSource) {
			if (source.getId() == value) {
				return source.getValue();
			}
		}
		
		return null;
	}

	public List<ComboDataSource> getDataSource() {
		return fDataSource;
	}

	public void setDataSource(List<ComboDataSource> dataSource) {
		fDataSource = dataSource;
		
		for (ComboDataSource source : dataSource) {
			fControl.add(source.getValue());
		}		
	}
}
