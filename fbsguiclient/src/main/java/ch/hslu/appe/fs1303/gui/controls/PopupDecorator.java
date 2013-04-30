/* ******************************************************************************
 * Copyright (c) 2010 Comerge
 * All rights reserved. 
 * ******************************************************************************/
package ch.hslu.appe.fs1303.gui.controls;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.util.Geometry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

/**
 * A lightweight popup that is shown below a given parent.
 * 
 * @author Thomas Bomatter
 */
public abstract class PopupDecorator {

	private static final int DROP_DOWN_MIN_HIGHT= 150;
	private static final int DROP_DOWN_MAX_HIGHT= 300;
	private static final int DROP_DOWN_MAX_WIDTH= 500;
	private static final int DROP_DOWN_MIN_WIDTH= 50;

	private final Control fParent;
	private boolean fDropDownShown;
	private Shell fDropDownShell;
	private final boolean fExpandToParentWidth;

	public PopupDecorator(Control parent, boolean expandToParentWidth) {
		fParent= parent;
		fExpandToParentWidth= expandToParentWidth;
	}

	/**
	 * Show this popup.
	 * 
	 * @param takeFocus
	 */
	public void show(boolean takeFocus) {
		if (fDropDownShown)
			return;

		fDropDownShown= true;

		fDropDownShell= new Shell(fParent.getShell(), SWT.NONE);

		GridLayout layout= new GridLayout(1, false);
		layout.marginHeight= 0;
		layout.marginWidth= 0;
		fDropDownShell.setLayout(layout);

		Composite composite= new Composite(fDropDownShell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout gridLayout= new GridLayout(1, false);
		gridLayout.marginHeight= 0;
		gridLayout.marginWidth= 0;
		composite.setLayout(gridLayout);

		createContent(composite);

		setShellBounds(fDropDownShell);

		fDropDownShell.setVisible(true);
		if (takeFocus) {
			fDropDownShell.setFocus();
		}
		installCloser(fDropDownShell);
	}

	/**
	 * Is the current popup shown to the user?
	 * 
	 * @return true if it is shown
	 */
	public boolean isShown() {
		return !fDropDownShell.isDisposed();
	}

	/**
	 * Close the popup. This disposes the content.
	 */
	public void close() {
		fDropDownShell.close();
	}

	/**
	 * Set the focus into this popup.
	 */
	public void focus() {
		fDropDownShell.setFocus();
	}

	/**
	 * Resize this popup such that it has the optimal size to show its content.
	 */
	public void resize() {
		resizeShell(fDropDownShell);
	}

	/**
	 * Create the content of this popup and add the content to the given parent.
	 * 
	 * @param parent
	 *            the parent of the content
	 */
	protected abstract void createContent(Composite parent);

	protected void onClose() {
	}

	private void installCloser(final Shell shell) {
		final Listener focusListener= new Listener() {
			@Override
			public void handleEvent(Event event) {
				Widget focusElement= event.widget;
				boolean isFocusWidgetShell= focusElement == shell;
				boolean isFocusWidgetParentShell= focusElement instanceof Control && ((Control) focusElement).getShell() == shell;

				switch (event.type) {
				case SWT.FocusIn:
					if (!isFocusWidgetShell && !isFocusWidgetParentShell) {
						shell.close();
					}
					break;

				case SWT.FocusOut:
					if (event.display.getActiveShell() == null) {
						shell.close();
					}
					break;

				default:
					Assert.isTrue(false);
				}
			}
		};

		final Listener mouseListener= new Listener() {
			@Override
			public void handleEvent(Event event) {
				Rectangle popupBounds= shell.getBounds();
				Point position= shell.toDisplay(event.x, event.y);
				if (!popupBounds.contains(position.x, position.y)) {
					shell.close();
				}
			}
		};

		final Display display= shell.getDisplay();
		display.addFilter(SWT.FocusIn, focusListener);
		display.addFilter(SWT.FocusOut, focusListener);
		display.addFilter(SWT.MouseUp, mouseListener);

		final ControlListener controlListener= new ControlListener() {
			@Override
			public void controlMoved(ControlEvent e) {
				shell.close();
			}

			@Override
			public void controlResized(ControlEvent e) {
				shell.close();
			}
		};
		fParent.getShell().addControlListener(controlListener);

		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				display.removeFilter(SWT.FocusIn, focusListener);
				display.removeFilter(SWT.FocusOut, focusListener);
				display.removeFilter(SWT.MouseUp, mouseListener);

				if (!fParent.isDisposed()) {
					fParent.getShell().removeControlListener(controlListener);
				}
			}
		});
		shell.addShellListener(new ShellListener() {
			@Override
			public void shellActivated(ShellEvent e) {
			}

			@Override
			public void shellClosed(ShellEvent e) {
				if (!fDropDownShown)
					return;

				fDropDownShown= false;
				onClose();
			}

			@Override
			public void shellDeactivated(ShellEvent e) {
			}

			@Override
			public void shellDeiconified(ShellEvent e) {
			}

			@Override
			public void shellIconified(ShellEvent e) {
			}
		});
	}

	/**
	 * Calculates a useful size for the given shell.
	 * 
	 * @param shell
	 *            the shell to calculate the size for.
	 */
	private void setShellBounds(Shell shell) {
		Rectangle rect= fParent.getBounds();

		shell.pack();
		Point size= shell.getSize();
		int height= Math.max(Math.min(size.y, DROP_DOWN_MAX_HIGHT), DROP_DOWN_MIN_HIGHT);
		int preferedWidth;
		if (fExpandToParentWidth) {
			preferedWidth= Math.max(size.x, rect.width);
		} else {
			preferedWidth= size.x;
		}
		int width= Math.max(Math.min(preferedWidth, DROP_DOWN_MAX_WIDTH), DROP_DOWN_MIN_WIDTH);

		Point pt= fParent.getParent().toDisplay(new Point(rect.x, rect.y + rect.height));

		Rectangle monitor= getClosestMonitor(shell.getDisplay(), pt).getClientArea();
		int overlap= (pt.x + width) - (monitor.x + monitor.width);
		if (overlap > 0)
			pt.x-= overlap;
		if (pt.x < monitor.x)
			pt.x= monitor.x;

		shell.setLocation(pt);
		shell.setSize(width, height);
	}

	/**
	 * Set the size of the given shell such that more content can be shown. The shell size does not
	 * exceed {@link #DROP_DOWN_HIGHT} and {@link #DROP_DOWN_WIDTH}.
	 * 
	 * @param shell
	 *            the shell to resize
	 */
	private void resizeShell(final Shell shell) {
		Point size= shell.getSize();
		int currentWidth= size.x;
		int currentHeight= size.y;

		if (currentHeight >= DROP_DOWN_MAX_HIGHT && currentWidth >= DROP_DOWN_MAX_WIDTH)
			return;

		Point preferedSize= shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);

		int newWidth;
		if (currentWidth >= DROP_DOWN_MAX_WIDTH) {
			newWidth= currentWidth;
		} else {
			newWidth= Math.min(Math.max(preferedSize.x, currentWidth), DROP_DOWN_MAX_WIDTH);
		}
		int newHeight;
		if (currentHeight >= DROP_DOWN_MAX_HIGHT) {
			newHeight= currentHeight;
		} else {
			newHeight= Math.min(Math.max(preferedSize.y, currentHeight), DROP_DOWN_MAX_HIGHT);
		}

		if (newHeight != currentHeight || newWidth != currentWidth) {
			shell.setRedraw(false);
			try {
				shell.setSize(newWidth, newHeight);
			} finally {
				shell.setRedraw(true);
			}
		}
	}

	/**
	 * Returns the monitor whose client area contains the given point. If no monitor contains the
	 * point, returns the monitor that is closest to the point.
	 * <p>
	 * Copied from <code>org.eclipse.jface.window.Window.getClosestMonitor(Display, Point)</code>
	 * </p>
	 * 
	 * @param display
	 *            the display showing the monitors
	 * @param point
	 *            point to find (display coordinates)
	 * @return the monitor closest to the given point
	 */
	private static Monitor getClosestMonitor(Display display, Point point) {
		int closest= Integer.MAX_VALUE;

		Monitor[] monitors= display.getMonitors();
		Monitor result= monitors[0];

		for (int i= 0; i < monitors.length; i++) {
			Monitor current= monitors[i];

			Rectangle clientArea= current.getClientArea();

			if (clientArea.contains(point))
				return current;

			int distance= Geometry.distanceSquared(Geometry.centerPoint(clientArea), point);
			if (distance < closest) {
				closest= distance;
				result= current;
			}
		}

		return result;
	}

}
