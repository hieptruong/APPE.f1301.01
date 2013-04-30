package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1301.business.shared.UserRole;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1303.gui.controls.APPECheckBoxField;
import ch.hslu.appe.fs1303.gui.controls.APPEComboBoxField;
import ch.hslu.appe.fs1303.gui.controls.APPEDateField;
import ch.hslu.appe.fs1303.gui.controls.APPEIntField;
import ch.hslu.appe.fs1303.gui.controls.APPEStringField;
import ch.hslu.appe.fs1303.gui.controls.APPETableField;
import ch.hslu.appe.fs1303.gui.datasource.ComboDataSource;
import ch.hslu.appe.fs1303.gui.datasource.OrderTableDescriptor;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.models.PersonEditorModel;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonView;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonViewListener;

public class PersonView extends AbstractBaseView<PersonEditorModel, iPersonViewListener> implements iPersonView {
	
	private APPEIntField fUserId;
	private APPEStringField fLastName;
	private APPEStringField fFirstName;
	private APPEStringField fStreet ;
	private APPEIntField fPLZ;
	private APPEDateField fBirthday;
	private APPEStringField fCity;
	private APPEStringField fEmail;
	private APPEStringField fUserName;
	private APPEStringField fPassword;
	private APPETableField<DTOBestellung> fOrderTable;
	private Button fNewOrderButton;
	private APPECheckBoxField fActive;
	private APPEComboBoxField fRole;
	private APPETableField<DTOBestellung> fCreatedOrdersTable;	

	@Override
	public void createPageContent(Composite parent, FormToolkit toolkit) {
	    Composite generalSection = createSection(fForm.getBody(), toolkit, "Allgemeine Informationen");	    
	    
	    fUserId = new APPEIntField(generalSection, toolkit, "ID: ", SWT.READ_ONLY);
	    register(fUserId);
	   	   	    
	    fFirstName = new APPEStringField(generalSection, toolkit, "Vorname: ", SWT.None);
	    fFirstName.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen Vornamen an.");
	    register(fFirstName);
	    
	    fLastName = new APPEStringField(generalSection, toolkit, "Name: ", SWT.None);
	    fLastName.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen Namen an");
	    register(fLastName);
	    
	    fStreet = new APPEStringField(generalSection, toolkit, "Strasse: ", SWT.None);
	    fStreet.addValidationMessage(fForm.getMessageManager(), "Geben Sie eine Strasse an.");
	    register(fStreet);
	    
	    fPLZ = new APPEIntField(generalSection, toolkit, "PLZ: ", SWT.None);
	    fPLZ.addValidationMessage(fForm.getMessageManager(), "Geben Sie eine gueltige Postleitzahl an.");
	    register(fPLZ);
	    
	    fCity = new APPEStringField(generalSection, toolkit, "Ort: ", SWT.None);
	    fCity.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen Ort an.");
	    register(fCity);
	    
	    fBirthday = new APPEDateField(generalSection, toolkit, "Geburtstag: ", SWT.None);
	    fBirthday.addValidationMessage(fForm.getMessageManager(), "Geben Sie ein gueltiges Geburtsdatum an.");
	    register(fBirthday);
	    
	    fEmail = new APPEStringField(generalSection, toolkit, "Email: ", SWT.None);
	    fEmail.addValidationMessage(fForm.getMessageManager(), "Geben sie eine Email an.");
	    register(fEmail);
	    
	    fUserName = new APPEStringField(generalSection, toolkit, "Benutzername: ", SWT.None);
	    fUserName.setNullable(true);
	    register(fUserName);
	    
	    fPassword = new APPEStringField(generalSection, toolkit, "Passwort: ", SWT.PASSWORD);
	    fPassword.setNullable(true);
	    register(fPassword);
	    
	    fRole = new APPEComboBoxField(generalSection, toolkit, "Rolle: ", SWT.None);
	    fRole.setDataSource(ComboDataSource.getDataSourceForClass(UserRole.class));
	    register(fRole);
	    
	    fActive = new APPECheckBoxField(generalSection, toolkit, "Aktive: ", SWT.None);
	    register(fActive);
	    
	    Composite orderSection = createSection(fForm.getBody(), toolkit, "Bestellungen");	 
	    
	    fOrderTable = new APPETableField<DTOBestellung>(orderSection, toolkit);
	    fOrderTable.setTableDescriptor(new OrderTableDescriptor());
	    fOrderTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {	}
			
			@Override
			public void mouseDown(MouseEvent e) { }
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				DTOBestellung selectedItem = fOrderTable.getSelectedItem();
				if (selectedItem != null) {
					fListener.openOrder(selectedItem.getId());
				}
			}
		});
	    register(fOrderTable);
	    
	    fNewOrderButton = toolkit.createButton(orderSection, "Bestellung erfassen", 0);
	    fNewOrderButton.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
	    fNewOrderButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {				
				fListener.onNewOrderButtonClick();
			}
		});	    
	    
	    Composite createdOrdersComposite = createSection(fForm.getBody(), toolkit, "Erstellte Bestellungen");	 
	    
	    fCreatedOrdersTable = new APPETableField<DTOBestellung>(createdOrdersComposite, toolkit);
	    fCreatedOrdersTable.setTableDescriptor(new OrderTableDescriptor());
	    fCreatedOrdersTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent e) {	}
			
			@Override
			public void mouseDown(MouseEvent e) { }
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				DTOBestellung selectedItem = fCreatedOrdersTable.getSelectedItem();
				if (selectedItem != null) {
					fListener.openOrder(selectedItem.getId());
				}
			}
		});
	    register(fCreatedOrdersTable);	    
	}
	
	@Override
	public void bindModel(PersonEditorModel model) {
		fModel = model;
		
		fForm.setText(new PersonLabelProvider().getText(model.getPerson()));
		
		fUserId.bindModel(model.getPerson(), "fId");
		fLastName.bindModel(model.getPerson(), "fName");
		fFirstName.bindModel(model.getPerson(), "fVorname");
		fStreet.bindModel(model.getPerson(), "fStrasse");
		fPLZ.bindModel(model.getPerson(), "fPlz");
		fBirthday.bindModel(model.getPerson(), "fGeburtstag");
		fCity.bindModel(model.getPerson(), "fOrt");
		fEmail.bindModel(model.getPerson(), "fEMail");
		fUserName.bindModel(model.getPerson(), "fBenutzername");
		fPassword.bindModel(model.getPerson(), "fPasswort");
		fRole.bindModel(model.getPerson(), "fRolle");
		fActive.bindModel(model.getPerson(), "fAktiv");
		
		fOrderTable.bindModel(model, "fOrders");
		fCreatedOrdersTable.bindModel(model, "fCreatedOrders");
	}
}
