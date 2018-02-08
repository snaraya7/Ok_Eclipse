package edu.ncstate.csc510.okeclipse.handlers;

import java.util.List;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.TextConsole;

import edu.ncstate.csc510.okeclipse.common.ILoudConsole;

public class LoudConsoleImpl implements ILoudConsole {

	/**
	 * Refer loud console extract requirement in explanation package
	 * TODO : kashyap
	 */

	@Override
	public List<String> extract(String consoleLog) {
		return null;
	}

	@Override
	public String getConsoleContent() {

		IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();

		IConsole[] consoles = manager.getConsoles();

		for (IConsole console : consoles) {

			if (console instanceof TextConsole) {

				IDocument document = ((TextConsole) console).getDocument();

				String text = document.get();
				
				return text;
				
			}

		}

		return null;
	}

}
