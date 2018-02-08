package edu.ncstate.csc510.okeclipse.common;

import java.util.List;

/**
 * 
 * @author ncshr
 *
 */
public interface ILoudConsole {

	public List<String> extract(String consoleLog);
	
	public String getConsoleContent();
	
}
