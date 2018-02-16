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
	public void injectCode(String javaClassprime) throws BadLocationException, IOException {

		String javaClass = javaClassprime; 
		
		int left = javaClass.indexOf("$");// String input = "test string (67)";
		ArrayList<String> input = new ArrayList<String>();
		ArrayList<Integer> count = new ArrayList<Integer>();
		int setpoint = javaClass.indexOf("$");
		int num = 1;
		while (setpoint > 0)
		{
			if (num % 2 != 0)
			{
			count.add(setpoint);
		}
			num+=1;
		}
		ArrayList<String> output = new ArrayList<String>();
		while (left >= 0) {
			String sub1 = javaClass.substring(left + 1);
			int right_pre = sub1.indexOf("$");
			// System.out.println(right_pre);
			int right = left + right_pre;
			// System.out.println(right);
			String sub = javaClass.substring(left + 1, right + 1);
			// System.out.println(right);
			// System.out.println(sub1);
			System.out.println(sub);
			System.out.println("\n");
			input.add(sub);
			// System.out.println("\n");
			javaClass = javaClass.substring(right + 2);
			// System.out.println(javaClass);
			left = javaClass.indexOf("$");
		}
		for (String question : input) {
			System.out.println(question);
		}
		// List<StackoverflowAnswer> stackoverflowAnswers = new
		// List<StackoverflowAnswer>();
		List<StackoverflowAnswer> myList = new ArrayList<StackoverflowAnswer>();
		IProgressMonitor monitor = new NullProgressMonitor();
		SOAnswerBuilder Ansfetcher = new SOAnswerBuilder();
			for (String question : input) {
				System.out.println("Entered Loop");
				System.out.println(question);
				myList = Ansfetcher.extractAnswers(question, monitor);

				// System.out.println(myList);
				// System.out.println("injecting code for "+javaClass);
				for (StackoverflowAnswer answer : myList) {
					if (answer.isAccepted()) {
						String Answer = answer.getBody();
						output.add(Answer);
						// System.out.println(Answer);
						left = Answer.indexOf("<code>");
						while (left > 0) {
							String sub1 = Answer.substring(left + 6);
							int right_pre = sub1.indexOf("</code>");
							int right = left + right_pre;
							// System.out.println(right);
							String sub = Answer.substring(left + 6, right - 1);
							// System.out.println(sub1);
							System.out.println(sub);
							insertContent(sub,0);
							// System.out.println("\n");
							Answer = Answer.substring(right - 1);
							left = Answer.indexOf("<code>");
						}
					}
				}
			}
	
		
//		for (String add : output)
//		{
//			left = javaClassprime.indexOf("<code>");
//			while (left > 0) {
//				String sub1 = javaClassprime.substring(left + 6);
//				int right_pre = sub1.indexOf("</code>");
//				int right = left + right_pre;
//				// System.out.println(right);
//				String sub = javaClass.substring(left + 6, right - 1);
//				// System.out.println(sub1);
//				System.out.println(sub);
//				// System.out.println("\n");
//				Answer = Answer.substring(right - 1);
//				left = Answer.indexOf("<code>");
			
	}
}

//

// {
// if (answer.isUpVoted())
// {
// String Answer = answer.getBody();
// System.out.println(Answer);
// int left = Answer.indexOf("<code>");
// while (left > 0)
// {
// String sub1 = Answer.substring(left+6);
// int right_pre = sub1.indexOf("</code>");
// int right = left + right_pre;
// //System.out.println(right);
// String sub = Answer.substring(left+6, right-1);
// //System.out.println(sub1);
// System.out.println(sub);
// //System.out.println("\n");
// Answer = Answer.substring(right-1);
// left = Answer.indexOf("<code>");
// }
// }
// }
