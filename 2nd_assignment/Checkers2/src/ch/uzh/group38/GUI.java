package ch.uzh.group38;// from: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener {

    private  Move currentMove;
    private  Board board;

    private int x1;
    private int y1;
    private boolean pawnActive;
    private JFrame frame;
    private final JPanel gui = new JPanel();
    private JPanel playboard;
    private JButton[][] playboardsquares = new JButton [8][8];
    private final String COLS = "ABCDEFGH";
    public static final JLabel message = new JLabel("Your add here!");
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    //constructor
    private GUI() {
        //creating new instance of Board
        this.board = new Board();
        
        //creating new window
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        
        //updating window
        refresh();
        frame.pack();
        frame.setVisible(true);
    }

    private void refresh(){
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
                JButton button = new JButton();
                button.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                
                if ((i+j) %2 == 1) {
                    if (board.getField(i, j).isRed() && board.getField(i, j).isKing()){
                        button.setIcon(redKingIcon);
                    }
                    if (board.getField(i, j).isRed() && !board.getField(i, j).isKing()){
                        button.setIcon(redPawnIcon);
                    }
                    if (board.getField(i, j).isWhite() && board.getField(i, j).isKing()){
                        button.setIcon(whiteKingIcon);
                    }
                    if (board.getField(i, j).isWhite() && !board.getField(i, j).isKing()){
                        button.setIcon(whitePawnIcon);
                    }
                    button.addActionListener(new GoodAction(i, j, button));
                    button.setBackground(Color.black);
                    button.setOpaque(true);
                }
                else {
                    button.setBackground(Color.white);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    class GoodAction implements ActionListener {
        private JButton associatedButton;
        private Integer x;
        private Integer y;

        public GoodAction(int i, int j, JButton button) {
            associatedButton = button;
            x = i;
            y = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            //i.e. the first chosen pawn is valid input
            if (pawnActive) {
                currentMove = new Move(x1, y1, x, y);
                //press the same button again to cancel
                if (associatedButton.getBackground() == Color.green) {
                    associatedButton.setBackground(Color.black);
                    pawnActive = false;
                }
                //i.e. the move is valid
                else if (RuleEvaluator.checkValidity(currentMove, board)) {
                    currentMove.move(board);
                    pawnActive = false;
                    gui.removeAll();
                    refresh();
                }
            }
            //no valid pawn has been chosen yet
            else {
                if (RuleEvaluator.checkInput(x, y, board)) {
                    associatedButton.setBackground(Color.green);
                    x1 = x;
                    y1 = y;
                    pawnActive = true;
                } else {
                    message.setText("Please touch your pawns only");
                }
            }

        }
    }

    public static void main(String[] args) {
        new GUI();
    } 

}