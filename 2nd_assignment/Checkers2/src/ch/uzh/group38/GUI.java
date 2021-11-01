package ch.uzh.group38;// from: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private static Move currentMove;
    private static Board board;

    private static int x1;
    private static int y1;
    private static boolean pawnActive;
    private static JFrame frame;
    private static final JPanel gui = new JPanel();
    private static JPanel playboard;
    private static Square[][] playboardsquares = new Square[8][8];
    private static final String COLS = "ABCDEFGH";
    public static final JLabel message = new JLabel("Your add here!");
    private static final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");
    private static final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");
    private static final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");
    private static final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    //constructor
    private GUI() {
        
        RuleEvaluator.resetCurrentPlayer();
        this.board = new Board();
        
        //creating new window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        refresh();
        frame.pack();
    }

    private static void refresh(){
        message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please enter your move");
                
        //creating toolbar
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(new JButton("Reset")); //no functionality yet
        toolbar.addSeparator();
        toolbar.add(message);
        
        //creating the board
        playboard = new JPanel(new GridLayout(0, 10));
        playboard.setBorder(new LineBorder(Color.black));

        for (int i = 0; i<playboardsquares.length; i++) {
            for (int j = 0; j<playboardsquares[i].length; j++) {
                Square button = new EmptySquare();

                if ((i+j) %2 == 1) {
                    if (board.getField(i, j).isRed() && board.getField(i, j).isKing()){
                        button = new RedKingSquare(i, j);
                    }
                    else if (board.getField(i, j).isRed() && !board.getField(i, j).isKing()){
                        button = new RedPawnSquare(i, j);
                    }
                    else if (board.getField(i, j).isWhite() && board.getField(i, j).isKing()){
                        button = new WhiteKingSquare(i, j);
                    }
                    else if (board.getField(i, j).isWhite() && !board.getField(i, j).isKing()){
                        button = new WhitePawnSquare(i, j);
                    }
                    else {
                        button = new BlackSquare(i, j);
                    }
                }
                playboardsquares[i][j] = button;
            }
        }

        //fill in top row
        playboard.add(new JLabel("+",SwingConstants.CENTER));
        for (int i = 0; i < 8; i++) {
            playboard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }
        playboard.add(new JLabel("+",SwingConstants.CENTER));
        
        //fill in playboard
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 || j==9) {
                        playboard.add(new JLabel("" + (8-i), SwingConstants.CENTER));
                }
                else {
                        playboard.add(playboardsquares[i][j-1]);
                }
            }
        }
        //fill in bottom row
        playboard.add(new JLabel("+",SwingConstants.CENTER));
        for (int i = 0; i < 8; i++) {
            playboard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        } 
        playboard.add(new JLabel("+", SwingConstants.CENTER)); 


        //set up GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.add(toolbar);
        gui.add(playboard);
        frame.add(gui);
        frame.setVisible(true);
    }
    

    public static void buttonUnpressed() {
        pawnActive = false;
    }

    public static void buttonPressed(int passedX, int passedY) {
        if (pawnActive) {
            currentMove = new Move(x1, y1, passedX, passedY);
            if (RuleEvaluator.checkValidity(currentMove, board)) {
                currentMove.move(board);
                pawnActive = false;
                gui.removeAll();
                refresh();
                if (RuleEvaluator.checkWinner(board)) {
                    JOptionPane.showMessageDialog(frame, "Player " + RuleEvaluator.getCurrentPlayer() + " wins!!");
                    //System.exit(0);
                }

            }
        }
        else {
            if (RuleEvaluator.checkInput(passedX, passedY, board)) {
                playboardsquares[passedX][passedY].setBackground(Color.green);
                playboardsquares[passedX][passedY].setOpaque(true);
                x1 = passedX;
                y1 = passedY;
                pawnActive = true;
                message.setText("Please select target field!");

            }
            else {
                message.setText("Please touch your pawns only!");
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    } 

}