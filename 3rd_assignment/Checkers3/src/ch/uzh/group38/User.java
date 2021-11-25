package ch.uzh.group38;

public class User {
    private static String name;
    private int score;

    public User (String name){
        this.name = name;
    }

    public static String getName(){
        return (name);

    }
    public void increaseScore(){
        score = score+1;
    }
}
