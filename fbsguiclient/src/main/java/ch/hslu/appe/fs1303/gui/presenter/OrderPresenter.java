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

public class OrderPresenter extends BasePresenter<OrderEditorModel> {

public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.OrderPresenter";
	
	public interface iOrderView extends iView<OrderEditorModel, iOrderViewListener> {
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

	@Override
	public void setFocus() {
		
	}

	@Override
	public OrderEditorModel loadModel() {
		String modelId = getViewSite().getSecondaryId();
		if (modelId.startsWith("new")) {
			return new OrderEditorModel(new DTOBestellung(), new ArrayList<BestellpositionWithProduktModel>(), null);
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
					
					fView.setEditable(false);
					return new OrderEditorModel(bestellung, bestellPositionWithProdukt, person);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (AccessDeniedException e) {
				ErrorUtils.handleAccessDenied(getSite().getShell());
			}			
		}
		
		return null;
	}

	@Override
	public void createControls(Composite composite) {
		fView.createContent(composite);		
		
		fView.setActionListener(new iOrderViewListener() {
			
			@Override
			public void onSave() {
				try {
					List<DTOBestellposition> bestellpositions = new ArrayList<DTOBestellposition>();
					for (BestellpositionWithProduktModel position : getModel().getBestellposition()) {
						bestellpositions.add(position.getBestellposition());
					}
					DTOBestellung createdOrder = fOrderApi.createNewOrder(getModel().getPerson().getId(), 1, bestellpositions);
					if (createdOrder != null) {
						getModel().setBestellung(createdOrder);
						fView.bindModel(getModel());
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
					getModel().getBestellposition().add(new BestellpositionWithProduktModel(wizard.getModel().getPosition(), wizard.getModel().getProdukt()));
				}
				fView.updateFromModel();
			}

			@Override
			public void reloadModel() {
				loadAndBindModel();
			}
		});
	}

	@Override
	public void bindModel(OrderEditorModel model) {
		setPartName(new OrderLabelProvider().getText(model.getBestellung()));
		fView.bindModel(model);
	}
}
