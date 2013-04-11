package ch.hslu.appe.fs1303.gui.perspectives;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import ch.hslu.appe.fs1303.gui.presenter.PersonSearchPresenter;

public class APPEPerspective implements IPerspectiveFactory {

	public static final String ID= "ch.hslu.appe.fs1303.gui.perspectives.APPEPerspective";
	
	public void createInitialLayout(IPageLayout layout) {
		
		String editorArea= layout.getEditorArea();
		IFolderLayout leftViews= layout.createFolder("left.view.folder", IPageLayout.LEFT, 0.27f, editorArea); //$NON-NLS-1$
		leftViews.addView(PersonSearchPresenter.ID);
		
	}
}
