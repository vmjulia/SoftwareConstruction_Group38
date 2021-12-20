package ch.uzh.group38;

import java.io.IOException;
import java.util.Scanner;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import java.util.Timer;
import java.util.logging.Logger;

public interface Strategy {
    public boolean hit(int score) throws IOException;
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
    SpeechResult result;
    Configuration configuration = new Configuration();

    public PlayerVoiceStrategy(){
        handleConfiguration();
    }

    @Override
    public boolean hit(int score) throws IOException {
        String input = VoiceInput();
        switch (input) {
            case "no": break;
            case "yes": return true;
        }
        return false;
    }

    private void handleConfiguration() {

        configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
        configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
        configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        configuration.setGrammarPath("file:4th_assignment/Blackjack/src");
        configuration.setGrammarName("words");
        configuration.setUseGrammar(true);

    }

    private String VoiceInput() throws IOException {

        Logger cmRootLogger = Logger.getLogger("default.config");
        cmRootLogger.setLevel(java.util.logging.Level.OFF);
        String conFile = System.getProperty("java.util.logging.config.file");
        if (conFile == null) {
            System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");
        }

        LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);

        while (true)
        {
            recognizer.startRecognition(true);
            System.out.println("Do you want to hit [Yes/No] ? ");
            result = recognizer.getResult();
            System.out.format("Hypothesis: %s\n", result.getHypothesis());
            recognizer.stopRecognition();
            if (result.getHypothesis().equals( "yes")|| result.getHypothesis().equals( "no")){
                return  (result.getHypothesis());
            }
        }
    }
}





class DealerStrategy implements Strategy{
    @Override
    public boolean hit(int score){
        if (score < 17) {
            System.out.println("Dealer hits\n");
            return true;
        }
        return false;
    }
}
