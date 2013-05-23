package ch.hslu.appe.fs1303.gui.presenter;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.iStockAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1301.business.shared.dto.DTOZentrallagerBestellung;
import ch.hslu.appe.fs1303.gui.models.ZentralLagerEditorModel;
import ch.hslu.appe.fs1303.gui.models.ZentralLagerWithProductModel;
import ch.hslu.appe.fs1303.gui.utils.ErrorUtils;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;

import com.google.inject.Inject;

public class ZentralLagerPresenter extends BasePresenter<ZentralLagerEditorModel> {
	
	public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.ZentralLagerPresenter";
	
	public interface iZentralLagerView extends iView<ZentralLagerEditorModel, iZentralLagerViewListener> {
	}
	
	public interface iZentralLagerViewListener extends iViewListener {
		public void confirmOrder(ZentralLagerWithProductModel selectedItem);
	}
		
	@Inject
	private iStockAPI fStockApi;
	
	@Inject
	private iProductAPI fProductApi;
	
	@Inject
	private iZentralLagerView fView;
	
	@Override
	public ZentralLagerEditorModel loadModel() {
		List<ZentralLagerWithProductModel> orderList = new ArrayList<ZentralLagerWithProductModel>();		
		
		try {
			List<DTOZentrallagerBestellung> openOrdes = fStockApi.getOpenOrders();
			
			for (DTOZentrallagerBestellung dtoZentrallagerBestellung : openOrdes) {
				DTOProdukt product;
				product = fProductApi.getProductById(dtoZentrallagerBestellung.getProdukt());
				orderList.add(new ZentralLagerWithProductModel(dtoZentrallagerBestellung, product));
			}
		} catch (AccessDeniedException e1) {
			ErrorUtils.handleAccessDenied(getViewSite().getShell());
			return null;
		}
		return new ZentralLagerEditorModel(orderList);
	}

	@Override
	public void createControls(Composite composite) {
		fView.createContent(composite);
		
		setPartName("ZentralLager");
		
		fView.setActionListener(new iZentralLagerViewListener() {
			
			@Override
			public void reloadModel() {
				loadAndBindModel();
			}
			
			@Override
			public void onSave() {
			}
			
			@Override
			public void confirmOrder(ZentralLagerWithProductModel selectedItem) {
				try {
					fStockApi.confirmOrderReceivedFromStock(selectedItem.getBestellung().getId());
					loadAndBindModel();
				} catch (AccessDeniedException e) {
					ErrorUtils.handleAccessDenied(getViewSite().getShell());
				}
			}
		});
		
	}

	@Override
	public void bindModel(ZentralLagerEditorModel model) {
		fView.bindModel(model);
	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub
		
	}

}
