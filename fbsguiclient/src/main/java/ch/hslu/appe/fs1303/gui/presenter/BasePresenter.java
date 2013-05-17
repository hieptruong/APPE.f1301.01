package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import ch.hslu.appe.fs1303.gui.GuiModule;

public abstract class BasePresenter<T> extends ViewPart {

	private T fModel;
	
	public BasePresenter() {
		GuiModule.injector.injectMembers(this);		
	}
	
	public void loadAndBindModel() {
		fModel = loadModel();
		bindModel(fModel);
	}
	public abstract T loadModel();
	
	@Override
	public void createPartControl(Composite arg0) {
		createControls(arg0);
		loadAndBindModel();
	}
	
	public abstract void createControls(Composite composite);
	public abstract void bindModel(T model);

	public T getModel() {
		return fModel;
	}

	public void setModel(T model) {
		fModel = model;
	}
}
