package edu.ncstate.csc510.okeclipse.common;

import java.io.IOException;

import org.eclipse.jface.text.BadLocationException;

/**
 * 
 * @author ncshr
 *
 */
public interface ISoundProgrammer {

	public void injectCode(String javaSourceCode) throws BadLocationException, IOException;

	public String generateMainMethod();

	public String generateGetterSetter(String javaSourceCode);

	public String implementInterface(String javaSourceCode, String variableName);

	public String generateSort(String variable);

	/**
	 * 
	 * @param content
	 *            - string to insert
	 * @param position
	 *            - index in the file to insert
	 * @throws BadLocationException
	 */
	public void insertContent(String content, int position) throws BadLocationException;

}
