package co.nodeath.magichealthcounter;

import java.util.Calendar;
import java.util.Random;

/**
 * Class used to keep one instance of random around for the app instead of instantiating it over and
 * over again
 */
public class RandomSingleton {

    private static RandomSingleton instance;
    private static Random mRandom;

    private RandomSingleton() {
        mRandom = new Random(Calendar.getInstance().getTimeInMillis());
    }

    /**
     * Get an Instance of RandomSingleton
     *
     * @return RandomSingleton
     */
    public static RandomSingleton getInstance() {
        if (instance == null) {
            instance = new RandomSingleton();
        }

        return instance;
    }

    /**
     * Get a random value 0 -> n-1
     *
     * @param n maximum value
     * @return random integer 0 through n-1
     */
    public int getRandom(int n) {
        return mRandom.nextInt(n);
    }
}
