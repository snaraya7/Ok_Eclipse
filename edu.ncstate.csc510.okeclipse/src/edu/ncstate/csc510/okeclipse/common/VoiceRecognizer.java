package edu.ncstate.csc510.okeclipse.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.ncstate.csc510.okeclipse.builder.CustomDictionaryBuilder;
import edu.ncstate.csc510.okeclipse.util.Util;

/**
 * 
 * @author ncshr
 *
 */
public class VoiceRecognizer {

	private static LiveSpeechRecognizer recognizer;

	private static final String FILENAME = "okeclipse.dict";

	public static void start() throws IOException {

		if (isDictionaryNeeded()) {
			writeDictionary();
		}

		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		System.out.println(getCustomDictionaryFile().toURI().toURL().toString());
		configuration.setDictionaryPath(getCustomDictionaryFile().toURI().toURL().toString());
		// configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		recognizer = new LiveSpeechRecognizer(configuration);
		recognizer.startRecognition(true);
	}

	private static void writeDictionary() throws IOException {

		Map<String, String> customDictionary = new CustomDictionaryBuilder().build();

		try (PrintWriter out = new PrintWriter(new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(getCustomDictionaryFile()), StandardCharsets.UTF_8)))) {

			for (String key : customDictionary.keySet()) {
				{
					out.println(customDictionary.get(key));
				}

			}

		} catch (IOException e) {
			throw e;
		}

	}

	private static File getCustomDictionaryFile() {
		return new File(System.getProperty("user.dir") + File.separator + FILENAME);
	}

	private static boolean isDictionaryNeeded() {
		return true;
	}

	public static LiveSpeechRecognizer getRecognizer() {
		return recognizer;
	}

}
