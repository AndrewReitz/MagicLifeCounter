package co.nodeath.magichealthcounter.presentation.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import co.nodeath.magichealthcounter.presentation.controller.CoinFlipController;

/**
 * @author pieces029
 */
public class CoinFlipDialogAPI4Minus extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final CoinFlipController coinFlipController = new CoinFlipController(this);
        return coinFlipController.getCoinFlipAlert();
    }
}
