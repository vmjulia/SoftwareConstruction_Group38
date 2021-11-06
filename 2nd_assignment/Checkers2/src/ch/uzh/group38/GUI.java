package ch.uzh.group38;// basic reference: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {

    private Move currentMove;
    private Board board;

    private int x1;
    private int y1;
    private boolean pawnActive;
    private JFrame frame;
    private final JPanel gui = new JPanel();
    private Square[][] playBoardSquares = new Square[8][8];
    private final String COLS = "ABCDEFGH";
    private static final JLabel message = new JLabel("Your add here!");

    private GUI() {

        //creating new window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Checkers");
        reset();
        frame.pack();
    }

    private void refresh(){
        // disgusting
        RuleEvaluator.board = board;

        gui.removeAll();
        message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please enter your move");
                
        //creating toolbar
        JButton rb = new JButton("Reset");
        rb.addActionListener(new ResetButton());
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(rb); 
        toolbar.addSeparator();
        toolbar.add(message);
        toolbar.setOpaque(false);
        
        //creating the board
        JPanel playBoard = new JPanel(new GridLayout(0, 10));
        playBoard.setBorder(new LineBorder(Color.black));

        for (int i = 0; i< playBoardSquares.length; i++) {
            for (int j = 0; j< playBoardSquares[i].length; j++) {
                //the square is black
                if ((i+j) %2 == 1) {
                    if (board.getField(i, j).isRed() && board.getField(i, j).isKing()){
                        playBoardSquares[i][j] = new RedKingSquare(new ButtonPressed(i, j));
                    }
                    else if (board.getField(i, j).isRed() && !board.getField(i, j).isKing()){
                        playBoardSquares[i][j] = new RedPawnSquare(new ButtonPressed(i, j));
                    }
                    else if (board.getField(i, j).isWhite() && board.getField(i, j).isKing()){
                        playBoardSquares[i][j] = new WhiteKingSquare(new ButtonPressed(i, j));
                    }
                    else if (board.getField(i, j).isWhite() && !board.getField(i, j).isKing()){
                        playBoardSquares[i][j] = new WhitePawnSquare(new ButtonPressed(i, j));
                    }
                    else {
                        playBoardSquares[i][j] = new BlackSquare(new ButtonPressed(i, j));
                    }
                }
                else playBoardSquares[i][j] = new EmptySquare();
            }
        }

        //fill in top row
        playBoard.add(new JLabel("+",SwingConstants.CENTER));
        for (int i = 0; i < 8; i++) {
            playBoard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        }
        playBoard.add(new JLabel("+",SwingConstants.CENTER));
        
        //fill in playboard
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 10; j++) {
                if (j == 0 || j==9) {
                        playBoard.add(new JLabel("" + (8-i), SwingConstants.CENTER));
                }
                else {
                        playBoard.add(playBoardSquares[i][j-1]);
                }
            }
        }
        //fill in bottom row
        playBoard.add(new JLabel("+",SwingConstants.CENTER));
        for (int i = 0; i < 8; i++) {
            playBoard.add(new JLabel(COLS.substring(i, i + 1), SwingConstants.CENTER));
        } 
        playBoard.add(new JLabel("+", SwingConstants.CENTER));


        //set up GUI
        gui.setBorder(new EmptyBorder(5, 5, 5, 5));
        gui.setLayout(new BoxLayout(gui, BoxLayout.Y_AXIS));
        gui.add(toolbar);
        gui.add(playBoard);
        frame.add(gui);
        frame.setVisible(true);
    }

    private void reset(){
        board = new Board();
        RuleEvaluator.resetCurrentPlayer();
        refresh();
    }

    public static void setMessage(String msg){
        message.setText(msg);
    }

    class ButtonPressed implements ActionListener {
        private boolean buttonActive = false;
        private final int x;
        private final int y;

        //x and y of a button are stored in AL upon creation for further reference
        public ButtonPressed(int X, int Y) {
            x = X;
            y = Y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (pawnActive) {
                //touchable button pressed again
                if (buttonActive) {
                    playBoardSquares[x][y].setBackground(Color.black);
                    playBoardSquares[x][y].setOpaque(true);
                    pawnActive = false;
                    buttonActive = false;
                    message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please enter your move");
                }
                //potential move
                else {
                    currentMove = new Move(x1, y1, x, y);
                    if (RuleEvaluator.isMovePossible(currentMove)) {
                        currentMove.move(board);
                        pawnActive = false;
                        refresh();
                        if (RuleEvaluator.checkWinner(board)) {
                            JOptionPane.showMessageDialog(frame, "Player " + RuleEvaluator.getCurrentPlayer() + " wins!!");
                            reset();
                        }
                    }
                }
            }
            else {
                if(RuleEvaluator.checkInput(x, y, board)) {
                    playBoardSquares[x][y].setBackground(Color.green);
                    playBoardSquares[x][y].setOpaque(true);
                    //the coordinates are stored in GUI
                    x1 = x;
                    y1 = y;
                    pawnActive = true;
                    buttonActive = true;
                    message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please select target field!");
                }
                else {
                    message.setText("Player " + RuleEvaluator.getCurrentPlayer() + " please touch your pawns only!");
                }
            }
        }
    }

    class ResetButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            reset();
        }
    }

    public static void main(String[] args) {
        new GUI();
    } 
}