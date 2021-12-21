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
    int makeBet(int cash);
}

class DummyInputBehaviour implements InputBehaviour {
    @Override
    public String readHitOrStayInput() {
        return null;
    }

    public int makeBet(int cash) {
        int b=0;
        return b;
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
    public int makeBet(int cash) {
        int b;
        do {
            System.out.println("How much would you like to bet? ");
            b =  this.readIntInput();
        } while(b > cash || b <= 0);
        return b;
    }

    private int readIntInput(){
        while (true) {
            String input = new Scanner(System.in).nextLine();
            try {
                int i = Integer.parseInt(input);
                return i;
            } catch (Exception NumberFormatException) {
                System.out.println("Invalid input! Please give an input of type integer");
            }
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

    public int makeBet(int cash)  {
        try {int b;
            do {
                System.out.println("How much would you like to bet? ");
                b =  this.getIntInput();
            } while(b > cash || b <= 0);
            return b;}
        catch (InterruptedException e) {
                // return default
                return (10);
            }

    }


    private int getIntInput() throws  InterruptedException {

            this.hideMessages();

            System.out.println("How much would you like to bet? (choose 10/20/40/50/60/80/100");
            // hold on to leave time for thinking
            TimeUnit.SECONDS.sleep(3);
            recognizer.startRecognition(true);
            // start talking when message appears
            System.out.println("speak now...");
            SpeechResult result;
            // exit the loop only if correct value is perceived
            while (true) {
                result = recognizer.getResult();
                System.out.format("Hypothesis: %s\n", result.getHypothesis());
                if ((result.getHypothesis().equals( "ten"))|| result.getHypothesis().equals( "twenty")||
                        (result.getHypothesis().equals( "forty"))||(result.getHypothesis().equals( "fifty"))||
                        (result.getHypothesis().equals( "sixty"))||(result.getHypothesis().equals( "eighty"))||(result.getHypothesis().equals( "hundred")))
                {
                    break;

                }
                System.out.println("speak now");
            }
            recognizer.stopRecognition();
        // interpret as integers
            int output = switch (result.getHypothesis()) {
                case "ten" -> 10;
                case "twenty" -> 20;
                case "thrity" -> 30;
                case "forty" -> 40;
                case "fifty" -> 50;
                case "sixty" -> 60;
                case "seventy" -> 70;
                case "eighty" -> 80;
                case "ninety" -> 90;
                case "hundred" -> 100;
                default -> 0;
            };
            return output;
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
        // exit the loop only if correct value is perceived
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
