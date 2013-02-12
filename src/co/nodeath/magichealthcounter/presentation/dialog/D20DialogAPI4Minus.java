package co.nodeath.magichealthcounter.presentation.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import co.nodeath.magichealthcounter.presentation.controller.D20DialogController;

/**
 * @author pieces029
 */
public class D20DialogAPI4Minus extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final D20DialogController d20DialogController = new D20DialogController(this);
        return d20DialogController.getD20Dialog();
    }
}