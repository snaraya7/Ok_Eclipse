package edu.ncstate.csc510.okeclipse.handlers;

import java.io.IOException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;
import edu.ncstate.csc510.okeclipse.common.OkEclipseActivator;
import edu.ncstate.csc510.okeclipse.common.VoiceRecognizer;
import edu.ncstate.csc510.okeclipse.resources.Resources;

/**
 * Our sample handler extends AbstractHandler, an IHandler base class.
 * 
 * @see org.eclipse.core.commands.IHandler
 * @see org.eclipse.core.commands.AbstractHandler
 */
public class VoiceHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		SpeechResult result;
		String spokenText = "";
		while ((result = VoiceRecognizer.getRecognizer().getResult()) != null) {

			for (WordResult r : result.getWords()) {
				spokenText += r.getWord() + " ";
			}

			if (spokenText != null && spokenText.length() > 0) {
				break;
			}

			// IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			//
			// MessageDialog.openInformation(window.getShell(), "Okeclipse", ""+spokenText);
			//
			// System.out.println("stext = " + spokenText);
			//
			// if (spokenText.toLowerCase().trim().contains("open perspective")) {
			//
			// IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
			// invokeCommand(window);
			// // recognizer.stopRecognition();
			//
			// }

			// System.out.format("Hypothesis: %s\n", result.getHypothesis());
		}

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		MessageDialog.openInformation(window.getShell(), "Okeclipse", "" + spokenText);

		// MessageDialog.openInformation(window.getShell(), "Testplugins", "Hello,
		// Eclipse world");

		return null;
	}

	private void invokeCommand(IWorkbenchWindow window) {
		IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
		try {
			handlerService.executeCommand("org.eclipse.ui.window.resetPerspective", null);
		} catch (Exception ex) {
			throw new RuntimeException("org.eclipse.ui.window.resetPerspective");
			// Give message
		}
	}
}
