package edu.ncstate.csc510.okeclipse.common;

import java.io.IOException;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.ncstate.csc510.okeclipse.resources.Resources;

public class VoiceRecognizer {

	private static LiveSpeechRecognizer recognizer;

	public static void start() throws IOException {

		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
		configuration.setDictionaryPath(Resources.class.getResource("partial_dict.dict").toString());
		// configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
		configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
		recognizer = new LiveSpeechRecognizer(configuration);
		recognizer.startRecognition(true);
	}

	public static LiveSpeechRecognizer getRecognizer() {
		return recognizer;
	}

}
