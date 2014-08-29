package co.nodeath.magichealthcounter.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import javax.inject.Inject;

import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.data.CoinFlipper;
import co.nodeath.magichealthcounter.ui.misc.BaseDialogFragment;

import static co.nodeath.magichealthcounter.data.CoinFlipper.CoinFlip.HEADS;

public final class CoinFlipDialog extends BaseDialogFragment {
  @Inject CoinFlipper coinFlipper;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    MagicLifeCounterApp.get(getActivity()).inject(this);
  }

  @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    final String title = getString(R.string.coin_flip);
    final String message = getString(coinFlipper.flipCoin() == HEADS ?
        R.string.heads : R.string.tails);
    final String ok = getString(android.R.string.ok);

    return new AlertDialog.Builder(getActivity())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton(ok, null)
        .create();
  }
}
