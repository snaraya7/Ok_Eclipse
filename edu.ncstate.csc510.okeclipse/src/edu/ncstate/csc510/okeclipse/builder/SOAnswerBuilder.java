package edu.ncstate.csc510.okeclipse.builder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;

import com.sohelper.datatypes.GoogleResult;
import com.sohelper.datatypes.StackoverflowAnswer;
import com.sohelper.datatypes.StackoverflowPost;
import com.sohelper.fetchers.GoogleFetcher;
import com.sohelper.fetchers.StackoverflowFetcher;
import com.sohelper.ui.QuestionPage;

/**
 * 
 * @author ncshr
 *
 */
public class SOAnswerBuilder {

	private static final String FILENAME = "soresponse.html";

	private StringBuffer content = new StringBuffer();

//	public void build(List<String> questions) throws IOException, PartInitException {
//
//		IProgressMonitor monitor = new NullProgressMonitor();
//
//		content.append("<html><body>");
//		for (String question : questions) {
//			content.append("<h2> Solution : " + question + "</h2>");
//			// content.append("<iframe width=\"420\" height=\"315\"
//			// src=\"https://www.youtube.com/results?search_query="
//			// + question + "\"> </iframe>");
//			buildHTMLBodyContent(extractAnswers(question, monitor));
//
//			String utubeUrl = "https://www.youtube.com/results?search_query=" + question;
//			content.append("<a target=\"_blank\" href=\"" + utubeUrl
//					+ "\"><img src=\"https://upload.wikimedia.org/wikipedia/commons/2/2e/YoutubeLogoLink.png\" alt=\"Smiley face\"></a>");
//
//			// openExternalBrowser(new URL("));
//
//		}
//		content.append("</body></html>");
//		write();
//
//		openBrowser();
//
//	}

	/**
	 * @author M.S.Karthik
	 * @param questions
	 * @throws IOException
	 * @throws PartInitException
	 */
	
	public void build(List<String> questions) throws IOException, PartInitException {

		IProgressMonitor monitor = new NullProgressMonitor();

		content.append("<html>");
		content.append("<style>	table { font-family: arial, sans-serif; border-collapse: collapse; width: 100%;}"); 
		content.append("td, th {border: 1px solid #bb8fce; text-align: left; padding: 8px;}");
		content.append("</style></head><body style=\"background-color:#ebdef0;\">");
		content.append("<h1 style=\"color:#a93226\" align=\"center\">Error Solution Recommendations</h1>");
		
		for (String question : questions) {
			content.append("<h2 style=\"color:red\">" + question + "</h2>");
			content.append("<table>");
			content.append("<tr><th bgcolor=\"#d35400\">Accepted?</th><th bgcolor=\"#d35400\">Upvotes</th> <th bgcolor=\"#d35400\">Solution</th></tr>");
						
			buildHTMLBodyContent(extractAnswers(question, monitor));  // content.append("<tr><td>VOTES_VALUE</td><td>SOLUTION_VALUE</td></tr>");

			content.append("</table><br></br>");
			
			String utubeUrl = "https://www.youtube.com/results?search_query=" + question;
			content.append("<a target=\"_blank\" href=\"" + utubeUrl
					+ "\"><img src=\"https://upload.wikimedia.org/wikipedia/commons/2/2e/YoutubeLogoLink.png\" alt=\"Smiley face\"></a>");
			
			
			// openExternalBrowser(new URL("));

		}
		
		content.append("</body></html>");
		
		write();

		openBrowser();

	}
	
	
	private List<StackoverflowAnswer> extractAnswers(String question, IProgressMonitor monitor) throws IOException {
		List<GoogleResult> googleResults = GoogleFetcher.getGoogleResults(question, monitor);
		List<StackoverflowPost> stackoverflowPosts = StackoverflowFetcher.getStackoverflowPosts(googleResults, monitor);
		QuestionPage questionPage = new CustomQuestionPage();
		List<StackoverflowAnswer> stackoverflowAnswers = StackoverflowFetcher
				.getStackoverflowAnswers(stackoverflowPosts, monitor, questionPage);
		return stackoverflowAnswers;
	}

	private void openExternalBrowser(URL url) throws PartInitException, MalformedURLException {
		final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport().getExternalBrowser();
		browser.openURL(url);
	}

	private void openBrowser() throws PartInitException, MalformedURLException {
		final IWebBrowser browser = PlatformUI.getWorkbench().getBrowserSupport()
				.createBrowser("org.eclipse.ui.browser");
		browser.openURL((getResponseFile()).toURI().toURL());
	}

	private void write() throws IOException {

		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(getResponseFile()), StandardCharsets.UTF_8)))) {

			out.println(content);

		} catch (IOException e) {
			throw e;
		}

	}

	private static File getResponseFile() {
		return new File(System.getProperty("user.dir") + File.separator + FILENAME);
	}

	private void buildHTMLBodyContent(List<StackoverflowAnswer> stackoverflowAnswers) {

		for (StackoverflowAnswer answer : stackoverflowAnswers) {
			
			content.append("<tr><td>");
			if (answer.isAccepted()) {
				content.append("Yes");
			} else {
				content.append("No");
			}
			
			content.append("</td>");
			content.append("<td>");
			content.append(answer.getVoteCount());
			content.append("</td>");
			content.append("<td>");
			content.append(answer.getBody());
			content.append("</td></tr>");
		}

	}

	class CustomQuestionPage extends QuestionPage {

		@Override
		public boolean isAcceptedOnly() {
			return true;
		}

		@Override
		public boolean isUpVotedOnly() {
			return true;
		}
	}

}
