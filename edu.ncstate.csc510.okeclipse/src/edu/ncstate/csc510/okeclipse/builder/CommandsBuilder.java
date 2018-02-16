package edu.ncstate.csc510.okeclipse.builder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import edu.ncstate.csc510.okeclipse.model.OECommand;
import edu.ncstate.csc510.okeclipse.resources.Resources;

/**
 * 
 * @author ncshr
 *
 */
public class CommandsBuilder {

	public static void main(String[] args) {
		System.out.println(getCommands());
	}
	private static final String FILENAME = "commands.csv";

	private static final File commandsFile = new File(System.getProperty("user.dir") + File.separator + FILENAME);

	private static File getCommandsFile() throws IOException {

		if (!commandsFile.exists()) {
			writeCommandsFile();
		}

		return commandsFile;

	}
	
	public static File getCommandsfile() {
		return commandsFile;
	}

	private static void writeCommandsFile() throws IOException {

		InputStream initialStream = Resources.class.getResourceAsStream(FILENAME);
		byte[] buffer = new byte[initialStream.available()];
		initialStream.read(buffer);

		OutputStream outStream = new FileOutputStream(commandsFile);
		outStream.write(buffer);
	}

	private static List<OECommand> commands = new ArrayList<>();

	static {

		try {
			build();
		} catch (Exception e) {
			PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
				public void run() {
					Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
					MessageDialog.openError(activeShell, "Ok Eclipse",
							"Error while loading commands " + e.getMessage());
				}
			});
		}

	}

	private static void build() throws Exception {

		BufferedReader br = null;
		String line = "";

		try {

			br = new BufferedReader(new FileReader(getCommandsFile()));
			while ((line = br.readLine()) != null) {

				String[] lineEntry = line.split(",");

				commands.add(new OECommand(lineEntry[0], lineEntry[1]));

			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}

	}
	

	public static List<OECommand> getCommands() {
		return commands;
	}
}
