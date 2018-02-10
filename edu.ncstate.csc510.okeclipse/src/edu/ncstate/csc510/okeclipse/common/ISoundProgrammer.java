package edu.ncstate.csc510.okeclipse.common;

import org.eclipse.jface.text.BadLocationException;

/**
 * 
 * @author ncshr
 *
 */
public interface ISoundProgrammer {
	
	public String generateMainMethod();
	
	public String generateGetterSetter(String javaSourceCode);
	
	public String implementInterface(String javaSourceCode, String variableName);
	 
	public String generateSort(String variable);
	
	public void insertContent(String content) throws BadLocationException;

}
