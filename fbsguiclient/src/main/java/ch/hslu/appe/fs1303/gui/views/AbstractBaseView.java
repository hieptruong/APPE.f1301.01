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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import ch.hslu.appe.fs1303.gui.APPEActivator;
import ch.hslu.appe.fs1303.gui.controls.APPEControl;

public abstract class AbstractBaseView<T, H extends iViewListener> implements iView<T, H> {

	protected ScrolledForm fForm;
	protected Action fSaveAction;
	protected Action fRefreshAction;
	protected H fListener;
	protected T fModel;
	protected boolean fEditable;
	
	List<APPEControl<?,?>> fControls = new ArrayList<APPEControl<?,?>>();

	public AbstractBaseView() {
		fEditable = true;
	}
	
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
		
		fRefreshAction = new Action("Refresh", IAction.AS_PUSH_BUTTON) {
			
			@Override
			public void run() {
				fListener.reloadModel();
			}
			
			@Override
			public ImageDescriptor getImageDescriptor() {
				return APPEActivator.getImageDescriptor("/icons/refresh.png");
			}
		};
		
		fillToolbar();
		
		GridLayout layout = new GridLayout(1, true);	
		layout.marginRight = 5;
		layout.marginLeft = 5;
		
		fForm.getBody().setLayout(layout);
		fForm.getBody().setLayoutData(GridData.FILL_HORIZONTAL);
		
		createPageContent(parent, toolkit);
	}
	
	public void fillToolbar() {
		fForm.getToolBarManager().removeAll();
		
		if (fEditable) {
			fForm.getToolBarManager().add(fSaveAction);
		}		
		fForm.getToolBarManager().add(fRefreshAction);
		
		fForm.getToolBarManager().update(true);
	}
	
	public abstract void createPageContent(Composite parent, FormToolkit toolkit);
	
	public Composite createSection(Composite parent, FormToolkit toolkit, String title) {
		Section section = toolkit.createSection(parent, Section.DESCRIPTION | Section.TITLE_BAR);
	    section.setText(title);
	    section.setLayout(new GridLayout(1, true));
	    section.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    Composite client = toolkit.createComposite(section, SWT.FILL);
	    
	    GridLayout gl_container = new GridLayout(2, false);
	    gl_container.marginRight = 5;
	    gl_container.marginLeft = 5;
	    gl_container.marginBottom = 5;
	    gl_container.marginTop = 5;
	    client.setLayout(gl_container);
	    
	    section.setClient(client);
	    return client;
	}
	
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
		fSaveAction.setEnabled(message == null && fEditable);
		fForm.getToolBarManager().update(true);
		
		return message == null;
	}	
	


	@Override
	public void setEditable(boolean editable) {
		fEditable = editable;
		
		fillToolbar();
		
		for (APPEControl<?, ?> control : fControls) {
			control.setEditable(editable);
		}
	}
}
