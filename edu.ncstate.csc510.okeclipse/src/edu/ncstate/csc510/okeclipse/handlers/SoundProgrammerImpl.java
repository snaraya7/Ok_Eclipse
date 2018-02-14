package edu.ncstate.csc510.okeclipse.handlers;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import edu.ncstate.csc510.okeclipse.common.ISoundProgrammer;

public class SoundProgrammerImpl implements ISoundProgrammer {

	/**
	 * Just return main method string
	 */
	@Override
	public String generateMainMethod() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Refer getter_setter_requirement.txt in explanation package
	 */
	@Override
	public String generateGetterSetter(String javaSourceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Refer interface_requirement.txt in explanation package
	 */
	@Override
	public String implementInterface(String javaSourceCode, String interfaceCode) {
		// TODO Auto-generated method stub
		return null;
	}

	// Requirement..

	// input =
	//
	// Collections.sort(list, new Comparator<String>() {
	//
	// @Override
	// public int compare(String o1, String o2) {
	// return 0;
	// }
	//
	// });

	@Override
	public String generateSort(String variable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertContent(String content) throws BadLocationException {

		final IEditorPart editor = (ITextEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		IDocumentProvider dp = ((ITextEditor) editor).getDocumentProvider();
		IDocument doc = dp.getDocument(editor.getEditorInput());
		int offset = doc.getLineOffset(doc.getNumberOfLines() - doc.getNumberOfLines() > 5 ? 4 : 1);
		doc.replace(offset, 0, content);

	}

	@Override
	public void injectCode(String javaClass) {
		// TODO Auto-generated method stub
		
	}

}
