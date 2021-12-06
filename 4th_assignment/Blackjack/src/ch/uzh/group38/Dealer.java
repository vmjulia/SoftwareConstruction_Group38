package ch.uzh.group38;

public class Dealer {

    private static Player player = new Player();
    private static int bet;

    private static void playGame(){
        do {
            bet = player.makeBet();
        } while (bet > player.getCashAmount());
        Table table = new Table();
        table.firstRound();
        table.playersTurn();
    }
    public static void main(String[] args){
        playGame();
    }
}
