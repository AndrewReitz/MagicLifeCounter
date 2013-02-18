/*
 *      Copyright (C) 2012-2013 Pieces029
 *
 *  This Program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2, or (at your option)
 *  any later version.
 *
 *  This Program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with MagicHealthCounter; see the file license.  If not, write to
 *  the Free Software Foundation, 675 Mass Ave, Cambridge, MA 02139, USA.
 *  http://www.gnu.org/copyleft/gpl.html
 *
 */

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
     * @param n positive integer, maximum value
     * @return random integer 0 through n-1
     */
    public int getRandom(int n) {
        return mRandom.nextInt(n);
    }
}
