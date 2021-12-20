package ch.uzh.group38;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public interface Strategy {
     boolean hit(int score) throws IOException, InterruptedException;
}

class PlayerStrategy implements Strategy{

    @Override
    public boolean hit(int score){
        System.out.println("hit or stay? [H/S] ");
        String input = readHitOrStayInput();
        switch (input) {
            case "s": break;
            case "h": return true;
        }
        return false;
    }

    private String readHitOrStayInput() {
        while (true) {
            String input = new Scanner(System.in).nextLine().toLowerCase();
            if (input.equals("h") || input.equals("s")){
                return input;
            }
            System.out.println("Invalid input! Please choose [H] or [S]");
        }
    }
}

class PlayerVoiceStrategy implements Strategy{
    private final Configuration configuration;
    // only one LiveSpeechRecognizer to avoid accessing busy microphone
    private final LiveSpeechRecognizer recognizer;

    public PlayerVoiceStrategy() throws IOException {
        // set up configuration once initialized
        configuration = new Configuration();
        handleConfiguration();
        recognizer = new LiveSpeechRecognizer(configuration);
    }

    @Override
    public boolean hit(int score) throws  InterruptedException {
        // get the string via voice input and interpret yes as hit
        String input = VoiceInput();
        switch (input) {
            case "no": break;
            case "yes": return true;
        }
        return false;
    }

    private void handleConfiguration() {
        // standard paths set up

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        // grammar includes 2 words only: yes/no, optimizes processing time
        configuration.setGrammarPath("file:4th_assignment/Blackjack/src");
        configuration.setGrammarName("words");
        configuration.setUseGrammar(true);

    }

    private String VoiceInput() throws InterruptedException {

        // do not output ino messages of Sphinx4 to console
        Logger cmRootLogger = Logger.getLogger("default.config");
        cmRootLogger.setLevel(java.util.logging.Level.OFF);
        String conFile = System.getProperty("java.util.logging.config.file");
        if (conFile == null) {
            System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");
        }



        System.out.println("Do you want to hit [Yes/No] ? ");
        // hold on to leave time for thinking
        TimeUnit.SECONDS.sleep(3);
        recognizer.startRecognition(true);
        // start talking when message appears
        System.out.println("speak now...");
        SpeechResult result;
        // repeat until a clear is received
        while (true)
        {
            result = recognizer.getResult();
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
            // print hypothesis and break the loop once clear input is received
            if (result.getHypothesis().equals( "yes")|| result.getHypothesis().equals( "no")){
                break;

        }
            System.out.println("speak now");
    }
        recognizer.stopRecognition();

        // return the recognized string
        return  (result.getHypothesis());
    }
}



class DealerStrategy implements Strategy{
    @Override
    // hit if score is lower than 17
    public boolean hit(int score){
        if (score < 17) {
            System.out.println("Dealer hits\n");
            return true;
        }
        return false;
    }
}
