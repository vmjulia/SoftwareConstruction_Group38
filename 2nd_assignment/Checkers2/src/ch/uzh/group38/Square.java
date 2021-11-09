package ch.uzh.group38;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class Square extends JButton {
}

class EmptySquare extends Square {
    public EmptySquare() {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.setBackground(Color.white);
        this.setOpaque(true);
    }
}

class BlackSquare extends Square{
    //private int x;
    //private int y;

    public BlackSquare(ActionListener PassedAction) {
        this.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        this.setBackground(Color.black);
        this.setOpaque(true);
        this.addActionListener(PassedAction);
    }
}

class RedPawnSquare extends BlackSquare {
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");

    public RedPawnSquare(ActionListener PassedAction) {
        super(PassedAction);
        this.setIcon(redPawnIcon);
    }
}

class WhitePawnSquare extends BlackSquare {
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");

    public WhitePawnSquare(ActionListener PassedAction) {
        super(PassedAction);
        this.setIcon(whitePawnIcon);
    }
}

class RedKingSquare extends BlackSquare {
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");

    public RedKingSquare(ActionListener PassedAction) {
        super(PassedAction);
        this.setIcon(redKingIcon);
    }
}

class WhiteKingSquare extends BlackSquare {
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");

    public WhiteKingSquare(ActionListener PassedAction){
        super(PassedAction);
        this.setIcon(whiteKingIcon);
    }
}