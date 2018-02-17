
package edu.ncstate.csc510.okeclipse.handlers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.*;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

import com.sohelper.datatypes.StackoverflowAnswer;

import edu.ncstate.csc510.okeclipse.builder.SOAnswerBuilder;
import edu.ncstate.csc510.okeclipse.common.ISoundProgrammer;
import edu.ncstate.csc510.okeclipse.util.Util;

public class Tester{
public static void main(String[] args) { 
	String javaClass = new String("$Hi did you get ittttttt$.$ge.$$Hi did you get ittttttt$");
	String javaClassset = javaClass;
	ArrayList<String> input = new ArrayList<String>();
	ArrayList<Integer> count = new ArrayList<Integer>();
	int left = javaClass.indexOf("$");
	//System.out.println(left);
	int setpoint = javaClass.indexOf("$");
	int left_1 = 0;
	while (left_1 >= 0)
	{	
		count.add(setpoint);
		String sub1 = javaClassset.substring(setpoint + 1);
		int right_pre = sub1.indexOf("$");
		int right = setpoint + right_pre + 1;
		String sub = javaClassset.substring(right + 1);
		left_1 = sub.indexOf("$");
		if (left_1 >=0)
		{
			setpoint = right + left_1+1;
		}
	}
	System.out.println(count);
}
}