package edu.ncstate.csc510.okeclipse.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class InjectInterfaceHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

//		ISoundProgrammer iSoundProgrammer = new SoundProgrammerImpl();
//
//		try {
//			iSoundProgrammer.insertContent(iSoundProgrammer.implementInterface(javaSourceCode, "java.io.Serializable"),
//					Util.getCurrentEditorContent().get().length() - 2);
//			
//			IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
//			IHandlerService handlerService = (IHandlerService) window.getService(IHandlerService.class);
//			handlerService.executeCommand("org.eclipse.jdt.ui.edit.text.java.format", null);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Util.showError(e, e.getMessage());
//		}

		return null;
	}

}