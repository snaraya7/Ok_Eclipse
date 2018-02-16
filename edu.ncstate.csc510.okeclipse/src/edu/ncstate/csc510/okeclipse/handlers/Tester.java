
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
	String javaClass = new String("$Hi did you get ittttttt$...$Hi did you get ittttttt$");
	int left = javaClass.indexOf("$");
	//System.out.println(left);
	while (left >= 0)
    	{
    	String sub1 = javaClass.substring(left+1);
    	int right_pre = sub1.indexOf("$");
    	//System.out.println(right_pre);
    	int right = left + right_pre;
    	//System.out.println(right);
    	String sub = javaClass.substring(left+1, right+1);
    	//System.out.println(right);
    	//System.out.println(sub1);
    	System.out.println(sub);
    	//input.add(sub);
    	//System.out.println("\n");
	    javaClass = javaClass.substring(right+2);
	    //System.out.println(javaClass);
    	left = javaClass.indexOf("$");
    	}
}

}