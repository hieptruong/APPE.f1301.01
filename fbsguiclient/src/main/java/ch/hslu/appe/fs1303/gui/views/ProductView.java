package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.controls.APPECurrencyField;
import ch.hslu.appe.fs1303.gui.controls.APPEIntField;
import ch.hslu.appe.fs1303.gui.controls.APPEStringField;
import ch.hslu.appe.fs1303.gui.labelprovider.ProductLabelProvider;
import ch.hslu.appe.fs1303.gui.presenter.ProductPresenter.iProductView;
import ch.hslu.appe.fs1303.gui.presenter.ProductPresenter.iProductViewListener;

public class ProductView extends AbstractBaseView<DTOProdukt, iProductViewListener> implements iProductView {
	
	private APPEIntField fProductId;
	private APPEStringField fBezeichnung;
	private APPEIntField fLagerbestand;
	private APPEIntField fMinimalMenge;
	private APPECurrencyField fPreis;

	
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
	    
	    fProductId = new APPEIntField(client, toolkit, "ID: ", SWT.READ_ONLY);
	    register(fProductId);
	    
	    fBezeichnung = new APPEStringField(client, toolkit, "Bezeichnung: ", SWT.None);
	    fBezeichnung.addValidationMessage(fForm.getMessageManager(), "Geben Sie eine Bezeichnung an.");
	    register(fBezeichnung);
	    
	    fLagerbestand = new APPEIntField(client, toolkit, "Lagerbestand: ", SWT.None);
	    fLagerbestand.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen Lagerbestand an.");
	    register(fLagerbestand);
	    
	    fMinimalMenge = new APPEIntField(client, toolkit, "Minimale Bestellmenge: ", SWT.None);
	    fMinimalMenge.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen minimalen Lagerbestand an.");
	    register(fMinimalMenge);
	    
	    fPreis = new APPECurrencyField(client, toolkit, "Preis: ", SWT.None);
	    fPreis.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen Preis an.");
	    register(fPreis);
	    	    
	    section.setClient(client);
	}
	
	@Override
	public void setActionListener(iProductViewListener actionListener) {
		fListener = actionListener;
	}

	@Override
	public void bindModel(DTOProdukt product) {
		fModel = product;
		
		fForm.setText(new ProductLabelProvider().getText(product));
		
		fProductId.bindModel(product, "fId");
		fBezeichnung.bindModel(product, "fBezeichnung");
		fLagerbestand.bindModel(product, "fLagerbestand");
		fMinimalMenge.bindModel(product, "fMinimalMenge");
		fPreis.bindModel(product, "fPreis");
	}
}
