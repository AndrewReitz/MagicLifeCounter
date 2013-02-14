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

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.RandomSingleton;

/**
 * Controller for coin flip dialogs
 *
 * @author pieces029
 */
public class CoinFlipController extends AbstractDialogFragmentController {

    public CoinFlipController(final Fragment fragment) {
        super.onCreate(fragment);
    }

    /**
     * @return dialog populated with heads or tails
     */
    public Dialog getCoinFlipAlert() {
        final Context context = mFragment.getActivity();
        final int flipNum = RandomSingleton.getInstance().getRandom(2);
        String flipMessage = context.getString(R.string.heads);

        if (flipNum == 0) {
            flipMessage = context.getString(R.string.tails);
        }

        final String title = context.getString(R.string.coin_flip);

        return getDialog(context, title, flipMessage);
    }
}
