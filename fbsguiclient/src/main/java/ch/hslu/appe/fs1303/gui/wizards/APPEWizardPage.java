package ch.hslu.appe.fs1303.gui.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.controls.APPEControl;

public abstract class APPEWizardPage<T> extends WizardPage {

	private List<APPEControl<?,?>> fControls = new ArrayList<APPEControl<?,?>>();
	protected T fModel;
	
	protected APPEWizardPage() {
		super("");
	}
	
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		parent.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		Composite pageComposite = toolkit.createComposite(parent);
		GridLayout layout = new GridLayout(2, false);
		pageComposite.setLayout(layout);
		createPageContent(pageComposite, toolkit);
		setControl(pageComposite);
	} 
	
	public abstract void createPageContent(Composite parent, FormToolkit toolkit);
	
	public abstract void bindModel(T model);
	
	public void setModel(T model) {
		fModel = model;
	}
	
	public void updateFromModel() {
		for (APPEControl<?, ?> control : fControls) {
			control.updateFromModel();
		}
	}
	
	public void register(APPEControl<?,?> control) {
		fControls.add(control);
		control.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				getWizard().getContainer().updateButtons();
			}
		});
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		
		if (visible) {
			bindModel(fModel);
			updateFromModel();
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean isPageComplete() {
		boolean completed = true;
		for (APPEControl control : fControls) {
			completed = completed && control.validate();
		}
		
		return completed;
	}
}
