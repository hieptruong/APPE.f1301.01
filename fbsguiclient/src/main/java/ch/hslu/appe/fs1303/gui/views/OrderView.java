package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1303.gui.controls.APPEDateTimeField;
import ch.hslu.appe.fs1303.gui.controls.APPEIntField;
import ch.hslu.appe.fs1303.gui.presenter.OrderPresenter.iOrderView;
import ch.hslu.appe.fs1303.gui.presenter.OrderPresenter.iOrderViewListener;

public class OrderView extends AbstractBaseView<DTOBestellung, iOrderViewListener> implements iOrderView  {

	private APPEIntField fId;
	private APPEDateTimeField fOrderDate;
	private APPEDateTimeField fDeliveryDateShould;
	private APPEDateTimeField fDeliveryDateIs;

	@Override
	public void createPageContent(Composite parent, FormToolkit toolkit) {
		Section section = toolkit.createSection(fForm.getBody(), Section.DESCRIPTION
	            | Section.TITLE_BAR);
		section.setText("Allgemeine Informationen");
		Composite client = toolkit.createComposite(section, SWT.FILL);
    
	    GridLayout gl_container = new GridLayout(2, false);
	    gl_container.marginRight = 5;
	    gl_container.marginLeft = 5;
	    client.setLayout(gl_container);
	    
	    fId = new APPEIntField(client, toolkit, "ID: ", SWT.READ_ONLY);
	    register(fId);
	    
	    fOrderDate = new APPEDateTimeField(client, toolkit, "Bestelldatum: ", SWT.READ_ONLY);
	    register(fOrderDate);
	    
	    fDeliveryDateShould = new APPEDateTimeField(client, toolkit, "Lieferdatum SOLL: ", SWT.READ_ONLY);
	    register(fDeliveryDateShould);
	    
	    fDeliveryDateIs = new APPEDateTimeField(client, toolkit, "Lieferdatum IST: ", SWT.READ_ONLY);
	    register(fDeliveryDateIs);
	    
	    section.setClient(client);
	}
	
	@Override
	public void bindModel(DTOBestellung model) {
		fId.bindModel(model, "fId");
		fOrderDate.bindModel(model, "fBestelldatum");
		fDeliveryDateShould.bindModel(model, "fLiefertermin_Soll");
		fDeliveryDateIs.bindModel(model, "fLiefertermin_Ist");
	}
}
