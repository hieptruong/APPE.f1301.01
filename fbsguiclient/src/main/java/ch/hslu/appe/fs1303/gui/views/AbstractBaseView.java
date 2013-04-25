package ch.hslu.appe.fs1303.gui.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;

import ch.hslu.appe.fs1303.gui.APPEActivator;
import ch.hslu.appe.fs1303.gui.controls.APPEControl;

public abstract class AbstractBaseView<T, H extends iViewListener> implements iView<T, H> {

	protected ScrolledForm fForm;
	protected Action fSaveAction;
	protected H fListener;
	protected T fModel;
	
	List<APPEControl<?,?>> fControls = new ArrayList<APPEControl<?,?>>();

	@Override
	public void createContent(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());		
		parent.setLayout(new FillLayout(SWT.VERTICAL));	
		fForm = toolkit.createScrolledForm(parent);
		toolkit.decorateFormHeading(fForm.getForm());
		fForm.getForm().addMessageHyperlinkListener(new HyperlinkAdapter());
		
		fSaveAction = new Action("Save", IAction.AS_PUSH_BUTTON) {
			@Override
			public void run() {
				if (fListener != null) {
					fListener.onSave();
				}
			}
			
			@Override
			public ImageDescriptor getImageDescriptor() {
				return APPEActivator.getImageDescriptor("/icons/save24x24.png");
			}
		};
		
		fForm.getToolBarManager().add(fSaveAction);
		fForm.getToolBarManager().update(true);
		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		fillLayout.marginHeight = 5;
		fillLayout.marginWidth = 5;
		fForm.getBody().setLayout(fillLayout);
		
		createPageContent(parent, toolkit);
	}
	
	public abstract void createPageContent(Composite parent, FormToolkit toolkit);
	
	public void register(APPEControl<?,?> control) {
		fControls.add(control);
		control.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				validate();
			}
		});
	}
 	
	@Override
	public void updateFromModel() {
		for (APPEControl<?, ?> control : fControls) {
			control.updateFromModel();
		}
	}
	
	@Override
	public void setActionListener(H listener) {
		fListener = listener;
	}
	
	@Override
	public boolean validate() {
		
		String message = fForm.getMessage();
		fSaveAction.setEnabled(message == null);
		fForm.getToolBarManager().update(true);
		
		return message == null;
	}	
}
