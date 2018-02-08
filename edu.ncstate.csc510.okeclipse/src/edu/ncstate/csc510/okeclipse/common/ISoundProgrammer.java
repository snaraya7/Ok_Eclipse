package edu.ncstate.csc510.okeclipse.common;

/**
 * 
 * @author ncshr
 *
 */
public interface ISoundProgrammer {
	
	public String generateMainMethod();
	
	public String generateGetterSetter(String javaSourceCode);
	
	public String implementInterface(String javaSourceCode, String variableName);

}
