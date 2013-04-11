package ch.hslu.appe.fs1303.gui;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	// Actions - important to allocate these only in makeActions, and then use
	// them
	// in the fill methods. This ensures that the actions aren't recreated
	// when fillActionBars is called with FILL_PROXY.

	private IContributionItem fViewsShortList;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		// TODO Auto-generated method stub
		//super.makeActions(window);
		
		fViewsShortList= ContributionItemFactory.VIEWS_SHORTLIST.create(window);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		// TODO Auto-generated method stub
		//super.fillMenuBar(menuBar);
		
		MenuManager showViewsMenu= new MenuManager("Views");
		showViewsMenu.add(fViewsShortList);
		menuBar.add(showViewsMenu);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// TODO Auto-generated method stub
		super.fillCoolBar(coolBar);
	}
}
