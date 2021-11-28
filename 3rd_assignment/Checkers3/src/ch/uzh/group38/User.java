package ch.uzh.group38;

public class User {
    private final String UserName ;
    private int score = 0;

    public  User (String name){
        this.UserName = name;
    }

    public String getName(){
        return (UserName);
    }

    public int getScore(){
        return (score);
    }
    public void increaseScore(){
        score += 1;
    }
}
