package ch.hslu.appe.fs1303.gui.presenter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iOrderAPI;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.OrderLabelProvider;
import ch.hslu.appe.fs1303.gui.models.BestellpositionWithProduktModel;
import ch.hslu.appe.fs1303.gui.models.OrderEditorModel;
import ch.hslu.appe.fs1303.gui.utils.ArrayUtils;
import ch.hslu.appe.fs1303.gui.utils.ErrorUtils;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;
import ch.hslu.appe.fs1303.gui.wizards.NewOrderPositionWizard;

import com.google.inject.Inject;

public class OrderPresenter extends BasePresenter {

public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.OrderPresenter";
	
	public interface iOrderView extends iView<OrderEditorModel, iOrderViewListener> {
		public void setEditable(boolean editable);
	}
	
	public interface iOrderViewListener extends iViewListener {
		public void addOrderPosition();
	}
	
	@Inject
	private iOrderView fView;
	
	@Inject
	private iOrderAPI fOrderApi;
	
	@Inject
	private iPersonAPI fPersonApi;
	
	@Inject
	private iProductAPI fProduktApi;
	
	private OrderEditorModel fModel;
	
	@Override
	public void createPartControl(Composite composite) {
		fView.createContent(composite);
		String modelId = getViewSite().getSecondaryId();
		if (modelId.startsWith("new")) {
			fModel = new OrderEditorModel(new DTOBestellung(), new ArrayList<BestellpositionWithProduktModel>(), null);
		} else {
			try {
				List<DTOBestellung> orders = fOrderApi.getOrders(Integer.parseInt(modelId));
				if (orders.size() > 0) {
					DTOBestellung bestellung = orders.get(0);
					DTOPerson person = fPersonApi.getCustomerById(bestellung.getPerson1());
					List<DTOBestellposition> bestellPositionen = fOrderApi.getOrderPositions(ArrayUtils.convertToIntArray(bestellung.getBestellpositions()));
					List<BestellpositionWithProduktModel> bestellPositionWithProdukt = new ArrayList<BestellpositionWithProduktModel>();
					for (DTOBestellposition dtoBestellposition : bestellPositionen) {
						BestellpositionWithProduktModel bPosition = new BestellpositionWithProduktModel(dtoBestellposition, fProduktApi.getProductById(dtoBestellposition.getProdukt()));
						bestellPositionWithProdukt.add(bPosition);
					}
					
					fModel = new OrderEditorModel(bestellung, bestellPositionWithProdukt, person);
					fView.setEditable(false);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			} catch (AccessDeniedException e) {
				ErrorUtils.handleAccessDenied(getSite().getShell());
				return;
			}			
		}
	
		setPartName(new OrderLabelProvider().getText(fModel.getBestellung()));
		
		fView.bindModel(fModel);
		fView.setActionListener(new iOrderViewListener() {
			
			@Override
			public void onSave() {
				try {
					
					List<DTOBestellposition> bestellpositions = new ArrayList<DTOBestellposition>();
					for (BestellpositionWithProduktModel position : fModel.getBestellposition()) {
						bestellpositions.add(position.getBestellposition());
					}
					DTOBestellung createdOrder = fOrderApi.createNewOrder(fModel.getPerson().getId(), 1, bestellpositions);
					if (createdOrder != null) {
						fModel.setBestellung(createdOrder);
						fView.bindModel(fModel);
					}
				} catch (AccessDeniedException e) {				
					ErrorUtils.handleAccessDenied(getSite().getShell());
				}
			}

			@Override
			public void addOrderPosition() {
				NewOrderPositionWizard wizard = new NewOrderPositionWizard();
				WizardDialog wDialog = new WizardDialog(getSite().getShell(), wizard);
				if (wDialog.open() == Window.OK) {
					fModel.getBestellposition().add(new BestellpositionWithProduktModel(wizard.getModel().getPosition(), wizard.getModel().getProdukt()));
				}
				fView.updateFromModel();
			}
		});
	}

	@Override
	public void setFocus() {
		
	}
}
