package ch.uzh.group38;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Square extends JButton {
    public Square() {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
    }
}

class EmptySquare extends Square {
    public EmptySquare() {
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
}

class BlackSquare extends Square implements ActionListener{
    public BlackSquare() {
        this.setBackground(Color.black);
        this.setOpaque(true);

        this.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

class RedPawnSquare extends BlackSquare {
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");

    public RedPawnSquare() {
        this.setIcon(redPawnIcon);
    }
}

class WhitePawnSquare extends BlackSquare {
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    public WhitePawnSquare() {
        this.setIcon(whitePawnIcon);
    }
}

class RedKingSquare extends BlackSquare {
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");

    public RedKingSquare() {
        this.setIcon(redKingIcon);
    }
}

class WhiteKingSquare extends BlackSquare {
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");

    public WhiteKingSquare(){
        this.setIcon(whiteKingIcon);
    }
}