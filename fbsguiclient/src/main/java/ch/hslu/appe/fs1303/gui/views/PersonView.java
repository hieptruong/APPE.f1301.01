package ch.hslu.appe.fs1303.gui.views;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.eclipse.jface.dialogs.IMessageProvider;
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
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonView;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonViewListener;
import ch.hslu.appe.fs1303.gui.validators.APPEValidator;

public class PersonView implements iPersonView {
	
	private DTOPerson fModel;
	
	private Text fUserId;
	private Text fLastName;
	private Text fFirstName;
	private Text fStreet ;
	private Text fPLZ;
	private Text fBirthday;
	private Text fCity;
	private Text fEmail;
	private Text fUserName;
	private Text fPassword;
	private Table fOrderTable;
	private Button fNewOrderButton;
	private TableViewer fTableViewer;

	private iPersonViewListener fActionListener;

	private ScrolledForm fForm;

	@Override
	public void createContent(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());		
		parent.setLayout(new FillLayout(SWT.VERTICAL));	
		fForm = toolkit.createScrolledForm(parent);
		toolkit.decorateFormHeading(fForm.getForm());
		fForm.getForm().addMessageHyperlinkListener(new HyperlinkAdapter());
		
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
	    fUserId = createLabelAndText(toolkit, client, "ID:", SWT.READ_ONLY);
	    fLastName = createLabelAndText(toolkit, client, "Name:");
	    fFirstName = createLabelAndText(toolkit, client, "Vorname:");
	    fStreet = createLabelAndText(toolkit, client, "Strasse:");
	    fPLZ = createLabelAndText(toolkit, client, "PLZ:");
	    fCity = createLabelAndText(toolkit, client, "Ort:");
	    fBirthday = createLabelAndText(toolkit, client, "Geburtstag:");
	    fEmail = createLabelAndText(toolkit, client, "Email:");
	    fUserName = createLabelAndText(toolkit, client, "Benutzername:");
	    fPassword = createLabelAndText(toolkit, client, "Password:", SWT.PASSWORD);
	    section.setClient(client);
	    
	    Section orderSection = toolkit.createSection(fForm.getBody(), Section.DESCRIPTION
	            | Section.TITLE_BAR);
	    orderSection.setText("Bestellungen");
	    orderSection.marginHeight = 10;
	    //orderSection.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
	    Composite orderComposite = toolkit.createComposite(orderSection, SWT.FILL);
	    orderComposite.setLayout(gl_container);
	    
	    fOrderTable = toolkit.createTable(orderComposite, 0);
	    fOrderTable.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	    fOrderTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) {
			}
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				if (fOrderTable.getSelectionCount() > 0) {
					fActionListener.openOrder(((DTOBestellung)fOrderTable.getSelection()[0].getData()).getId());
				}
			}
		});
	    fNewOrderButton = toolkit.createButton(orderComposite, "Bestellung erfassen", 0);
	    fNewOrderButton.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
	    fNewOrderButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {				
				fActionListener.onNewOrderButtonClick();
			}
		});
	    
	    fTableViewer = new TableViewer(fOrderTable);
	    
	    orderSection.setClient(orderComposite);
	    
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
			fForm.setText(String.format("%d: %s %s", fModel.getId(), fModel.getName(), fModel.getVorname()));
			fUserId.setText(String.valueOf(fModel.getId()));
			fLastName.setText(fModel.getName());
			fFirstName.setText(fModel.getVorname());
			fStreet.setText(fModel.getStrasse());
			fPLZ.setText(String.valueOf(fModel.getPlz()));
			fCity.setText(fModel.getOrt());
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			fBirthday.setText(df.format(fModel.getGeburtstag()));
			fEmail.setText(fModel.getEMail());
			fUserName.setText(fModel.getBenutzername());
			fPassword.setText(fModel.getPasswort());
		}
	}
	
	@Override
	public void setActionListener(iPersonViewListener actionListener) {
		fActionListener = actionListener;
	}
	
	@Override
	public boolean validate() {
		fForm.getMessageManager().removeAllMessages();

		if (!APPEValidator.validateNotNull(fLastName)) {
			fForm.getMessageManager().addMessage("fLastName", "Geben Sie einen Namen an.", null, IMessageProvider.ERROR);
		}
		if (!APPEValidator.validateNotNull(fFirstName)) {
			fForm.getMessageManager().addMessage("fFirstName", "Geben Sie einen Vornamen an.", null, IMessageProvider.ERROR);
		}
		if (!APPEValidator.validateNotNull(fStreet)) {
			fForm.getMessageManager().addMessage("fStreet", "Geben Sie eine Strasse an.", null, IMessageProvider.ERROR);
		}
		if (!APPEValidator.valideInt(fPLZ)) {			
			fForm.getMessageManager().addMessage("fPLZ", "Geben Sie eine gueltige Postleitzahl an.", null, IMessageProvider.ERROR);
		} 
		if (!APPEValidator.validateNotNull(fCity)) {
			fForm.getMessageManager().addMessage("fCity", "Geben Sie einen Ort an.", null, IMessageProvider.ERROR);
		}
		if (!APPEValidator.valideDate(fBirthday, false)) {
			fForm.getMessageManager().addMessage("fBirthday", "Geben Sie ein gueltiges Geburtsdatum an.", null, IMessageProvider.ERROR);
		}
		if (!APPEValidator.validateNotNull(fEmail)) {
			fForm.getMessageManager().addMessage("fEmail", "Geben sie eine Email an.", null, IMessageProvider.ERROR);
		}
		
		return fForm.getMessage() == null;
	}

	@Override
	public void bindModel(DTOPerson person) {
		fModel = person;
		updateFromModel();
	}
}
