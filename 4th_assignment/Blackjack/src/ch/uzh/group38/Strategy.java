package ch.uzh.group38;

import java.util.Scanner;

public interface Strategy {
    public boolean hit(int score);
}

class PlayerStrategy implements Strategy{

    @Override
    public boolean hit(int score){
        System.out.println("hit or stay? [H/S] ");
        String input = readHitOrStayInput();
        switch (input) {
            case "s": break;
            case "h": return true;
        }
        return false;
    }

    private String readHitOrStayInput() {
        while (true) {
            String input = new Scanner(System.in).nextLine().toLowerCase();
            if (input.equals("h") || input.equals("s")){
                return input;
            }
            System.out.println("Invalid input! Please choose [H] or [S]");
        }
    }
}

class DealerStrategy implements Strategy{
    @Override
    public boolean hit(int score){
        if (score < 17) {
            System.out.println("Dealer hits\n");
            return true;
        }
        return false;
    }
}
