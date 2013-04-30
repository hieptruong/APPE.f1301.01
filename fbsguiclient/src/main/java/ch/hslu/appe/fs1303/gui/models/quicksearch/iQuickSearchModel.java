package ch.hslu.appe.fs1303.gui.models.quicksearch;

import java.util.List;

public interface iQuickSearchModel<T> {
	public List<T> search(String filter);
}
