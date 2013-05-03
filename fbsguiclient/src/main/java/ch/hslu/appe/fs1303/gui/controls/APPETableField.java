package ch.hslu.appe.fs1303.gui.controls;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.datasource.iTableDescriptor;

public class APPETableField<T> extends APPEControl<List<T>, Table> {

	private iTableDescriptor<T> fTableDescriptor;

	public APPETableField(Composite parent, FormToolkit toolkit) {
		this(parent, toolkit, null, SWT.None);
	}
	
	private APPETableField(Composite parent, FormToolkit toolkit,
			String labelText, int style) {
		super(parent, toolkit, labelText, style);
	}

	@Override
	public Table createControl(Composite parent, FormToolkit toolkit, int style) {
		Table table = toolkit.createTable(parent, style);
		table.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		table.setHeaderVisible(true);
		return table;
	}
	
	public void addMouseListener(MouseListener listener) {
		fControl.addMouseListener(listener);
	}
	
	public T getSelectedItem() {
		if (fControl.getSelectionIndex() >= 0) {
			return fTableDescriptor.getItem(fControl.getSelectionIndex());
		}
		
		return null;
	}
	
	@Override
	public void updateFromModel() {
		super.updateFromModel();

		fControl.getParent().getParent().getParent().layout();
	}
	
	@Override
	public void addModifyListener(ModifyListener listener) {
		
	}

	@Override
	public void removeModifyListener(ModifyListener listener) {
		
	}

	@Override
	public String getControlValue() {
		return "";
	}

	@Override
	public Class<?> getFieldClass() {
		return List.class;
	}

	@Override
	public void setControlValue(String value) {	}

	@Override
	public List<T> getValueForModel(String value) {	throw new UnsupportedOperationException("This should not be called. EVER!"); }

	@Override
	public String getDisplayValue(List<T> value) {
		if (fTableDescriptor != null) {
			fTableDescriptor.createTableRows(fControl, value);
		}
				
		return "";
	}

	public iTableDescriptor<T> getTableDescriptor() {
		return fTableDescriptor;
	}

	public void setTableDescriptor(iTableDescriptor<T> tableDescriptor) {
		fTableDescriptor = tableDescriptor;
		fTableDescriptor.createColumns(fControl);
	}
	
	@Override
	public void setEditable(boolean editable) {
	
	}
}
