package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;

import com.google.inject.Inject;

import ch.hslu.appe.fs1301.business.shared.iPersonAPI;
import ch.hslu.appe.fs1301.business.shared.iProductAPI;
import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1301.business.shared.dto.DTOProdukt;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;
import ch.hslu.appe.fs1303.gui.labelprovider.ProductLabelProvider;
import ch.hslu.appe.fs1303.gui.views.iView;
import ch.hslu.appe.fs1303.gui.views.iViewListener;

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
		
		fProduct = fProductApi.getProductById(Integer.parseInt(getViewSite().getSecondaryId()));
		setPartName(new ProductLabelProvider().getText(fProduct));
		fView.bindModel(fProduct);
		fView.setActionListener(new iProductViewListener() {
			
			@Override
			public void onSave() {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void setFocus() {
		
	}

}
