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
