package co.nodeath.magichealthcounter.presentation.controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;

/**
 * All dialog fragment controllers should extend this class
 *
 * @author pieces029
 */
public class AbstractDialogFragmentController extends AbstractFragmentController {

    /**
     * Creates a dialog based off of the API level
     *
     * @param context context of the current fragment
     * @param title   title to show on the dialog
     * @param message message to show in the dialog
     * @return dialog created with the message and title passed in based off the phones api
     */
    public Dialog getDialog(final Context context, final String title,
            final String message) {
        final String ok = context.getString(android.R.string.ok);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.DONUT) {
            final org.holoeverywhere.app.AlertDialog ad
                    = new org.holoeverywhere.app.AlertDialog.Builder(context)
                    .setTitle(title)
                    .setPositiveButton(ok, null)
                    .setMessage(message)
                    .create();
            return ad;
        } else {
            final AlertDialog ad = new AlertDialog.Builder(context)
                    .setTitle(title)
                    .setPositiveButton(ok, null)
                    .setMessage(message)
                    .create();

            return ad;
        }
    }
}
