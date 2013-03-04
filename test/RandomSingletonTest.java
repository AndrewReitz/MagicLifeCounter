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

import org.junit.Test;

import co.nodeath.magichealthcounter.RandomSingleton;

import static org.junit.Assert.assertTrue;


/**
 * @author pieces029
 */
public class RandomSingletonTest {

    @Test
    public void shouldReturnAValueBetweenZeroAndN() {
        RandomSingleton r = RandomSingleton.getInstance();

        for (int i = 0; i < 100; i++) {
            int actual = r.getRandom(20);

            assertTrue(actual < 20);
            assertTrue(actual > -1);
        }
    }
}