package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonView;
import ch.hslu.appe.fs1303.gui.presenter.PersonPresenter.iPersonViewActions;

public class PersonView implements iPersonView {
	
	private DTOPerson fPerson;
	
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

	private iPersonViewActions fActionListener;

	@Override
	public void createContent(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());		
		FillLayout fillLayout = new FillLayout();
		fillLayout.type = SWT.VERTICAL;
		parent.setLayout(fillLayout);
		
	    Section section = toolkit.createSection(parent, Section.DESCRIPTION
	            | Section.TITLE_BAR);
	    section.setText("Allgemeine Informationen");
	    //section.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
	    Composite client = toolkit.createComposite(section, SWT.FILL);
	    
	    GridLayout gl_container = new GridLayout(2, false);
	    gl_container.marginRight = 5;
	    gl_container.marginLeft = 5;
	    client.setLayout(gl_container);
	    fUserId = createLabelAndText(toolkit, client, "ID:");
	    fLastName = createLabelAndText(toolkit, client, "Name:");
	    fFirstName = createLabelAndText(toolkit, client, "Vorname:");
	    fStreet = createLabelAndText(toolkit, client, "Strasse:");
	    fPLZ = createLabelAndText(toolkit, client, "PLZ:");
	    fCity = createLabelAndText(toolkit, client, "Ort:");
	    fBirthday = createLabelAndText(toolkit, client, "Geburtstag:");
	    fEmail = createLabelAndText(toolkit, client, "Email:");
	    fUserName = createLabelAndText(toolkit, client, "Benutzername:");
	    fPassword = createLabelAndText(toolkit, client, "Password:");
	    section.setClient(client);
	    
	    Section orderSection = toolkit.createSection(parent, Section.DESCRIPTION
	            | Section.TITLE_BAR);
	    orderSection.setText("Bestellungen");
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
	    Label label = toolkit.createLabel(parent, labelText);
	    label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));

	    Text text = toolkit.createText(parent, null);
	    text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	    return text;
	}

	@Override
	public void updateFromDTO(DTOPerson person) {
		fPerson = person;
		fUserId.setText(String.valueOf(person.getId()));
		fLastName.setText(person.getName());
		fFirstName.setText(person.getVorname());
		fStreet.setText(person.getStrasse());
		fPLZ.setText(String.valueOf(person.getPlz()));
		fCity.setText(person.getOrt());
		fBirthday.setText(person.getGeburtstag().toString());
		fEmail.setText(person.getEMail());
		fUserName.setText(person.getBenutzername());
		fPassword.setText(person.getPasswort());
	}

	@Override
	public void addActionListener(iPersonViewActions actionListener) {
		fActionListener = actionListener;

	}
}
