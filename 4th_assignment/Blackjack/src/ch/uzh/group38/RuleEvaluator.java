package ch.uzh.group38;

public class RuleEvaluator {

    private static Player player = new Player();
    private static int bet;

    private static void startGame(){
        do {
            bet = player.makeBet();
        } while (bet > player.getCashAmount());
        Table table = new Table();
        table.firstRound();
    }
    public static void main(String[] args){
        startGame();
    }  
}
