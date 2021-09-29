public class NikitaChristmas {
    public static void main(String[] args) {

        //definitions:

        String[] lyrics = {"Twelve drummers drumming,",
                "Eleven pipers piping,",
                "Ten lords a-leaping,",
                "Nine ladies dancing,",
                "Eight maids a-milking,",
                "Seven swans a-swimming,",
                "Six geese a-laying,",
                "Five golden rings,",
                "Four calling birds,",
                "Three french hens,",
                "Two turtle doves, and",
                "A partridge in a pear tree."};

        String[] intro = {"On the ", " day of Christmas, my true love sent to me:"};

        String[] day = {"first", "second", "third", "fourth", "fifth", "sixth",
                "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};

        //execution:

        for (int i = 0; i < 12; i++) {
            System.out.println(intro[0] + day[i] + intro[1]);

            for (int j = 11 - i; j <= 11; j++) {
                System.out.println(lyrics[j]);
            }
            System.out.println();
        }

    }
}
