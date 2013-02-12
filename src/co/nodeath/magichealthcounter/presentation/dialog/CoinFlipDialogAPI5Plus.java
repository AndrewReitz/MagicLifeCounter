package co.nodeath.magichealthcounter.presentation.dialog;

import android.os.Bundle;

import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;

import co.nodeath.magichealthcounter.presentation.controller.CoinFlipController;

/**
 * @author pieces029
 */
public class CoinFlipDialogAPI5Plus extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final CoinFlipController coinFlipController = new CoinFlipController(this);
        return (Dialog) coinFlipController.getCoinFlipAlert();
    }
}
