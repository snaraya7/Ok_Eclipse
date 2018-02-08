package edu.ncstate.csc510.okeclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class SampleHandler extends AbstractHandler {

	public String getCurrentEditorContent() throws BadLocationException {
		final IEditorPart editor = (ITextEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();

		IDocumentProvider dp = ((ITextEditor) editor).getDocumentProvider();
		IDocument doc = dp.getDocument(editor.getEditorInput());

		return new LoudConsoleImpl().getConsoleContent();

		// int offset = doc.getLineOffset(doc.getNumberOfLines() - 4);
		// doc.replace(offset, 0, "//shrikanth was here" + "\n");

	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		
		VoiceHandler handler = new VoiceHandler();
		
		handler.execute(event);
		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		try {
			MessageDialog.openInformation(window.getShell(), "Okeclipse", ""+getCurrentEditorContent());
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
//			e1.printStackTrace();
		}

//		try {
//			getCurrentEditorContent();
//		} catch (BadLocationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		return null;
	}
}
