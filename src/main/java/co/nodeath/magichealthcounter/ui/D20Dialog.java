package co.nodeath.magichealthcounter.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.data.DieRoller;
import co.nodeath.magichealthcounter.ui.misc.BaseActivity;
import co.nodeath.magichealthcounter.ui.misc.BaseDialogFragment;

public final class D20Dialog extends BaseDialogFragment {

  @Inject Activity activity;
  @Inject DieRoller dieRoller;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    BaseActivity.get(this).inject(this);
  }

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    final String title = getString(R.string.d20_roll);
    final String ok = getString(android.R.string.ok);
    final String message = String.format(getString(R.string.you_rolled), dieRoller.rollDie(1, 20));

    return new AlertDialog.Builder(activity)
        .setTitle(title)
        .setPositiveButton(ok, null)
        .setMessage(message)
        .create();
  }
}
