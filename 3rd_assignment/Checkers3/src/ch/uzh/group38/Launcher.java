package ch.uzh.group38;

import javax.swing.*;

public class Launcher {

    private static GUI gui;
    private static JFrame frame;
    private static int currentRound = 0;
    private static User user1;
    private static User user2;



    public void nextMove(){
        int currentPlayerNumber = RuleEvaluator.getCurrentPlayer();
        User currentPlayer = user1;
        if (currentPlayerNumber == 2){
            currentPlayer = user2;}
        frame.add(gui.refresh(currentRound, currentPlayer));
        frame.setVisible(true);

    }

    public void finishRound(){
        JOptionPane.showMessageDialog(frame, "Player " +
                RuleEvaluator.getCurrentPlayer() + " wins this round!! Do you want to play one more?");

    }

    public static void reset(){
        frame.add(gui.enterUser(currentRound));
        frame.setVisible(true);
    }

    public static void startRound(String name1, String name2){
        currentRound = 1;
        user1 = new User(name1);
        user2 = new User(name2);
        startGame();
    }

    public static void startGame(){
        RuleEvaluator.resetCurrentPlayer();
        gui.reset();
        frame.add(gui.refresh(currentRound, user1));
        frame.setVisible(true);
    }


    public static void nextRound(){
        currentRound = currentRound +1;
        startGame();
    }

    public static void displayHistory(){
        frame.add(gui.displayHistory(currentRound));
        frame.setVisible(true);

    }

    public static void returnToGame(){
        frame.add(gui.refresh(currentRound, user1));
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

