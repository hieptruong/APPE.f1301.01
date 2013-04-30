package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.swt.widgets.Composite;

public interface iView<T, H extends iViewListener> {
	public void createContent(Composite parent);
	public void updateFromModel();
	public void bindModel(T model);
	public void setActionListener(H listener);
	public boolean validate();
	public void setEditable(boolean editable);
}
