package edu.ncstate.csc510.okeclipse.handlers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	 * @author charan
	 */
	@Override
	public String generateMainMethod() {
		
		String returnValue;
		returnValue = "public static void main (String[] args) {\r\n" + "		\r\n" + "}\r\n"; 
		return returnValue;
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
	 * @author charan
	 */
	@Override
	public String implementInterface(String javaSourceCode, String interfaceCode) {
		
		String returnValue,replaceStr;
		int beginIndex,endIndex;
		String [] method;
		
		beginIndex=interfaceCode.indexOf("interface");
		beginIndex+=8;
		endIndex=interfaceCode.indexOf("{", beginIndex);
		replaceStr="implements"+interfaceCode.substring(beginIndex+1, endIndex)+"{";
		javaSourceCode=javaSourceCode.replaceFirst("\\{", replaceStr);
		beginIndex=interfaceCode.indexOf("{");
		endIndex=interfaceCode.indexOf("}");
		replaceStr=interfaceCode.substring(beginIndex+1, endIndex);
		returnValue=javaSourceCode.replaceFirst("\\s+\\}", replaceStr+"\\}");
		replaceStr=replaceStr.replaceAll("\\{|\r\n\\s+", "");
		method=replaceStr.split(";");
		for (String s: method) 
		{           
			Pattern p = Pattern.compile("(?:public|private|static)\\s(\\w+)\\s(\\w+)\\(\\)");
	        Matcher m = p.matcher(s);
	        while(m.find())
	        {
	        	String dataType=m.group(1);
	        	String fnctName=m.group(2);
	        	switch(dataType) {
	        	case "void": 	replaceStr="){\r\n\r\n\t}";
	        				 	break;
	        	case "int": 	replaceStr="){\r\n\r\n\treturn 0;\r\n\t}";
	        					break;
	        	case "boolean": replaceStr="){\r\n\r\n\treturn false;\r\n\t}";
	        					break;
	        	case "String": replaceStr="){\r\n\r\n\treturn null;\r\n\t}";
	        					break;
	        	}
	        	returnValue=returnValue.replaceFirst("\\);", replaceStr);
	        }
		}
		return returnValue;
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
