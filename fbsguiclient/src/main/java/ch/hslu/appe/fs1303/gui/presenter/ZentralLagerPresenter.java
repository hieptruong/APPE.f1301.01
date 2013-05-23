package ch.hslu.appe.fs1303.gui.presenter;

import org.eclipse.swt.widgets.Composite;

import ch.hslu.appe.fs1303.gui.models.ZentralLagerEditorModel;
import ch.hslu.appe.fs1303.gui.models.ZentralLagerWithProductModel;
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
	private iZentralLagerView fView;
	
	@Override
	public ZentralLagerEditorModel loadModel() {
		// TODO Auto-generated method stub
		return null;
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
				// TODO Auto-generated method stub
				
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
