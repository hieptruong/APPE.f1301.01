package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.controls.APPEDTOField;
import ch.hslu.appe.fs1303.gui.controls.APPEDateTimeField;
import ch.hslu.appe.fs1303.gui.controls.APPEIntField;
import ch.hslu.appe.fs1303.gui.controls.APPETableField;
import ch.hslu.appe.fs1303.gui.datasource.BestellpositionTableDescriptor;
import ch.hslu.appe.fs1303.gui.models.BestellpositionWithProduktModel;
import ch.hslu.appe.fs1303.gui.models.OrderEditorModel;
import ch.hslu.appe.fs1303.gui.presenter.OrderPresenter.iOrderView;
import ch.hslu.appe.fs1303.gui.presenter.OrderPresenter.iOrderViewListener;

public class OrderView extends AbstractBaseView<OrderEditorModel, iOrderViewListener> implements iOrderView  {

	private APPEIntField fId;
	private APPEDateTimeField fOrderDate;
	private APPEDateTimeField fDeliveryDateShould;
	private APPEDateTimeField fDeliveryDateIs;
	private APPETableField<BestellpositionWithProduktModel> fBestellpositionenTable;
	private APPEDTOField<DTOPerson> fPersonField;
	private Button fNewPositionButton;

	@Override
	public void createPageContent(Composite parent, FormToolkit toolkit) {
		
		Composite generalSection = createSection(fForm.getBody(), toolkit, "Allgemeine Informationen");
		
	    fId = new APPEIntField(generalSection, toolkit, "ID: ", SWT.READ_ONLY);
	    register(fId);
	    
	    fPersonField = new APPEDTOField<DTOPerson>(generalSection, toolkit, "Person: ", DTOPerson.class, SWT.None);
	    register(fPersonField);
	    
	    fOrderDate = new APPEDateTimeField(generalSection, toolkit, "Bestelldatum: ", SWT.READ_ONLY);	    
	    register(fOrderDate);
	    
	    fDeliveryDateShould = new APPEDateTimeField(generalSection, toolkit, "Lieferdatum SOLL: ", SWT.READ_ONLY);
	    register(fDeliveryDateShould);
	    
	    fDeliveryDateIs = new APPEDateTimeField(generalSection, toolkit, "Lieferdatum IST: ", SWT.READ_ONLY);
	    register(fDeliveryDateIs);	    
	    
		Composite bestellSection = createSection(fForm.getBody(), toolkit, "Bestellpositionen");
	    
	    fBestellpositionenTable = new APPETableField<BestellpositionWithProduktModel>(bestellSection, toolkit);
	    fBestellpositionenTable.setTableDescriptor(new BestellpositionTableDescriptor());
	    register(fBestellpositionenTable);
	    
	    fNewPositionButton = toolkit.createButton(bestellSection, "Position hinzufuegen", 0);
	    fNewPositionButton.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
	    fNewPositionButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {				
				fListener.addOrderPosition();
			}
		});	 
	}
	
	@Override
	public void setEditable(boolean editable) {
		super.setEditable(editable);
		
		fNewPositionButton.setEnabled(false);
	}
	
	@Override
	public void bindModel(OrderEditorModel model) {
		fId.bindModel(model.getBestellung(), "fId");
		fPersonField.bindModel(model, "fPerson");
		fOrderDate.bindModel(model.getBestellung(), "fBestelldatum");
		fDeliveryDateShould.bindModel(model.getBestellung(), "fLiefertermin_Soll");
		fDeliveryDateIs.bindModel(model.getBestellung(), "fLiefertermin_Ist");
		
		fBestellpositionenTable.bindModel(model, "fBestellposition");
	}
}
