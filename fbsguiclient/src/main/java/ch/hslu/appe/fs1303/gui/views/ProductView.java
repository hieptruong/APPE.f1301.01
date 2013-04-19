package ch.hslu.appe.fs1303.gui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.APPEActivator;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonView;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonViewListener;
import ch.hslu.appe.fs1303.gui.presenter.ProductPresenter.iProductView;
import ch.hslu.appe.fs1303.gui.presenter.ProductPresenter.iProductViewListener;
import ch.hslu.appe.fs1303.gui.validators.APPEValidator;

public class ProductView implements iProductView {
	
	private DTOProdukt fModel;
	
	private Text fProductId;
	private Text fBezeichnung;
	private Text fLagerbestand;
	private Text fMinimalMenge;
	private Text fPreis;

	private iProductViewListener fActionListener;

	private ScrolledForm fForm;

	private Action fSaveAction;

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
				updateModel();
				fActionListener.onSave();
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
		
	    Section section = toolkit.createSection(fForm.getBody(), Section.DESCRIPTION
	            | Section.TITLE_BAR);
	    section.setText("Allgemeine Informationen");
	    Composite client = toolkit.createComposite(section, SWT.FILL);
	    
	    GridLayout gl_container = new GridLayout(2, false);
	    gl_container.marginRight = 5;
	    gl_container.marginLeft = 5;
	    client.setLayout(gl_container);
	    fProductId = createLabelAndText(toolkit, client, "ID:", SWT.READ_ONLY);
	    fBezeichnung = createLabelAndText(toolkit, client, "Bezeichnung");
	    fLagerbestand = createLabelAndText(toolkit, client, "Lagerbestand");
	    fMinimalMenge = createLabelAndText(toolkit, client, "Minimale Bestellmenge");
	    fPreis = createLabelAndText(toolkit, client, "Preis");
	    
	    
	    section.setClient(client);
	}
	
	public Text createLabelAndText(FormToolkit toolkit, Composite parent, String labelText) {
	    return createLabelAndText(toolkit, parent, labelText, SWT.None);
	}

	public Text createLabelAndText(FormToolkit toolkit, Composite parent, String labelText, int style) {
		Label label = toolkit.createLabel(parent, labelText);
	    label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

	    Text text = toolkit.createText(parent, null, style);
	    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    text.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				validate();
			}
		});
	    return text;
	}
	
	@Override
	public void updateFromModel() {
		if (fModel != null) {
			fForm.setText(String.format("%d: %s", fModel.getId(), fModel.getBezeichnung()));
			fProductId.setText(String.valueOf(fModel.getId()));
			fBezeichnung.setText(String.valueOf(fModel.getBezeichnung()));
		    fLagerbestand.setText(String.valueOf(fModel.getLagerbestand()));
		    fMinimalMenge.setText(String.valueOf(fModel.getMinimalMenge()));
		    fPreis.setText(String.valueOf(fModel.getPreis()));
		}
	}
	
	@Override
	public void setActionListener(iProductViewListener actionListener) {
		fActionListener = actionListener;
	}
	
	@Override
	public boolean validate() {
		fForm.getMessageManager().removeAllMessages();
		
		return fForm.getMessage() == null;
	}

	@Override
	public void bindModel(DTOProdukt product) {
		fModel = product;
		updateFromModel();
	}

	@Override
	public void updateModel() {
		if (fModel != null) {
			fModel.setBezeichnung(fBezeichnung.getText());
		}
	}
}
