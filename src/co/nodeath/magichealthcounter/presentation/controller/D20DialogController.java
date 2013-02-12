package co.nodeath.magichealthcounter.presentation.controller;

import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.RandomSingleton;

/**
 * Controller for D20Dialogs
 *
 * @author pieces029
 */
public class D20DialogController extends AbstractDialogFragmentController {

    public D20DialogController(final Fragment fragment) {
        super.onCreate(fragment);
    }

    /**
     * @return dialog populated with random dice role
     */
    public Dialog getD20Dialog() {

        final Context context = mFragment.getActivity();

        //(0 -> 19) + 1 = (1 -> 20)
        final int d20Roll = RandomSingleton.getInstance().getRandom(20) + 1;
        final String title = context.getString(R.string.d20_roll);
        final String message = String.format(context.getString(R.string.you_rolled, d20Roll));

        return getDialog(context, title, message);
    }
}
