package ch.uzh.group38;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

interface InputBehaviour {
    String readHitOrStayInput();
}

class DummyInputBehaviour implements InputBehaviour {
    @Override
    public String readHitOrStayInput() {
        return null;
    }
}

class TerminalInputBehaviour implements InputBehaviour {
    @Override


    public String readHitOrStayInput() {
        System.out.println("hit or stay? [H/S] ");
        while (true) {
            String input = new Scanner(System.in).nextLine().toLowerCase();
            if (input.equals("h") || input.equals("s")) {
                return input;
            }
            System.out.println("Invalid input! Please choose [H] or [S]");
        }
    }
}

class VoiceInputBehaviour implements InputBehaviour {
    private final Configuration configuration;
    // only one LiveSpeechRecognizer to avoid accessing busy microphone
    private final LiveSpeechRecognizer recognizer;

    public VoiceInputBehaviour() throws IOException {
        configuration = new Configuration();
        handleConfiguration();
        recognizer = new LiveSpeechRecognizer(configuration);
    }

    private void handleConfiguration() {
        // standard paths set up
        this.hideMessages();
        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        // grammar includes 2 words only: yes/no, optimizes processing time
        configuration.setGrammarPath("file:4th_assignment/Blackjack/src");
        configuration.setGrammarName("words");
        configuration.setUseGrammar(true);

    }

    private void hideMessages(){
        // do not output  messages of Sphinx4 to console
        Logger cmRootLogger = Logger.getLogger("default.config");
        cmRootLogger.setLevel(java.util.logging.Level.OFF);
        String conFile = System.getProperty("java.util.logging.config.file");
        if (conFile == null) {
            System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");
        }

    }

    private String voiceInput() throws InterruptedException {

        this.hideMessages();

        System.out.println("Do you want to hit [Yes/No] ? ");
        // hold on to leave time for thinking
        TimeUnit.SECONDS.sleep(3);
        recognizer.startRecognition(true);
        // start talking when message appears
        System.out.println("speak now...");
        SpeechResult result;
        while (true) {
            result = recognizer.getResult();
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
            if (result.getHypothesis().equals( "yes")|| result.getHypothesis().equals( "no")){
                break;

            }
            System.out.println("speak now");
        }
        recognizer.stopRecognition();

        // return the recognized string
        return  (result.getHypothesis());
    }

    @Override
    public String readHitOrStayInput() {
        String input = null;
        try {
            input = voiceInput();
        } catch (InterruptedException e) {
            System.out.println("\nJulia help!\n");
        }
        switch (input) {
            case "no":
                break;
            case "yes":
                return "h";
        }
        return "s";
    }
}
