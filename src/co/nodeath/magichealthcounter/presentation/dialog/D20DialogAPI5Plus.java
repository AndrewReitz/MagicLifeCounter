package co.nodeath.magichealthcounter.presentation.dialog;

import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;

import android.os.Bundle;

import co.nodeath.magichealthcounter.presentation.controller.D20DialogController;

/**
 * @author pieces029
 */
public class D20DialogAPI5Plus extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final D20DialogController d20DialogController = new D20DialogController(this);
        return (Dialog) d20DialogController.getD20Dialog();
    }
}
