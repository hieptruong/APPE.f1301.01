package ch.hslu.appe.fs1303.gui.dialogs;

import java.util.Comparator;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Shell;

import ch.hslu.appe.fs1301.business.shared.dto.DTOPerson;
import ch.hslu.appe.fs1303.gui.labelprovider.PersonLabelProvider;

public class PersonSearchDialog extends QuickSearchDialog<DTOPerson> {

	public static final String ID = "ch.hslu.appe.fs1303.gui.dialogs.PersonSearchDialog";
	
	public PersonSearchDialog(Shell shell, boolean multi,
			iQuickSearchCallback<DTOPerson> callback) {
		super(shell, multi, callback);
	}

	@Override
	public LabelProvider getLabelProvider() {		
		return new PersonLabelProvider();
	}

	@Override
	public String getId() {
		return ID;
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
}
