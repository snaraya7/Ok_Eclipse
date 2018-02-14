package edu.ncstate.csc510.okeclipse.util;

/**
 * 
 * @author ncshr
 *
 */
public class Util {

	public static boolean isNullString(String content) {

		return content == null || content.trim().length() <= 0 || content.trim().toLowerCase().equals("null");
	}
}
