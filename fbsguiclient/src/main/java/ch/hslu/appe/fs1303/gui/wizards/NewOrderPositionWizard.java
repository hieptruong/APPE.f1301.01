package ch.hslu.appe.fs1303.gui.wizards;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.controls.APPECurrencyField;
import ch.hslu.appe.fs1303.gui.controls.APPEDTOField;
import ch.hslu.appe.fs1303.gui.controls.APPEDTOField.iDTOModelUpdatedListener;
import ch.hslu.appe.fs1303.gui.controls.APPEIntField;
import ch.hslu.appe.fs1303.gui.models.NewOrderPositionWizardModel;

public class NewOrderPositionWizard extends Wizard {
	
	public class OrderPositionWizardPage extends APPEWizardPage<NewOrderPositionWizardModel> {

		private APPEDTOField<DTOProdukt> fProductField;
		private APPECurrencyField fPreisField;
		private APPEIntField fAnzahlField;

		public OrderPositionWizardPage() {
			setTitle("Produktposition hinzufuegen");
			setDescription("Waehlen Sie ein Produkt und die Anzahl!");
		}
		
		@Override
		public void createPageContent(Composite parent, FormToolkit toolkit) {
			
			fProductField = new APPEDTOField<DTOProdukt>(parent, toolkit, "Produkt: ", DTOProdukt.class, SWT.None);	
			fProductField.setModelUpdatedListener(new iDTOModelUpdatedListener<DTOProdukt>() {

				@Override
				public void updated(DTOProdukt element) {
					fModel.getPosition().setStueckpreis(element.getPreis());
					fPreisField.updateFromModel();
				}
			});
			register(fProductField);
			
			fPreisField = new APPECurrencyField(parent, toolkit, "Stueckpreis: ", SWT.None);
			register(fPreisField);
			
			fAnzahlField = new APPEIntField(parent, toolkit, "Anzahl: ", SWT.None);
			register(fAnzahlField);			
		}
		
		@Override
		public void bindModel(NewOrderPositionWizardModel model) {
			fProductField.bindModel(model, "fProdukt");
			fPreisField.bindModel(model.getPosition(), "fStueckpreis");
			fAnzahlField.bindModel(model.getPosition(), "fAnzahl");
			
		}
		
	}
	
	private OrderPositionWizardPage fOrderPositionWizardPage;
	private NewOrderPositionWizardModel fModel;
	
	@Override
	public void addPages() {
		fModel = new NewOrderPositionWizardModel(new DTOBestellposition(), null);
		
		fOrderPositionWizardPage = new OrderPositionWizardPage();
		fOrderPositionWizardPage.setModel(fModel);
		addPage(fOrderPositionWizardPage);
		super.addPages();	
		
	}
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
	}
	
	@Override
	public boolean performFinish() {
		fModel.getPosition().setProdukt(fModel.getProdukt().getId());
		return true;
	}

	public NewOrderPositionWizardModel getModel() {
		return fModel;
	}

	public void setModel(NewOrderPositionWizardModel model) {
		fModel = model;
	}
}
