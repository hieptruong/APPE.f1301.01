package ch.hslu.appe.fs1303.gui.controls;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.isA;
import static org.fest.assertions.Assertions.assertThat;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import ch.hslu.appe.fs1303.gui.controls.APPEDTOField.ContentTableAssistPopup;
import ch.hslu.appe.fs1303.gui.controls.APPEDTOField.IContentAssistPresenter;
import ch.hslu.appe.fs1303.gui.controls.APPEDTOField.iDTOModelUpdatedListener;
import ch.hslu.appe.fs1303.gui.models.quicksearch.iQuickSearchModel;
import ch.hslu.appe.fs1303.gui.utils.DTOUtils;

@RunWith(PowerMockRunner.class)
@SuppressStaticInitializationFor( {"ch.hslu.appe.fs1303.gui.utils.DTOUtils",
								   "org.eclipse.swt.widgets.Canvas"})
public class DTOFieldTest extends FieldTestBase<APPEDTOField<Integer>, Text, Integer>{

	private iQuickSearchModel<Integer> fQuickSearchModel;
	private Capture<ModifyListener> fCaptureModifyListner;

	@SuppressWarnings("unchecked")
	protected Text doMockCreateControl() throws Exception {
		PowerMock.mockStatic(DTOUtils.class);
		fQuickSearchModel = PowerMock.createMock(iQuickSearchModel.class);
		expect(DTOUtils.getQuickSearchModel(Integer.class)).andReturn(fQuickSearchModel);
		Text text = mockCreateControlForTextBox();
		
		fCaptureModifyListner = new Capture<ModifyListener>();
		text.addModifyListener(EasyMock.capture(fCaptureModifyListner));
		
		text.addKeyListener(isA(KeyListener.class));
		text.addTraverseListener(isA(TraverseListener.class));
		return text;
	}
	
	@Test
	public void TestBaseFieldClass() {
		RunBaseClassTest(APPETextControl.class);
	}
	
	@Test
	public void TestGetFieldClass() {
		RunFieldClassTest(Integer.class);
	}
	
	@Test
	public void ReturnsNull_OnGetDisplayValue_WhenValueIsNull() {
		assertThat(fTestee.getDisplayValue(null)).isEmpty();
	}
	
