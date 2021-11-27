package ch.uzh.group38;

import java.awt.Dimension;

import javax.swing.*;

public class Launcher {

    private static GUI gui;
    private static JFrame frame;
    private static int currentRound = 0;
    private  User user1;
    private static User user2;

    private Launcher(){
        // creating new window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Checkers");
        frame.setPreferredSize(new Dimension(600, 1000));
        frame.pack();
    }

    private User currentPlayer(){
        if (RuleEvaluator.getCurrentPlayer() ==1){
            return (user1);}
        return(user2);
    }

    public String currentPlayerName(){
        return(currentPlayer().getName());
    }



    public void nextMove(){
        frame.add(gui.refresh());
        frame.setVisible(true);

    }

    public void finishRound(){
        currentPlayer().increaseScore();
        frame.add(gui.displayHistory(currentRound, user1, user2, true));
        frame.setVisible(true);

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
        gui.refresh();
        frame.setVisible(true);
    }


    public  void nextRound(){
        currentRound = currentRound +1;
        startRound();
    }

    public  void displayHistory(){
        frame.add(gui.displayHistory(currentRound, user1, user2, false));
        frame.setVisible(true);

    }

    public  void returnToGame(){
        frame.add(gui.refresh());
        frame.setVisible(true);

    }

    public int getCurrentRound(){
        return currentRound;
    }


        public static void main(String[] args) {
        Launcher launcher = new Launcher();        
        gui = new GUI(launcher);
        reset();

        }

    }

