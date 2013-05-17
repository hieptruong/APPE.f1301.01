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

public class ProductPresenter extends BasePresenter<DTOProdukt> {

	public static final String ID = "ch.hslu.appe.fs1303.gui.presenter.ProductPresenter";
	
	public interface iProductView extends iView<DTOProdukt, iProductViewListener> {
		
	}	
	public interface iProductViewListener extends iViewListener {
	}
	
	@Inject
	private iProductView fView;
	
	@Inject
	private iProductAPI fProductApi;
	
	public ProductPresenter() {
		super();
	}
	
	@Override
	public void setFocus() {
		
	}

	@Override
	public DTOProdukt loadModel() {
		String modelId = getViewSite().getSecondaryId();
		if (modelId.startsWith("new")) {
			return new DTOProdukt();
		} else {
			try {
				return fProductApi.getProductById(Integer.parseInt(getViewSite().getSecondaryId()));
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
		
		fView.setActionListener(new iProductViewListener() {
			
			@Override
			public void onSave() {
				try {
					setModel(fProductApi.saveProduct(getModel()));				
					fView.bindModel(getModel());
					setPartName(new ProductLabelProvider().getText(getModel()));
				} catch (AccessDeniedException e) {
					ErrorUtils.handleAccessDenied(getSite().getShell());
				}
			}

			@Override
			public void reloadModel() {
				loadAndBindModel();
			}
		});
	}

	@Override
	public void bindModel(DTOProdukt model) {
		setPartName(new ProductLabelProvider().getText(model));
		fView.bindModel(model);
	}

}
