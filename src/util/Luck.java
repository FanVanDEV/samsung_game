package util;

import java.util.Random;

public class Luck {
    private static final Random rand = new Random();

    public static boolean chance(int chance) {
//        if (chance < 0 || chance > 100 )
//            throw new Exception();

        int random = rand.nextInt(100) + 1;

        return random <= chance;
    }
}
