package ch.hslu.appe.fs1303.gui;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import ch.hslu.appe.fs1303.gui.actions.SearchPersonAction;
import ch.hslu.appe.fs1303.gui.actions.SearchProductAction;

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

	private IWorkbenchAction fQuitAction;
	private SearchPersonAction fSearchPersonAction;
	private SearchProductAction fSearchProduktAction;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		
		fQuitAction= ActionFactory.QUIT.create(window);
		register(fQuitAction);
		
		fSearchPersonAction = new SearchPersonAction();
		register(fSearchPersonAction);
		
		fSearchProduktAction = new SearchProductAction();
		register(fSearchProduktAction);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		// TODO Auto-generated method stub
		//super.fillMenuBar(menuBar);
		
		MenuManager fileMenu = new MenuManager("File");
		fileMenu.add(fSearchPersonAction);
		fileMenu.add(fSearchProduktAction);
		fileMenu.add(new Separator());
		fileMenu.add(fQuitAction);
		menuBar.add(fileMenu);
		
		
		//MenuManager showViewsMenu= new MenuManager("Views");
		//showViewsMenu.add(fViewsShortList);
		//menuBar.add(showViewsMenu);
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar) {
		// TODO Auto-generated method stub
		super.fillCoolBar(coolBar);
	}
}
