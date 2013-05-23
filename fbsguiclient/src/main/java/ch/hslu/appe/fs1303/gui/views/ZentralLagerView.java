package ch.hslu.appe.fs1303.gui.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.controls.APPETableField;
import ch.hslu.appe.fs1303.gui.datasource.ZentralLagerTableDescriptor;
import ch.hslu.appe.fs1303.gui.models.ZentralLagerEditorModel;
import ch.hslu.appe.fs1303.gui.models.ZentralLagerWithProductModel;
import ch.hslu.appe.fs1303.gui.presenter.ZentralLagerPresenter.iZentralLagerView;
import ch.hslu.appe.fs1303.gui.presenter.ZentralLagerPresenter.iZentralLagerViewListener;

public class ZentralLagerView extends AbstractBaseView<ZentralLagerEditorModel, iZentralLagerViewListener> implements iZentralLagerView {

	private APPETableField<ZentralLagerWithProductModel> fOpenOrdersTable;
	private Button fConfirmButton;

	@Override
	public void createPageContent(Composite parent, FormToolkit toolkit) {
		Composite section = createSection(fForm.getBody(), toolkit, "Offene Bestellungen");
		
		fOpenOrdersTable = new APPETableField<ZentralLagerWithProductModel>(section, toolkit);
		fOpenOrdersTable.setTableDescriptor(new ZentralLagerTableDescriptor());
	    register(fOpenOrdersTable);
	    
	    fConfirmButton = toolkit.createButton(section, "Eingang bestaetigen", 0);
	    fConfirmButton.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
	    fConfirmButton.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				ZentralLagerWithProductModel selectedItem = fOpenOrdersTable.getSelectedItem();
				if (selectedItem != null) {
					fListener.confirmOrder(selectedItem);					
				}
			}
		});	
	}

	@Override
	public void bindModel(ZentralLagerEditorModel model) {
		fOpenOrdersTable.bindModel(model, "fBestellungen");
	}

}
