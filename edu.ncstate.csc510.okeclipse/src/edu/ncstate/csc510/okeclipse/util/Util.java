package edu.ncstate.csc510.okeclipse.util;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

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
}
