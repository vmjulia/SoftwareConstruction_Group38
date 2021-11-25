package ch.uzh.group38;

import javax.swing.*;

public class Launcher {

    private static GUI gui;
    private static JFrame frame;
    private static int currentRound = 0;
    private  User user1;
    private static User user2;
    private static boolean Continue;

    public String currentPlayer(){
        if (RuleEvaluator.getCurrentPlayer() ==1){
            return (user1.getName());}
        return(user2.getName());
    }


    public void nextMove(){
        frame.add(gui.refresh(currentRound, currentPlayer()));
        frame.setVisible(true);

    }

    public void finishRound(){
        JOptionPane.showMessageDialog(frame, "Player " +
                currentPlayer() + " wins this round!! Do you want to play one more?");

        if (Continue){
            nextRound();

        }

    }

    public static void reset(){
        frame.add(gui.enterUser(currentRound));
        frame.setVisible(true);
    }

    public void startGame(String name1, String name2){
        currentRound = 1;
        user1 = new User(name1);
        user2 = new User(name2);
        startRound();
    }

    public  void startRound(){
        RuleEvaluator.resetCurrentPlayer();
        gui.reset();
        frame.add(gui.refresh(currentRound, currentPlayer()));
        frame.setVisible(true);
    }


    public  void nextRound(){
        currentRound = currentRound +1;
        startRound();
    }

    public static void displayHistory(){
        frame.add(gui.displayHistory(currentRound));
        frame.setVisible(true);

    }

    public  void returnToGame(){
        frame.add(gui.refresh(currentRound, currentPlayer()));
        frame.setVisible(true);

    }


        public static void main(String[] args) {
        Launcher launcher = new Launcher();
    // creating new window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Checkers");
        frame.pack();

        gui = new GUI(launcher);
        reset();

        }

    }

