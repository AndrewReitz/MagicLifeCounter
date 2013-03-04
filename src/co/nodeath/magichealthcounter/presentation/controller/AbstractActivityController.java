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

package co.nodeath.magichealthcounter.presentation.controller;

import android.support.v4.app.FragmentActivity;

/**
 * All activity controllers should extend this
 *
 * @author pieces029
 */
public abstract class AbstractActivityController {

    public FragmentActivity mActivity;

    /**
     * Call this in order to save the activity this is here instead of in the constructor because not
     * all controllers will need to save the activity
     *
     * @param activity activity to save
     */
    public void onCreate(final FragmentActivity activity) {
        mActivity = activity;
    }
}
