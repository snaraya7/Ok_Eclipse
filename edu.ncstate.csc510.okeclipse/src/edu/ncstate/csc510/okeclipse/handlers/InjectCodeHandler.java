package edu.ncstate.csc510.okeclipse.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.BadLocationException;

import edu.ncstate.csc510.okeclipse.common.ISoundProgrammer;
import edu.ncstate.csc510.okeclipse.util.Util;

/**
 * 
 * @author ncshr
 *
 */
public class InjectCodeHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		ISoundProgrammer programmer = new SoundProgrammerImpl();

		try {
			programmer.injectCode(Util.getCurrentEditorContent().get());
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
