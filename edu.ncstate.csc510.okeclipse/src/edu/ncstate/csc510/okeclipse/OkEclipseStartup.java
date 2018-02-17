
package edu.ncstate.csc510.okeclipse;

import java.io.IOException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.PlatformUI;

import edu.ncstate.csc510.okeclipse.common.VoiceRecognizer;

/**
 * 
 * @author ncshr
 *
 */
public class OkEclipseStartup implements IStartup {

	@Override
	public void earlyStartup() {

		try {
			VoiceRecognizer.start();

			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageDialog.openConfirm(activeShell, "Ok Eclipse",
							"'shift+z' and recite your request!");
				}
			});

		} catch (IOException e) {
			e.printStackTrace();
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageDialog.openConfirm(activeShell, "Ok Eclipse!",
							"Error occured (see log for more details)  : " + e.getMessage());
				}
			});

		}
	}

}
