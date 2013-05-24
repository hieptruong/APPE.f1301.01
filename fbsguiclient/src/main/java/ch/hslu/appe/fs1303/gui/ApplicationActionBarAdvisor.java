package ch.hslu.appe.fs1303.gui;

import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import ch.hslu.appe.fs1303.gui.actions.NewPersonAction;
import ch.hslu.appe.fs1303.gui.actions.NewProductAction;
import ch.hslu.appe.fs1303.gui.actions.SearchPersonAction;
import ch.hslu.appe.fs1303.gui.actions.SearchProductAction;
import ch.hslu.appe.fs1303.gui.actions.ZentralLagerAction;

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
	private NewPersonAction fNewPersonAction;
	private NewProductAction fNewProductAction;
	private ZentralLagerAction fZentralLagerAction;

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
		
		fNewPersonAction = new NewPersonAction();
		register(fNewPersonAction);
		
		fNewProductAction = new NewProductAction();
		register(fNewProductAction);
		
		fZentralLagerAction = new ZentralLagerAction();
		register(fZentralLagerAction);
	}
	
	@Override
	protected void fillMenuBar(IMenuManager menuBar) {
		// TODO Auto-generated method stub
		//super.fillMenuBar(menuBar);
		
		MenuManager fileMenu = new MenuManager("File");
		
		MenuManager newMenu = new MenuManager("Neu");
		newMenu.add(fNewPersonAction);
		newMenu.add(fNewProductAction);
		
		fileMenu.add(newMenu);
		fileMenu.add(fSearchPersonAction);
		fileMenu.add(fSearchProduktAction);
		fileMenu.add(fZentralLagerAction);
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
