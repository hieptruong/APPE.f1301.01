package ch.hslu.appe.fs1303.gui.editors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.editor.FormEditor;
import org.eclipse.ui.forms.editor.FormPage;
import org.eclipse.ui.forms.editor.SharedHeaderFormEditor;

public class PersonEditor extends SharedHeaderFormEditor {

	public class PersonDetailPage extends FormPage {

		public static final String ID = "ch.hslu.appe.fs1303.gui.editors.PersonDetailPage";
		
		public PersonDetailPage(FormEditor editor) {
			super(editor, ID, "Details");		
		}
		
		@Override
		protected void createFormContent(IManagedForm managedForm) {
			super.createFormContent(managedForm);
			
		}		
	}
	
	
	@Override
	protected void addPages() {
		try {
			PersonDetailPage page = new PersonDetailPage(this);
			int addPage = addPage(page);
			setPageText(addPage, page.getTitle());
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void doSave(IProgressMonitor arg0) {
		
	}

	@Override
	public void doSaveAs() {
		
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

}
