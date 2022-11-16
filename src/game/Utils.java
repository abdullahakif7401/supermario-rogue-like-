package game;

import java.util.Random;

/**
 * Utils Class manages some all the miscellaneous functions needed for quick calculations
 * Mainly includes random number generators - between different indexes
 * @author Danesh Mariapan
 */
public class Utils {

    /**
     * Large Random Number Generator (10000 - 99999)
     * @return Random Integer
     */
    public static int randomVal() {
        Random r = new Random();
        int low = 10000;
        int high = 99999;
        return (r.nextInt(high - low) + low);
    }

    /**
     * Random Number Generator (Between indexes 0 - 3)
     * @return Random Integer
     */
    public static int randomIndexFull() {
        Random r = new Random();
        int low = 0;
        int high = 3;
        return (r.nextInt(high - low + 1) + low);
    }

    /**
     * Random Number Generator (Between indexes 0 - 2)
     * @return Random Integer
     */
    public static int randomIndexThree() {
        Random r = new Random();
        int low = 0;
        int high = 2;
        return (r.nextInt(high - low + 1) + low);
    }

    /**
     * Random Number Generator (Between indexes 0 - 1)
     * @return Random Integer
     */
    public static int randomIndexTwo() {
        Random r = new Random();
        int low = 0;
        int high = 1;
        return (r.nextInt(high - low + 1) + low);
    }

}