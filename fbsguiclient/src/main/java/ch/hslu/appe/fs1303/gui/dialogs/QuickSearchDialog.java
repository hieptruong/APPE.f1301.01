package ch.hslu.appe.fs1303.gui.dialogs;

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;

import ch.hslu.appe.fs1303.gui.APPEActivator;

public abstract class QuickSearchDialog<T> extends FilteredItemsSelectionDialog {

	private iQuickSearchCallback<T> fCallback;
	private Label fMessageLabel;
	private LabelProvider fLabelProvider;

	public interface iQuickSearchCallback<T> {
		List<T> resultForSearchString(String input);		
	}

	public QuickSearchDialog(Shell shell, boolean multi, iQuickSearchCallback<T> callback) {
		super(shell, multi);
		// TODO Auto-generated constructor stub
		fCallback = callback;
		fLabelProvider = getLabelProvider();
		setDetailsLabelProvider(fLabelProvider);
		setListLabelProvider(fLabelProvider);
	}
	
	public abstract LabelProvider getLabelProvider();
	
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
		for (T person : fCallback.resultForSearchString(filter.getPattern())) {
			monitor.worked(1);
			contentProvider.add(person, filter);
		} 
		
		monitor.done();
	}
	
	public abstract String getId();
	
	@Override
	protected IDialogSettings getDialogSettings() {
		IDialogSettings settings= APPEActivator.getDefault().getDialogSettings().getSection(getId());

		if (settings == null) {
			settings= APPEActivator.getDefault().getDialogSettings().addNewSection(getId());
		}

		return settings;
	}

	@Override
	public String getElementName(Object arg0) {
		return fLabelProvider.getText(arg0);
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
