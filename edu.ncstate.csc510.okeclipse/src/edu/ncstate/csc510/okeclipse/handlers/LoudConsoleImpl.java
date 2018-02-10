package edu.ncstate.csc510.okeclipse.handlers;

import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import edu.ncstate.csc510.okeclipse.common.ILoudConsole;

public class LoudConsoleImpl implements ILoudConsole {
	public static void main(String[] args) {

		LoudConsoleImpl consoleImpl = new LoudConsoleImpl();
		List<String> output = consoleImpl.extract("java.lang.ASDERror, java.lang.NasdaException");
		//
		System.out.println(output);
	}

	/**
	 * @author kashy
	 */
	@Override
	public List<String> extract(String consoleLog) {
		ArrayList<String> result = new ArrayList<String>();
		String searchTerm_Except = "Exception";
		String searchTerm_Error = "error";
		String text = consoleLog;
		String sPattern_Except = "(?i)\\b\\w*" + Pattern.quote(searchTerm_Except) + "\\w*\\b";
		String sPattern_Error = "(?i)\\b\\w*" + Pattern.quote(searchTerm_Error) + "\\w*\\b";
		Pattern pattern_Except = Pattern.compile(sPattern_Except);
		Pattern pattern_Error = Pattern.compile(sPattern_Error);
		Matcher matcher_Except = pattern_Except.matcher(text);
		while (matcher_Except.find()) {
			result.add("java.lang." + matcher_Except.group());
		}
		Matcher matcher_Error = pattern_Error.matcher(text);
		while (matcher_Error.find()) {
			result.add("java.lang." + matcher_Error.group());
		}
		Set<String> hs = new HashSet<>();
		hs.addAll(result);
		result.clear();
		result.addAll(hs);
		return result;
	}

	@Override
	public String getConsoleContent() {
		// TODO Auto-generated method stub
		return null;
	}
}