	@Test
	public void AsksLabelProviderForText_OnGetDisplayValue_WhenValueIsNotNull() {
		final Integer ExpectedValue = 12;
		
		PowerMock.reset(DTOUtils.class);
		LabelProvider labelProviderMock = PowerMock.createMock(LabelProvider.class); 
		expect(DTOUtils.getLabelProvider(isA(Integer.class))).andReturn(labelProviderMock);
		expect(labelProviderMock.getText(ExpectedValue)).andReturn("AText");
		PowerMock.replayAll();
		
		assertThat(fTestee.getDisplayValue(ExpectedValue)).isNotNull();
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void ModelUpdateListenerCanBeSet() {
		iDTOModelUpdatedListener listner = PowerMock.createMock(iDTOModelUpdatedListener.class);
		fTestee.setModelUpdatedListener(listner);
		
		assertThat(fTestee.getModelUpdatedListener()).isSameAs(listner);
	}
	
	@Test
	public void ReturnsSelectedElement_OnGetValueForModel() {
		final int ExpectedValue = 11234;
		
		Whitebox.setInternalState(fTestee, "fSelectedElement", ExpectedValue);
		
		assertThat(fTestee.getValueForModel("")).isEqualTo(ExpectedValue);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void UpdatesListener_OnGetValueFOrModel() {
		final int ExpectedValue = 11234;
		
		iDTOModelUpdatedListener listner = PowerMock.createMock(iDTOModelUpdatedListener.class);
		fTestee.setModelUpdatedListener(listner);
		listner.updated(ExpectedValue);
		Whitebox.setInternalState(fTestee, "fSelectedElement", ExpectedValue);
		
		assertThat(fTestee.getValueForModel("")).isEqualTo(ExpectedValue);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void SetsItemOnSelectItem() {
		final int ExpectedValue = 234;
		fTestee = PowerMock.createPartialMock(APPEDTOField.class, "updateModel", "updateFromModel");
		fTestee.selectItem(ExpectedValue);
		
		assertThat(fTestee.getValueForModel("")).isEqualTo(ExpectedValue);
	}
	
	@Test
	public void ClosesPopup_OnPressingEsc_WhenItWasOpen() {
		KeyEvent event = PowerMock.createMock(KeyEvent.class);
		event.character = SWT.ESC;
		
		Shell shell = PowerMock.createMock(Shell.class);
		IContentAssistPresenter popup = (IContentAssistPresenter)Whitebox.getInternalState(fTestee, "fDropDownPopup");
		Whitebox.setInternalState(popup, "fDropDownShell", shell);
		
		expect(shell.isDisposed()).andReturn(false);
		shell.close();
		PowerMock.replayAll();
		
		fTestee.handleKeyRelease(event);
	}

	@Test
	public void DoesNothing_OnPressingEsc_WhenPopupWasNotOpen() {
		KeyEvent event = PowerMock.createMock(KeyEvent.class);
		event.character = SWT.ESC;
		
		Shell shell = PowerMock.createMock(Shell.class);
		IContentAssistPresenter popup = (IContentAssistPresenter)Whitebox.getInternalState(fTestee, "fDropDownPopup");
		Whitebox.setInternalState(popup, "fDropDownShell", shell);
		
		expect(shell.isDisposed()).andReturn(true);
		PowerMock.replayAll();
		
		fTestee.handleKeyRelease(event);
	}

	@Test
	public void DoesNothing_OnPressingAnyKeyExceptESCOrDown() {
		KeyEvent event = PowerMock.createMock(KeyEvent.class);
		event.character = SWT.DEL;
		PowerMock.replayAll();
		
		fTestee.handleKeyRelease(event);
	}
	
	@Test
	public void DoesNothing_OnPressingEsc_WhenPopupWasNotCreated() {
		KeyEvent event = PowerMock.createMock(KeyEvent.class);
		event.character = SWT.ESC;
		
		Shell shell = PowerMock.createMock(Shell.class);
		IContentAssistPresenter popup = (IContentAssistPresenter)Whitebox.getInternalState(fTestee, "fDropDownPopup");
		Whitebox.setInternalState(popup, "fDropDownShell", shell);
		
		expect(shell.isDisposed()).andReturn(true);
		PowerMock.replayAll();
		
		fTestee.handleKeyRelease(event);
	}
	
	@Test
	public void DoesNothing_OnPressingDown_WhenFieldIsReadOnly() {
		KeyEvent event = PowerMock.createMock(KeyEvent.class);
		event.keyCode = SWT.ARROW_DOWN;
		event.stateMask = SWT.MODIFIER_MASK;
		PowerMock.replayAll();
		
		fTestee.handleKeyRelease(event);
	}
	
	@Test
	public void FocusesPopup_OnPressingDown_WhenPopupWasOpen() {
		KeyEvent event = PowerMock.createMock(KeyEvent.class);
		event.keyCode = SWT.ARROW_DOWN;
		
		Shell shell = PowerMock.createMock(Shell.class);
		IContentAssistPresenter popup = (IContentAssistPresenter)Whitebox.getInternalState(fTestee, "fDropDownPopup");
		Whitebox.setInternalState(popup, "fDropDownShell", shell);
		TableViewer viewer = PowerMock.createMock(TableViewer.class);
		Whitebox.setInternalState(popup, "fViewer", viewer);
		
		expect(shell.isDisposed()).andReturn(false);
		expect(shell.setFocus()).andReturn(true);
		Table table = PowerMock.createMock(Table.class);
		expect(viewer.getTable()).andReturn(table);
		table.setSelection(0);
		PowerMock.replayAll();
		
		fTestee.handleKeyRelease(event);
	}
	
	@Test
	public void ShowsPopup_OnPressingDown_WhenPopupWasNotOpen() {
		KeyEvent event = PowerMock.createMock(KeyEvent.class);
		event.keyCode = SWT.ARROW_DOWN;
		
		IContentAssistPresenter popup = PowerMock.createMock(IContentAssistPresenter.class);
		Whitebox.setInternalState(fTestee, "fDropDownPopup", popup);
		expect(popup.isShown()).andReturn(false);
		popup.show(true);
		
		PowerMock.replayAll();
		
		fTestee.handleKeyRelease(event);
	}
	
	@Test
	public void UpdatesFilter_OnModifyText_WhenNotUpdatingFromModel(){
		ModifyListener listener = fCaptureModifyListner.getValue();
		IContentAssistPresenter popup = PowerMock.createMock(IContentAssistPresenter.class);
		Whitebox.setInternalState(fTestee, "fDropDownPopup", popup);
		
		popup.show(false);
		popup.updateFilter(isA(String.class));
		PowerMock.reset(fControl);
		expect(fControl.getText()).andReturn("");
		PowerMock.replayAll();
		
		listener.modifyText(null);
	}

	@Test
	public void DoesNothing_OnModifyText_WhenUpdatingFromModel(){
		ModifyListener listener = fCaptureModifyListner.getValue();
		IContentAssistPresenter popup = PowerMock.createMock(IContentAssistPresenter.class);
		Whitebox.setInternalState(fTestee, "fDropDownPopup", popup);
		Whitebox.setInternalState(fTestee, "fUpdatingFromModel", true);
		
		PowerMock.replayAll();
		
		listener.modifyText(null);
	}
}
