package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1301.business.shared.AccessDeniedException;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.labelprovider.ProductLabelProvider;
import ch.hslu.appe.fs1303.gui.utils.ErrorUtils;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;

import com.google.inject.Inject;

public class ProductPresenter extends BasePresenter {

	public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.ProductPresenter";
	
	public interface iProductView extends iView<DTOProdukt, iProductViewListener> {
		
	}	
	public interface iProductViewListener extends iViewListener {
	}
	
	@Inject
	private iProductView fView;
	
	@Inject
	private iProductAPI fProductApi;

	private DTOProdukt fProduct;
	
	public ProductPresenter() {
		super();
	}
	
	@Override
	public void createPartControl(Composite composite) {				
		fView.createContent(composite);
		
		String modelId = getViewSite().getSecondaryId();
		if (modelId.startsWith("new")) {
			fProduct = new DTOProdukt();
		} else {
			try {
				fProduct = fProductApi.getProductById(Integer.parseInt(getViewSite().getSecondaryId()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				return;
			} catch (AccessDeniedException e) {
				ErrorUtils.handleAccessDenied(getSite().getShell());
				return;
			}
		}
		
		setPartName(new ProductLabelProvider().getText(fProduct));
		fView.bindModel(fProduct);
		fView.setActionListener(new iProductViewListener() {
			
			@Override
			public void onSave() {
				try {
					fProduct = fProductApi.saveProduct(fProduct);				
					fView.bindModel(fProduct);
					setPartName(new ProductLabelProvider().getText(fProduct));
				} catch (AccessDeniedException e) {
					ErrorUtils.handleAccessDenied(getSite().getShell());
				}
			}
		});
	}
	
	@Override
	public void setFocus() {
		
	}

}
