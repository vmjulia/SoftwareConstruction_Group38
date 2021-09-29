import java.util.Scanner;

class LaniChristmas{

    // will print strophe n of the song (n has to be 1 - 12)
    public static void strophe(int n){

        // an invalid n is entered
        if(n<1 || n>12){
            System.out.println("Invalid number");
            return;
        }

        // the first strophe as a special case
        if(n==1){
            System.out.println("On the first day of Christmas,");
            System.out.println("my true love sent to me");
            System.out.println("A partridge in a pear tree.");
            return;
        }

        // secon to twelfth strophe

        String[] day = {"second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};
        String[] gift = {"And a partridge in a pear tree.",
                "Two turtle doves,",
                "Three French hens,",
                "Four calling birds,",
                "Five golden rings,",
                "Six geese a-laying,",
                "Seven swans a-swimming,",
                "Eight maids a-milking,",
                "Nine ladies dancing,",
                "Ten lords a-leaping,",
                "Eleven pipers piping,",
                "Twelve drummers drumming,"};

        System.out.println("On the " + day[n-2] + " day of Christmas,");
        System.out.println("my true love sent to me");

        for(int i=n-1 ; i>=0 ; i--){
            System.out.println(gift[i]);
        }

    }

    public static void main(String[] args){

        Scanner s = new Scanner(System.in);

        System.out.println("Enter a number from 1 to 12:");
        int n = s.nextInt();

        strophe(n);

    }

}
