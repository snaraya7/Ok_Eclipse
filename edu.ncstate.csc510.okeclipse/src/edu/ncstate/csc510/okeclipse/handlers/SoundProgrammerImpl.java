package edu.ncstate.csc510.okeclipse.handlers;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import edu.ncstate.csc510.okeclipse.common.ISoundProgrammer;
import edu.ncstate.csc510.okeclipse.util.Util;

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
	public void insertContent(String soCodeContent, int position) throws BadLocationException {

		IDocument doc = Util.getCurrentEditorContent();

		doc.replace(position, 0, soCodeContent);

	}

	@Override
	public void injectCode(String javaClass) {

		System.out.println("injecting code for "+javaClass);
		
	}

}
