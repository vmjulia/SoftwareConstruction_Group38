package ch.uzh.group38;

import java.io.IOException;
import java.util.Scanner;

public interface HitBehaviour {
    boolean hit(String input, int score);
}

class PlayerStrategy implements HitBehaviour {
    @Override
    public boolean hit(String input, int score) {
        System.out.println("hit or stay? [H/S] ");
        switch (input) {
            case "s":
                break;
            case "h":
                return true;
        }
        return false;
    }
}

class DealerHitBehaviour implements HitBehaviour {
    @Override
    public boolean hit(String input, int score) {
        if (score < 17) {
            System.out.println("Dealer hits\n");
            return true;
        }
        return false;
    }
}
