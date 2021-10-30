package ch.uzh.group38;// from: https://introcs.cs.princeton.edu/java/15inout/GUI.java.html

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI implements ActionListener {

    private final JLabel label;
    private JFrame frame;
    private JPanel panel;
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    public GUI(Board board) {
        /*
        basically the window
         */
        frame = new JFrame();

        /*
        actions for the buttons
         */

        label = new JLabel("Your add here!");

        /*
        layout to put stuff in the window
         */
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 200, 100));
        panel.setLayout(new GridLayout(9, 8));

        boolean timeForWhite = false;
        for (int i = 0; i<8; i++) {
            for (int j = 0; j<8; j++) {
                if (timeForWhite) {
                    JButton button = new JButton();
                    button.setBackground(Color.white);
                    button.setBorder(BorderFactory.createEmptyBorder(25, 25, 25,
                            25));
                    panel.add(button);
                }
                else {
                    JButton button;
                    if (board.isWhite(i, j)) {
                        button = new JButton(redPawnIcon);
                    }
                    else if (board.isRed(i, j)) {
                        button = new JButton(whitePawnIcon);
                    }
                    else {
                        button = new JButton();
                    }
                    button.setBackground(Color.black);
                    button.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
                    button.addActionListener(new GoodAction(i, j));
                    panel.add(button);
                }
                timeForWhite = !timeForWhite;
            }
            timeForWhite = !timeForWhite;
        }
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    class GoodAction implements ActionListener {
        private int x;
        private int y;

        public GoodAction(int i, int j) {
            x = i;
            y = j;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.print(x);
            System.out.println(y);
        }
    }
    class BadAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

}
