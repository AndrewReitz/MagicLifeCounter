package co.nodeath.magichealthcounter.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.event.ClearScoreEvent;
import co.nodeath.magichealthcounter.ui.misc.BaseFragment;
import hugo.weaving.DebugLog;

abstract class TwoPlayerFragment extends BaseFragment {

  @Inject FragmentManager fragmentManager;
  @Inject Bus bus;

  @InjectViews({
      R.id.them_minus_1,
      R.id.them_minus_5,
      R.id.them_plus_1,
      R.id.them_plus_5,
      R.id.them_score,
      R.id.them_poison_counter
  }) List<View> themViews;

  @InjectViews({
      R.id.them_poison_counter,
      R.id.me_poison_counter
  }) List<TextView> poisonCounters;

  @InjectViews({
      R.id.them_score,
      R.id.me_score
  }) List<TextView> scoreViews;

  @InjectView(R.id.them_score) TextView themScore;
  @InjectView(R.id.me_score) TextView meScore;
  @InjectView(R.id.them_poison_counter) TextView themPoisonCounter;
  @InjectView(R.id.me_poison_counter) TextView mePoisonCounter;

  private static final ClearScoreEvent ClearScoreEvent = new ClearScoreEvent();

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_magic, menu);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_flip_coin:
        new CoinFlipDialog().show(fragmentManager, "coinflip");
        break;
      case R.id.action_roll_die:
        new D20Dialog().show(fragmentManager, "D20");
        break;
      case R.id.raction_reset:
        setText(themScore, 20);
        setText(meScore, 20);
        bus.post(ClearScoreEvent);
        break;
      default:
        throw new IllegalStateException("Unknown or unhandled menu id");
    }

    return true;
  }

  @DebugLog
  @OnClick(R.id.them_minus_1) void themMinusOneClick() {
    updateText(themScore, -1);
  }

  @DebugLog
  @OnClick(R.id.them_minus_5) void themMinusFiveClick() {
    updateText(themScore, -5);
  }

  @DebugLog
  @OnClick(R.id.them_plus_1) void themPlusOneClick() {
    updateText(themScore, 1);
  }

  @DebugLog
  @OnClick(R.id.them_plus_5) void themPlusFiveClick() {
    updateText(themScore, 5);
  }

  @DebugLog
  @OnClick(R.id.me_minus_1) void meMinusOneClick() {
    updateText(meScore, -1);
  }

  @DebugLog
  @OnClick(R.id.me_minus_5) void meMinusFiveClick() {
    updateText(meScore, -5);
  }

  @DebugLog
  @OnClick(R.id.me_plus_1) void mePlusOneClick() {
    updateText(meScore, 1);
  }

  @DebugLog
  @OnClick(R.id.me_plus_5) void mePlusFiveClick() {
    updateText(meScore, 5);
  }

  @DebugLog
  void updateText(TextView textView, int value) {
    int currentVal = (int) textView.getTag();
    int result = currentVal + value;
    setText(textView, result);
  }

  void setText(TextView textView, int value) {
    textView.setText(String.valueOf(value));
    textView.setTag(value);
  }
}
