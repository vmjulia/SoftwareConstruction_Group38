package ch.uzh.group38;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Display1 extends JPanel {
    GUI gui;

    public Display1(GUI gui){
        this.gui = gui;

    }

    public void displayHistory2( boolean roundEnd, JToolBar toolbar, JLabel message, User player1, User player2) {

        this.removeAll();
        toolbar.removeAll();
        toolbar.setFloatable(false);

        if (roundEnd){
            message.setText("Player " + GUI.currentPlayerName() + " wins this round!! Do you want to play one more?");
            JButton resb = new JButton("One more round");
            resb.addActionListener(new Display1.NextRoundButton());
            JButton rb1 = new JButton("New Game");
            rb1.addActionListener(new Display1.ResetButton());
            toolbar.add(resb);
            toolbar.addSeparator();
            toolbar.add(rb1);
        }

        else{
            message.setText("Round " + RuleEvaluator.getCurrentRound());
            JButton rb3 = new JButton("back to game");
            rb3.addActionListener(new Display1.BackButton());
            toolbar.add(rb3);
        }

        toolbar.addSeparator();
        toolbar.add(message);
        toolbar.setOpaque(false);
        this.add(toolbar);
        this.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Score table", TitledBorder.CENTER, TitledBorder.TOP));
        String[][] rec = {
                { player1.getName(), String.valueOf(player1.getScore())},
                { player2.getName(), String.valueOf(player2.getScore()) },

        };
        String[] header = { "Player", "Score"};
        JTable table = new JTable(rec, header);
        this.add(new JScrollPane(table));

    }


    class NextRoundButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            RuleEvaluator.updateCurrentRound();
            gui.reset();
        }


    }

    class BackButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.refresh();
        }
    }

    class ResetButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            RuleEvaluator.resetCurrentRound();
            gui.setPlayers();
            gui.reset();
        }
    }
}
