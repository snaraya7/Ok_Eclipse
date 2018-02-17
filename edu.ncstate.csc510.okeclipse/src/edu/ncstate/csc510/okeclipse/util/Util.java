package edu.ncstate.csc510.okeclipse.util;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.IDocument;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import edu.ncstate.csc510.okeclipse.views.OkEclipseView;

/**
 * 
 * @author ncshr
 *
 */
public class Util {

	public static boolean isNullString(String content) {

		return content == null || content.trim().length() <= 0 || content.trim().toLowerCase().equals("null");
	}

	public static IDocument getCurrentEditorContent() {
		final IEditorPart editor = (ITextEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		IDocumentProvider dp = ((ITextEditor) editor).getDocumentProvider();
		IDocument doc = dp.getDocument(editor.getEditorInput());
		return doc;
	}

	public static void showOkEclipseView() {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(OkEclipseView.ID);
		} catch (PartInitException e) {
		}
	}

	public static void showError(Exception e, String message) {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog.openError(activeShell, "Ok Eclipse", message + e.getMessage());
			}
		});
	}
}
