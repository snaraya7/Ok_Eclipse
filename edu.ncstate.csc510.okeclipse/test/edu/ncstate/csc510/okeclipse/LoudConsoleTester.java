package edu.ncstate.csc510.okeclipse;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncstate.csc510.okeclipse.common.ILoudConsole;
import edu.ncstate.csc510.okeclipse.handlers.LoudConsoleImpl;

public class LoudConsoleTester {

	@Test
	public void testExtract() {
		
		ILoudConsole loudConsole = new LoudConsoleImpl();
		
		loudConsole.extract("");
		
		fail("Not yet implemented");
	}

}
