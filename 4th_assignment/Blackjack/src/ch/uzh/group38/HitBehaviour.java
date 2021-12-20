package ch.uzh.group38;


public interface HitBehaviour {
    boolean hit(String input, int score);
}

class PlayerHitBehaviour implements HitBehaviour {
    @Override
    public boolean hit(String input, int score) {
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
