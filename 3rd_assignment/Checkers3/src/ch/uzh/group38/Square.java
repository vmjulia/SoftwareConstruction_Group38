package ch.uzh.group38;

import javax.swing.*;
import java.awt.*;

abstract class Square extends JButton {
    public abstract void setVoidAction();
    public abstract void setEmptyAction();
    public abstract void setInactiveAction();
    public abstract void setActiveAction();
    public abstract void activate();
    public abstract void deactivate();
}

class EmptySquare extends Square {
    GUI.ButtonPressed buttonPressed;

    public EmptySquare(GUI.ButtonPressed passedAction) {
        this.buttonPressed = passedAction;
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        this.addActionListener(passedAction);
        this.setBackground(Color.white);
        this.setOpaque(true);
    }

    public void setVoidAction() {
        buttonPressed.setState(buttonPressed.getVoidState());
    }
    public void setEmptyAction() {
        buttonPressed.setState(buttonPressed.getEmptyState());
    }
    public void setInactiveAction() {
        buttonPressed.setState(buttonPressed.getInactiveState());
    }
    public void setActiveAction() {
        buttonPressed.setState(buttonPressed.getActiveState());
    }

    public void activate() {}
    public void deactivate() {}
}

class BlackSquare extends Square {
    GUI.ButtonPressed buttonPressed;

    public BlackSquare(GUI.ButtonPressed passedAction) {
        this.buttonPressed = passedAction;
        this.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        this.addActionListener(passedAction);
        this.setBackground(Color.black);
        this.setOpaque(true);
    }

    public void setVoidAction() {
        buttonPressed.setState(buttonPressed.getVoidState());
    }
    public void setEmptyAction() {
        buttonPressed.setState(buttonPressed.getEmptyState());
    }
    public void setInactiveAction() {
        buttonPressed.setState(buttonPressed.getInactiveState());
    }
    public void setActiveAction() {
        buttonPressed.setState(buttonPressed.getActiveState());
    }

    public void activate() {
        this.setBackground(Color.green);
    }

    public void deactivate() {
        this.setBackground(Color.black);
    }
}