package ch.hslu.appe.fs1303.gui.presenter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iOrderAPI;
import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellposition;
import ch.hslu.appe.fs1301.business.shared.dto.DTOBestellung;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.OrderLabelProvider;
import ch.hslu.appe.fs1303.gui.models.OrderEditorModel;
import ch.hslu.appe.fs1303.gui.utils.ArrayUtils;
import ch.hslu.appe.fs1303.gui.utils.ErrorUtils;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;

import com.google.inject.Inject;

public class OrderPresenter extends BasePresenter {

public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.OrderPresenter";
	
	public interface iOrderView extends iView<OrderEditorModel, iOrderViewListener> {
		public void setEditable(boolean editable);
	}
	
	public interface iOrderViewListener extends iViewListener {
		
	}
	
	@Inject
	private iOrderView fView;
	
	@Inject
	private iOrderAPI fOrderApi;
	
	@Inject
	private iPersonAPI fPersonApi;

	private OrderEditorModel fModel;
	
	@Override
	public void createPartControl(Composite composite) {
		fView.createContent(composite);
		String modelId = getViewSite().getSecondaryId();
		if (modelId.startsWith("new")) {
			fModel = new OrderEditorModel(new DTOBestellung(), new ArrayList<DTOBestellposition>(), null);
		} else {
			try {
				List<DTOBestellung> orders = fOrderApi.getOrders(Integer.parseInt(modelId));
				if (orders.size() > 0) {
					DTOBestellung bestellung = orders.get(0);
					DTOPerson person = fPersonApi.getCustomerById(bestellung.getPerson1());
					List<DTOBestellposition> bestellPositionen = fOrderApi.getOrderPositions(ArrayUtils.convertToIntArray(bestellung.getBestellpositions()));				
					
					fModel = new OrderEditorModel(bestellung, bestellPositionen, person);
					//fView.setEditable(false);
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
				
			}
		});
	}

	@Override
	public void setFocus() {
		
	}
}
