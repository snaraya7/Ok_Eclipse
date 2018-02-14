package edu.ncstate.csc510.okeclipse.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import edu.ncstate.csc510.okeclipse.builder.SOAnswerBuilder;
import edu.ncstate.csc510.okeclipse.common.ILoudConsole;

/**
 * 
 * @author ncshr
 *
 */
public class AnalyzeConsoleHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		SOAnswerBuilder response = new SOAnswerBuilder();

		ILoudConsole console = new LoudConsoleImpl();

		try {
			response.build(console.extract(console.getConsoleContent()));
		} catch (PartInitException | IOException e) {
			Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog.openError(activeShell, "Ok Eclipse", "Error while executing your request " + e.getMessage());

		}

		return null;
	}

}
