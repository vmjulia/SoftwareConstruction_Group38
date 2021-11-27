package ch.uzh.group38;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI {


    //the argument might be null, but intellij's suggestion does not solve that issue
    private final Icon whitePawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_pawn.png");
    private final Icon whiteKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/white_king.png");
    private final Icon redPawnIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_pawn.png");
    private final Icon redKingIcon = new ImageIcon("2nd_assignment/Checkers2/resources/red_king.png");

    private Board board;


    private int x1;
    private int y1;
    private boolean pawnActive = false;
    private final JPanel gui = new JPanel();
    private final JPanel history = new JPanel();
    private final JPanel user = new JPanel();
    private final Square[][] playBoardSquares = new Square[8][8];
    private static final JLabel message = new JLabel("Your add here!");
    private final Launcher launcher;
    private JTextField User1;
    private JTextField User2;



    public GUI(Launcher launcher) {

        this.launcher = launcher;

        for (int i = 0; i< playBoardSquares.length; i++) {
            for (int j = 0; j< playBoardSquares[i].length; j++) {
                if ((i+j) %2 == 1){
                    playBoardSquares[i][j] = new BlackSquare(new ButtonPressed(i, j));
                }
                else {
                    playBoardSquares[i][j] = new EmptySquare(new ButtonPressed(i, j));
                }
            }
        }

    }

    public JPanel refresh(){
        gui.removeAll();
        history.removeAll();
        user.removeAll();
        message.setText("Round " + launcher.getCurrentRound() + ". Player " + launcher.currentPlayerName() + " please enter your move");
                
        //creating toolbar
        JButton rb = new JButton("Reset");
        rb.addActionListener(new ResetButton());
        JButton rb2 = new JButton("Check game history");
        rb2.addActionListener(new ScoreTableButton());
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(rb); 
        toolbar.addSeparator();
        toolbar.add(rb2);
        toolbar.addSeparator();
        toolbar.add(message);
        toolbar.setOpaque(false);
        
        //creating the board
        JPanel playBoard = new JPanel(new GridLayout(0, 10));
        playBoard.setBorder(new LineBorder(Color.black));

        Iterator currentIterator = board.createIterator();
        while (currentIterator.hasNext()){
            Field currentField = currentIterator.next();
            int i =currentField.getX();
            int j = currentField.getY();
            if ((i+ j)%2 ==1){
                if (currentField.isRed() && currentField.isKing()){
                    playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(redKingIcon);
                }
                else if (currentField.isRed() && !currentField.isKing()){
                    playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(redPawnIcon);
                }
                else if (currentField.isWhite() && currentField.isKing()){
                    playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(whiteKingIcon);
                }
                else if (currentField.isWhite() && !currentField.isKing()){
                    playBoardSquares[i][j].setInactiveAction();
                        playBoardSquares[i][j].applyIcon(whitePawnIcon);
                }
                else {
                    playBoardSquares[i][j].setEmptyAction();
                        //removes any icon
                        playBoardSquares[i][j].applyIcon(null);
                }

            }
            else playBoardSquares[i][j].setVoidAction();
        }

        //fill in top row
        playBoard.add(new JLabel("+",SwingConstants.CENTER));
        String COLS = "ABCDEFGH";
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
        return(gui);

    }

    public JPanel displayHistory(int currentRound, User User1, User User2, boolean roundEnd){
        gui.removeAll();
        history.removeAll();
        user.removeAll();
        if (roundEnd){
            message.setText("Player " +
                    launcher.currentPlayerName() + " wins this round!! Do you want to play one more?");

        //creating toolbar
        JButton rb = new JButton("One more round");
        rb.addActionListener(new NextRoundButton());
        JButton rb1 = new JButton("New Game");
        rb1.addActionListener(new ResetButton());
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(rb);
        toolbar.addSeparator();
        toolbar.add(rb1);
        toolbar.add(message);
        toolbar.setOpaque(false);
        history.add(toolbar);

        }
        else{
        message.setText("Round " + currentRound );
        //creating toolbar
        JButton rb3 = new JButton("back to game");
        rb3.addActionListener(new BackButton());
        JToolBar toolbar = new JToolBar();
        toolbar.setFloatable(false);
        toolbar.add(rb3);
        toolbar.addSeparator();
        toolbar.add(message);
        toolbar.setOpaque(false);
        history.add(toolbar);
        }



        history.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Score table", TitledBorder.CENTER, TitledBorder.TOP));
        String[][] rec = {
                { User1.getName(), String.valueOf(User1.getScore())},
                { User2.getName(), String.valueOf(User2.getScore()) },

        };
        String[] header = { "Player", "Score"};
        JTable table = new JTable(rec, header);
        history.add(new JScrollPane(table));
        return(history);

    }

    public void reset(){
        board = new Board();
        if (pawnActive) {
            playBoardSquares[x1][y1].deactivate();
        }
    }


    public JPanel enterUser(int currentRound){
        gui.removeAll();
        history.removeAll();
        user.removeAll();
        message.setText("Round " + currentRound );

        //creating toolbar

        JLabel Username1 = new JLabel("Enter name of the Player 1: ");
        JLabel Username2 = new JLabel("Enter name of the Player 2: ");
        User1 = new JTextField(20);
        User2 = new JTextField(20);
        JButton rb = new JButton("Start the game");
        rb.addActionListener(new ReadInputButton());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        user.add(Username1, constraints);

        constraints.gridx = 1;
        user.add(User1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        user.add(Username2, constraints);

        constraints.gridx = 1;
        user.add(User2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        user.add(rb, constraints);

        // set border for the panel
        user.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Start the game Panel"));

        return(user);

    }



    public static void setMessage(String msg){
        message.setText(msg);
    }

    class ButtonPressed implements ActionListener{
        private final int x;
        private final int y;

        private State currentState;

        private final VoidState voidState;
        private final EmptyState emptyState;
        private final InactiveState inactiveState;
        private final ActiveState activeState;

        public ButtonPressed(int x, int y) {
            this.x = x;
            this.y = y;
            //initialise states
            this.voidState = new VoidState();
            this.emptyState = new EmptyState(this);
            this.inactiveState = new InactiveState(this);
            this.activeState = new ActiveState(this);

            this.currentState = voidState;
        }

        public void setState(State passedState) {
            this.currentState = passedState;
        }

        public State getVoidState() {return this.voidState;}
        public State getEmptyState() {return this.emptyState;}
        public State getInactiveState() {return this.inactiveState;}
        public State getActiveState() {return this.activeState;}

        @Override
        public void actionPerformed(ActionEvent e) {
            this.currentState.actionPerformed(e);
        }
    }

    interface State {
        void actionPerformed(ActionEvent e);
    }

    //white square pressed, mustn't be static
    class VoidState implements State {
        public VoidState() {}

        public void actionPerformed(ActionEvent e) {
            message.setText("Player" + launcher.currentPlayerName() + " please touch your pawns only!");
        }
    }

    //empty square pressed
    class EmptyState implements State {
        private final ButtonPressed buttonPressed;

        public EmptyState(ButtonPressed actionListener) {
            this.buttonPressed = actionListener;
        }

        public void actionPerformed(ActionEvent e) {
            if (pawnActive) {
                Move currentMove = new Move(x1, y1, buttonPressed.x, buttonPressed.y);
                if (board.getField(x1, y1).isMoveStored(currentMove)) {
                    currentMove.move(board);
                    playBoardSquares[x1][y1].deactivate();
                    pawnActive = false;
                    if (!RuleEvaluator.checkWinner(board)) {
                        launcher.nextMove();
                    }


                    else {
                        launcher.finishRound();
                    }

                }
                else {
                    message.setText("Player" + launcher.currentPlayerName()+ " this is not a valid move");
                }
            }
            else {
                message.setText("Player" + launcher.currentPlayerName() + " please touch your pawns only!");
            }
        }
    }

    class InactiveState implements State {
        private final ButtonPressed buttonPressed;

        public InactiveState(ButtonPressed buttonPressed) {
            this.buttonPressed = buttonPressed;
        }

        public void actionPerformed(ActionEvent e) {
            if(RuleEvaluator.checkInput(buttonPressed.x, buttonPressed.y)) {
                //deactivate the previously pressed button
                if (pawnActive) {
                    playBoardSquares[x1][y1].setInactiveAction();
                    playBoardSquares[x1][y1].deactivate();
                }
                //activate this button
                buttonPressed.setState(buttonPressed.getActiveState());
                playBoardSquares[buttonPressed.x][buttonPressed.y].activate();
                x1 = buttonPressed.x;
                y1 = buttonPressed.y;
                pawnActive = true;
                message.setText("Player " + launcher.currentPlayerName() + " please select target field!");
            }
            else {
                message.setText("Player " + launcher.currentPlayerName() + " please touch your pawns only!");
            }
        }
    }

    class ActiveState implements State{
        private final ButtonPressed buttonPressed;

        public ActiveState(ButtonPressed buttonPressed) {
            this.buttonPressed = buttonPressed;
        }

        public void actionPerformed(ActionEvent e) {
            buttonPressed.setState(buttonPressed.getInactiveState());
            playBoardSquares[buttonPressed.x][buttonPressed.y].deactivate();
            pawnActive = false;
            message.setText("Player " + launcher.currentPlayerName()+ " please enter your move");
        }
    }

    class ResetButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Launcher.reset();
        }
    }


    class NextRoundButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            launcher.nextRound();
        }
    }

    class ScoreTableButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            launcher.displayHistory();
        }
    }

    class BackButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            launcher.returnToGame();
        }
    }

    class ReadInputButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
        String name1 = User1.getText();
        String name2 = User2.getText();
        launcher.startGame(name1, name2);
        }

    }


}