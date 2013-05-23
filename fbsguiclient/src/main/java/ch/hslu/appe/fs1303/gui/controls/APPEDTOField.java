package ch.hslu.appe.fs1303.gui.controls;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;

import ch.hslu.appe.fs1303.gui.models.quicksearch.iQuickSearchModel;
import ch.hslu.appe.fs1303.gui.utils.DTOUtils;
import ch.hslu.appe.fs1303.gui.validators.iValidator;

public class APPEDTOField<T> extends APPETextControl<T> {

	public static interface IContentAssistPresenter {

		public abstract void show(boolean takeFocus);

		public abstract void updateFilter(String filter);

		public abstract boolean isShown();
		
		void focus();

		public abstract void close();

	}
	
	protected final class ContentTableAssistPopup extends PopupDecorator implements IContentAssistPresenter {

		private String fFilter;
		private iQuickSearchModel<T> fSearchModel;
		private TableViewer fViewer;

		private ContentTableAssistPopup(Control parent, iQuickSearchModel<T> searchModel) {
			super(parent, true);
			fSearchModel = searchModel;
			fFilter= "";
		}

		@Override
		protected void createContent(Composite parent) {			
			fViewer= new TableViewer(parent, SWT.FULL_SELECTION);
			fViewer.setContentProvider(new IStructuredContentProvider() {

				@Override
				public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
				}

				@Override
				public void dispose() {
				}

				@Override
				public Object[] getElements(Object inputElement) {
					if (fSearchModel == null)
						return new Object[0];

					List<T> result= fSearchModel.search(fFilter);
					return result.toArray(new Object[result.size()]);
				}
			});
			
			fViewer.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					return DTOUtils.getLabelProvider(element).getText(element);
				}
			});			

			fViewer.getTable().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
			fViewer.setInput(new Object());
			fViewer.getTable().addKeyListener(new KeyListener() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.keyCode == SWT.CR || e.keyCode == SWT.KEYPAD_CR) {
						onOpen();
					}
				}

				@Override
				public void keyPressed(KeyEvent e) {
				}
			});

			fViewer.getTable().addMouseListener(new MouseListener() {
				@Override
				public void mouseUp(MouseEvent e) {
					if (e.button != 1)
						return;			
					
					if (fViewer.getTable().getSelectionCount() == 1) {
						onOpen();	
					}					
				}

				@Override
				public void mouseDown(MouseEvent e) {
				}

				@Override
				public void mouseDoubleClick(MouseEvent e) {
				}
			});
		}
		
		@SuppressWarnings("unchecked")
		private void onOpen() {
			ISelection selection= fViewer.getSelection();
			if (!(selection instanceof IStructuredSelection))
				return;

			Object element= ((IStructuredSelection) selection).getFirstElement();

			closeDropDown();
			selectItem((T) element);
		}
		
		protected void closeDropDown() {
			if (fDropDownPopup != null && fDropDownPopup.isShown()) {
				fDropDownPopup.close();
			}
		}
		
		@Override
		public void show(boolean takeFocus) {
			super.show(takeFocus);
		}

		@Override
		public void updateFilter(String filter) {
			fFilter= filter;
			fViewer.refresh();
		}

		@Override
		protected void onClose() {
			super.onClose();
		}
		
		public void selectFirst() {
			fViewer.getTable().setSelection(0);
		}
		
		@Override
		public void focus() {			
			selectFirst();
			super.focus();
		}
	}
	
	public interface iDTOModelUpdatedListener<T> {
		void updated(T element);
	}
	
	private iDTOModelUpdatedListener<T> fModelUpdatedListener;
	private IContentAssistPresenter fDropDownPopup;
	private T fSelectedElement;
	private Class<T> fClazz;
	
	@SuppressWarnings("unchecked")
	public APPEDTOField(Composite parent, FormToolkit toolkit,
			String labelText, Class<T> clazz, int style) {
		super(parent, toolkit, labelText, style);
		fClazz = clazz;
		fDropDownPopup = new ContentTableAssistPopup(fControl, DTOUtils.getQuickSearchModel(clazz));
		
		addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent e) {
				if (!fUpdatingFromModel) {
					fDropDownPopup.show(false);
					fDropDownPopup.updateFilter(fControl.getText());					
				}
			}
		});
		
		setValidator(new iValidator<T>() {
			
			@Override
			public boolean validate(String input, boolean isNullable) {
				if (fSelectedElement == null && isNullable) {
					return true;
				} else if (fSelectedElement == null) {
					return false;
				} else {
					return true;
				}
			}

			@Override
			public T getValueFor(String value) {
				// Dummy implementation
				return null;
			}
		});
		
		fControl.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent e) {
				handleKeyRelease(e);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.ARROW_DOWN) {
					if (fDropDownPopup.isShown()) {
						fDropDownPopup.focus();
					}
				}
			}
		});
		
		fControl.addTraverseListener(new TraverseListener() {
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.character == SWT.TAB) {
					if (fDropDownPopup.isShown()) {
						fDropDownPopup.focus();
					}
				}
			}
		});
	}

	protected void handleKeyRelease(KeyEvent e) {
		int keyCode= e.keyCode;
		if (keyCode == SWT.ARROW_DOWN) {
			if ((e.stateMask & SWT.MODIFIER_MASK) != 0)
				return;

			if (fDropDownPopup.isShown()) {
				fDropDownPopup.focus();
			} else {				
				fDropDownPopup.show(true);
			}
		} else if (e.character == SWT.ESC) {
			if (fDropDownPopup.isShown()) {
				fDropDownPopup.close();
			}
		} 
	}
	
	protected void selectItem(T element) {
		fSelectedElement = element;
		updateModel();	
		updateFromModel();
	}
	
	@Override
	public void addModifyListener(ModifyListener listener) {
		fControl.addModifyListener(listener);
	}
	
	public Class<?> getFieldClass() {	
		return fClazz;
	}

	@Override
	public T getValueForModel(String value) {	
		if (fModelUpdatedListener != null) {
			fModelUpdatedListener.updated(fSelectedElement);
		}
		return fSelectedElement;
	}

	@Override
	public String getDisplayValue(T value) {
		fSelectedElement = value;
		
		if (value == null) {
			return "";
		}
			
		return DTOUtils.getLabelProvider(value).getText(value);
	}

	public iDTOModelUpdatedListener<T> getModelUpdatedListener() {
		return fModelUpdatedListener;
	}

	public void setModelUpdatedListener(iDTOModelUpdatedListener<T> modelUpdatedListener) {
		fModelUpdatedListener = modelUpdatedListener;
	}
}
