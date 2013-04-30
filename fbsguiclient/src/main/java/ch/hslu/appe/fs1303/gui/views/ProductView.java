package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

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
		Composite generalSection = createSection(fForm.getBody(), toolkit, "Allgemeine Informationen");
	    
	    fProductId = new APPEIntField(generalSection, toolkit, "ID: ", SWT.READ_ONLY);
	    register(fProductId);
	    
	    fBezeichnung = new APPEStringField(generalSection, toolkit, "Bezeichnung: ", SWT.None);
	    fBezeichnung.addValidationMessage(fForm.getMessageManager(), "Geben Sie eine Bezeichnung an.");
	    register(fBezeichnung);
	    
	    fLagerbestand = new APPEIntField(generalSection, toolkit, "Lagerbestand: ", SWT.None);
	    fLagerbestand.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen Lagerbestand an.");
	    register(fLagerbestand);
	    
	    fMinimalMenge = new APPEIntField(generalSection, toolkit, "Minimale Bestellmenge: ", SWT.None);
	    fMinimalMenge.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen minimalen Lagerbestand an.");
	    register(fMinimalMenge);
	    
	    fPreis = new APPECurrencyField(generalSection, toolkit, "Preis: ", SWT.None);
	    fPreis.addValidationMessage(fForm.getMessageManager(), "Geben Sie einen Preis an.");
	    register(fPreis);
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
