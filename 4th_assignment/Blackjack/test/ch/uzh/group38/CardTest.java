package ch.uzh.group38;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardTest {
    @Test

public void testGetters(){
    for (Suit s : Suit.values()){
        for (Rank r : Rank.values()){
            Card c = new Card(r, s);
            assertEquals(c.getRank(), r);
            if (r==Rank.ACE){
            assertEquals(c.getValue(),11);
            }
            else if (r==Rank.TWO){
                assertEquals(c.getValue(),2);
            }
            else if (r==Rank.THREE){
                assertEquals(c.getValue(),3);
            }
            else if (r==Rank.FOUR){
                assertEquals(c.getValue(),4);
            }
        }
    }
    }

    @Test

    public void testDisplay(){
        for (Suit s : Suit.values()){
            for (Rank r : Rank.values()){
                Card c = new Card(r, s);
                String output  = "[" + r + " of " + s + "]";
                assertEquals(c.display(), output);
            }
        }
    }
}