package ch.uzh.group38;

import ch.uzh.group38.Board;
import ch.uzh.group38.RuleEvaluator;

public class Game {

    /*
    asks for input 
    */
    public static void askForInput(){
        System.out.println("User " + RuleEvaluator.getCurrentPlayer() + " please enter your next move:");
        readInput();
    }

    /*
    reads input from console 
    */
    public static void readInput(){
        
    }
    public static void main(String[] args) {
        Board board = new Board();
        board.resetBoard();
        board.printBoard();

        askForInput();
    }
}
