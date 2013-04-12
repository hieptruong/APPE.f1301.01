package ch.hslu.appe.fs1303.gui.dialogs;

import java.util.Comparator;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;

public class PersonSearchDialog extends FilteredItemsSelectionDialog {

	private iPersonSearchCallback fCallback;
	private Label fMessageLabel;
	private PersonLabelProvider fPersonLabelProvider;

	public interface iPersonSearchCallback {
		List<DTOPerson> personsForSearchString(String input);
		boolean matchItem(DTOPerson item);
	}

	public PersonSearchDialog(Shell shell, boolean multi, iPersonSearchCallback callback) {
		super(shell, multi);
		// TODO Auto-generated constructor stub
		fCallback = callback;
		fPersonLabelProvider = new PersonLabelProvider();
		setDetailsLabelProvider(fPersonLabelProvider);
		setListLabelProvider(fPersonLabelProvider);
	}

	@Override
	protected Control createExtendedContentArea(Composite parent) {
		
		fMessageLabel= new Label(parent, SWT.NONE);
		GridData layoutData= new GridData(SWT.FILL, SWT.CENTER, true, false);
		layoutData.horizontalIndent= 5;
		fMessageLabel.setLayoutData(layoutData);
		return fMessageLabel;
	}

	@Override
	protected ItemsFilter createFilter() {
		return new ItemsFilter() {
			
			@Override
			public boolean matchItem(Object item) {
				return true;
			}
			
			@Override
			public boolean isConsistentItem(Object arg0) {
				return true;
			}
			
			@Override
			public boolean isSubFilter(ItemsFilter filter) {
				return false;
			}
		};
	}

	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider,
			ItemsFilter filter, IProgressMonitor monitor) throws CoreException {

		monitor.beginTask("", 100);
		for (DTOPerson person : fCallback.personsForSearchString(filter.getPattern())) {
			monitor.worked(1);
			contentProvider.add(person, filter);
		} 
		
		monitor.done();
	}

	
	@Override
	protected IDialogSettings getDialogSettings() {
		return new DialogSettings("PersonSearch");
	}

	@Override
	public String getElementName(Object arg0) {
		return fPersonLabelProvider.getText(arg0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected Comparator getItemsComparator() {
		return new Comparator<DTOPerson>() {
			@Override
			public int compare(DTOPerson o1, DTOPerson o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
	}

	@Override
	public void setMessage(String message) {
		super.setMessage(message);
		
		fMessageLabel.setText(message);
	}
	
	@Override
	protected IStatus validateItem(Object arg0) {
		return Status.OK_STATUS;
	}
}
