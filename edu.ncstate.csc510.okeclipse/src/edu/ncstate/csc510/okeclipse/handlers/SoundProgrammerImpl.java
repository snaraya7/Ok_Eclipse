package edu.ncstate.csc510.okeclipse.handlers;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

import edu.ncstate.csc510.okeclipse.common.ISoundProgrammer;

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
	 * @author M.S.Karthik
	 */
	@Override
	public String generateGetterSetter(String javaSourceCode) {
		String getSetResult = javaSourceCode.substring(0,javaSourceCode.length()-1);
		List<String> getSetResultLines = new ArrayList<String>();
		
// Finding indentation		
		int flag = 0;
		String JSC_findIndent= javaSourceCode.substring(javaSourceCode.indexOf('{')+1);
		JSC_findIndent = JSC_findIndent.replaceAll("\r\n", "");
		for(int i = 0; i < JSC_findIndent.length(); i++) {
			if (Character.isLetter(JSC_findIndent.charAt(i))) {
				flag = i;
				break;
			}
		}
		String indentation = JSC_findIndent.substring(0, flag);
		
// Extracting names and types of members		
		String JSC_temp = javaSourceCode.replaceAll("public", "").replaceAll("private", "").replaceAll("\r\n", "");
		JSC_temp = JSC_temp.substring(JSC_temp.indexOf("{")+1, JSC_temp.length()-1);
		int char_flag = 0;
		String JSC_modified = "";
		for(int i = 1; i < JSC_temp.length(); i++) {
			if(JSC_temp.charAt(i) != ' ') {
				JSC_modified += JSC_temp.charAt(i);
				char_flag = 1;
			}
			else if(char_flag == 1 && JSC_temp.charAt(i) == ' ') {
				JSC_modified += '#';
				char_flag = 0;
			}
		}	
		JSC_modified += "#";
		
// Removing methods
		while(JSC_modified.contains("{")) {
			int rem1 = JSC_modified.indexOf("{");
			int rem2 = JSC_modified.indexOf("}");
			String rem = JSC_modified.substring(rem1+1, rem2);
			rem = "\\{" + rem + "\\}"; 
			JSC_modified = JSC_modified.replaceFirst(rem, "");
		}
		List<String> JSC_modifiedList = new ArrayList<String>(Arrays.asList(JSC_modified.split("#")));
		JSC_modified = "";
		String item;
		for(int i = 0; i < JSC_modifiedList.size()-1; i++) {
			item = JSC_modifiedList.get(i+1);
			if(item.contains("(")) {
				JSC_modified += JSC_modifiedList.get(i) + "#";
			}
			else {
				i++;
			}
		}
		JSC_modified = JSC_modified.replaceAll("\\s+", "").replaceAll(";", "");
		List<String> JSC_memberList = new ArrayList<String>(Arrays.asList(JSC_modified.split("#")));

// Preparing the getter and setter method strings	
		for(int i = 0; i < JSC_memberList.size(); i+=2) {
			String member_type = JSC_memberList.get(i);
			String member_identifier = JSC_memberList.get(i+1);
			String getter = "\r\n" + indentation + "public " + member_type + " get" + member_identifier + "()" + "{\r\n" +
			indentation + "\treturn " + member_identifier + ";\r\n" + 
			indentation + "}";
			String setter = "\r\n" + indentation + "public " + "void" + " set" + member_identifier + "(" + member_type + " " + member_identifier + ")" + "{\r\n" + 
			indentation + "\tthis." + member_identifier + " = " + member_identifier +";\r\n" + 
			indentation + "}";
			getSetResultLines.add(getter);
			getSetResultLines.add(setter);
		}
		
// Combining the generated methods and given class to supply filled class string
		for(int i = 0; i<getSetResultLines.size(); i++) {
			getSetResult += getSetResultLines.get(i);
		}
		getSetResult += "\r\n}";
		return getSetResult;

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
	public void insertContent(String content) throws BadLocationException {

		final IEditorPart editor = (ITextEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();
		IDocumentProvider dp = ((ITextEditor) editor).getDocumentProvider();
		IDocument doc = dp.getDocument(editor.getEditorInput());
		int offset = doc.getLineOffset(doc.getNumberOfLines() - doc.getNumberOfLines() > 5 ? 4 : 1);
		doc.replace(offset, 0, content);

	}

}
