package edu.ncstate.csc510.okeclipse.handlers;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.handlers.IHandlerService;

import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.result.WordResult;
import edu.ncstate.csc510.okeclipse.builder.CommandsBuilder;
import edu.ncstate.csc510.okeclipse.common.VoiceRecognizer;
import edu.ncstate.csc510.okeclipse.model.OECommand;
import edu.ncstate.csc510.okeclipse.util.Util;

/**
 * 
 * @author ncshr
 *
 */
public class SpeechHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
		Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();

		try {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(activeShell);

			dialog.run(false, false, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask("Speak now", 3);

					SpeechResult result;
					String spokenText = "";
					while ((result = VoiceRecognizer.getRecognizer().getResult()) != null) {

						for (WordResult r : result.getWords()) {
							spokenText += r.getWord() + " ";
						}

						if (spokenText != null && spokenText.length() > 0) {
							break;
						}

					}

					spokenText = clean(spokenText);

					monitor.setTaskName("You've asked for " + spokenText);

					monitor.worked(1);

					try {
						executeCommand(spokenText, window);
					} catch (ExecutionException | NotDefinedException | NotEnabledException | NotHandledException e) {
						MessageDialog.openError(activeShell, "Ok Eclipse",
								"Error while executing your request " + e.getMessage());
						e.printStackTrace();
					}

					monitor.done();
				}
			});
		} catch (Exception e) {

			MessageDialog.openError(activeShell, "Ok Eclipse", "Error while executing your request " + e.getMessage());
			e.printStackTrace();

		}

		return null;
	}

	private String clean(String spokenText) {

		if (spokenText != null) {

			spokenText = spokenText.replace("<sil>", "");
			spokenText = spokenText.replace("[SPEECH]", "");
			spokenText = spokenText.replace("[NOISE]", "");
			spokenText = spokenText.replace("[noise]", "");
		}

		return spokenText.trim().toLowerCase();
	}

	private void executeCommand(String spokenText, IWorkbenchWindow window)
			throws ExecutionException, NotDefinedException, NotEnabledException, NotHandledException {

		String commandId = findCommand(spokenText);

		if (Util.isNullString(commandId)) {

			Shell activeShell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
			MessageDialog.openInformation(activeShell, "Ok Eclipse", "Sorry we don't understand '" + spokenText
					+ "', Feel free to add/customize commands at " + CommandsBuilder.getCommandsfile() + ".");
			return;
		}

		IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
		handlerService.executeCommand(commandId, null);

	}

	// if (commands.isEmpty()) {
	// System.out.println("Empty");
	// return;
	// }

	// Collections.sort(commands, new Comparator<Command>() {
	//
	// @Override
	// public int compare(Command o1, Command o2) {
	//
	// int result = 0;
	//
	// String name1 = "";
	// try {
	// name1 = o1.getName().toLowerCase();
	// } catch (NotDefinedException e) {
	// }
	// String name2 = "";
	// try {
	// name2 = o2.getName().toLowerCase();
	// } catch (NotDefinedException e) {
	// }
	//
	// if (name1.startsWith(spokenText)) {
	// result = 1;
	// }
	//
	// if (name2.startsWith(spokenText)) {
	// result = -1;
	// }
	//
	// return result;
	// }
	//
	// });

	// System.out.println(spokenText + "\t sorted >> " + commands);
	// for (Command c : commands)
	// {

	// Command selected = commands.get(0);
	// int i = 0;
	// try {
	// for (Command c : commands) {
	// System.out.println("sorted > " + c.getId() + " " + c.getName() + " > " +
	// i++);
	// }
	//
	// System.out.println(spokenText + "\tExecuting " + selected.getId() + " > " +
	// selected.getName());
	// invokeCommand(window, selected, event);
	// } catch (ExecutionException | NotDefinedException | NotEnabledException |
	// NotHandledException e) {
	// System.out.println("Failed");
	// }
	// // }

	private String findCommand(String spokenText) {

		List<OECommand> commands = CommandsBuilder.getCommands();

		for (OECommand command : commands) {

			if (spokenText.contains(command.getName())) {
				return command.getId();
			}
		}

		return null;
	}

	// private List<Command> findRelevantCommands(String spokenText) {
	//
	// CommandManager commandManager =
	// PlatformUI.getWorkbench().getActiveWorkbenchWindow()
	// .getService(CommandManager.class);
	//
	// Command[] commands = commandManager.getAllCommands();
	//
	// write(commands);
	//
	// List<Command> relevantCommands = new ArrayList<>();
	//
	// for (Command c : commands) {
	//
	// try {
	// if (c.getName().toLowerCase().contains(spokenText)) {
	// relevantCommands.add(c);
	// }
	// } catch (NotDefinedException e) {
	// }
	//
	// }
	//
	// return relevantCommands;
	// }

	// private void write(Command[] commands) {
	//
	// int ccr = 0;
	// for (Command c : commands) {
	// try {
	// String rowEntry = c.getName().toLowerCase() + "," + c.getId();
	// ++ccr;
	// try (PrintWriter out = new PrintWriter(new BufferedWriter(
	// new OutputStreamWriter(new FileOutputStream("commands.csv", true),
	// StandardCharsets.UTF_8)))) {
	//
	// out.println(rowEntry);
	//
	// }
	//
	// catch (IOException e) {
	// e.printStackTrace();
	// }
	// } catch (NotDefinedException e) {
	// }
	// }
	//
	// System.out.println(ccr);
	// }

	// private void invokeCommand(IWorkbenchWindow window, Command command,
	// ExecutionEvent event)
	// throws ExecutionException, NotDefinedException, NotEnabledException,
	// NotHandledException {
	//
	// IHandlerService handlerService = (IHandlerService)
	// window.getService(IHandlerService.class);
	// handlerService.executeCommand(command.getId(), null);
	// }
}
