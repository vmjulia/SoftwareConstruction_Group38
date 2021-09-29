public class MarkChristmas {
    public static void main(String[] args) {
        intro(0);
    }   
    
    public static int intro(int i){
        String[] intro = {"On the ", " day of Christmas, my true love sent to me:"};

        String[] day = {"first", "second", "third", "fourth", "fifth", "sixth",
                "seventh", "eighth", "ninth", "tenth", "eleventh", "twelfth"};
        
        if (i >= 12){
            return 0;
        }else{
            System.out.println(intro[0] + day[i] + intro[1]);
            lyrics(11-i);
            System.out.println();
            return intro(i+1);
        }
    }
    public static int lyrics(int j){

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
        
        if (j >= 12){
            return 0;
        }else{
            System.out.println(lyrics[j]);
            return lyrics(j+1);
        }
    }
}
