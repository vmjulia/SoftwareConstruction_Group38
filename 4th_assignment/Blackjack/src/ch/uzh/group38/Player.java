package ch.uzh.group38;

public class Player {
    private int cash = 100;

    public int makeBet(){
        System.out.println("current chash: " + this.cash + "\nHow much would you like to bet? ");
        return this.readInput();
    }

    public int readInput(){        
        do{
            String input = System.console().readLine();
            try {
                int i = Integer.parseInt(input);
                return i;
            } catch (Exception NumberFormatException) {
                System.out.println("Invalid input! Please give an input of type integer");
            }
        }while (true);
    }

    public int getCashAmount(){
        return cash;
    }

    public void increaseCash(int c){
        this.cash += c;
    }

    public void pay(int c){
        this.cash -= c;
    }
    
}
