package co.nodeath.magichealthcounter.ui;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inkapplications.preferences.BooleanPreference;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.data.DarkTheme;
import co.nodeath.magichealthcounter.ui.event.ClearScoreEvent;
import co.nodeath.magichealthcounter.ui.misc.BaseFragment;
import hugo.weaving.DebugLog;
import icepick.Icicle;

import static butterknife.ButterKnife.Action;

abstract class TwoPlayerFragment extends BaseFragment {

  @Inject @Standard Action<TextView> setStandardLife;
  @Inject @Zero Action<TextView> setZero;
  @Inject @Show Action<View> showViews;
  @Inject @Hide Action<View> hideViews;
  @Inject @DarkTheme BooleanPreference darkTheme;
  @Inject FragmentManager fragmentManager;
  @Inject Bus bus;

  @InjectViews({
      R.id.them_minus_1,
      R.id.them_minus_5,
      R.id.them_plus_1,
      R.id.them_plus_5,
      R.id.them_score,
      R.id.them_poison_counter,
      R.id.them_poison_minus,
      R.id.them_poison_plus
  }) List<View> themViews;

  @InjectViews({
      R.id.them_poison_counter,
      R.id.me_poison_counter
  }) List<TextView> poisonCounters;

  @InjectViews({
      R.id.them_score,
      R.id.me_score
  }) List<TextView> scoreViews;

  @InjectViews({
      R.id.me_poison_counter,
      R.id.me_poison_minus,
      R.id.me_poison_plus,
      R.id.them_poison_counter,
      R.id.them_poison_minus,
      R.id.them_poison_plus
  }) List<TextView> poisonViews;

  @InjectView(R.id.them_score) TextView themScore;
  @InjectView(R.id.me_score) TextView meScore;
  @InjectView(R.id.them_poison_counter) TextView themPoisonCounter;
  @InjectView(R.id.me_poison_counter) TextView mePoisonCounter;

  @Icicle boolean showingPoison = false;

  private static final ClearScoreEvent ClearScoreEvent = new ClearScoreEvent();

  private MenuItem poisonToggle;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
  }

  @Override public void onPause() {
    super.onPause();
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu_magic, menu);
    poisonToggle = menu.findItem(R.id.action_poison);
    if (showingPoison) {
      setPoisonText(R.string.hide_poision_counters, showViews);
    } else {
      setPoisonText(R.string.show_poision_counters, hideViews);
    }

    if (darkTheme.get()) {
      menu.findItem(R.id.action_flip_coin).setIcon(R.drawable.ic_action_coin_dark);
      menu.findItem(R.id.action_roll_die).setIcon(R.drawable.ic_action_d20_dark);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_flip_coin:
//        dialog = new CoinFlipDialog();
//        dialog.show(fragmentManager, "coinflip");
        new CoinFlipDialog().show(fragmentManager, "coinflip");
        break;
      case R.id.action_roll_die:
//        dialog = new D20Dialog();
//        dialog.show(fragmentManager, "D20");
        new D20Dialog().show(fragmentManager, "D20");
        break;
      case R.id.action_poison:
        togglePoisonCounters();
        break;
      case R.id.raction_reset:
        ButterKnife.apply(scoreViews, setStandardLife);
        ButterKnife.apply(poisonCounters, setZero);
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
  @OnClick(R.id.them_poison_plus) void themPoisonPlus() {
    updateText(themPoisonCounter, 1);
  }

  @DebugLog
  @OnClick(R.id.them_poison_minus) void themPoisonMinus() {
    updateText(themPoisonCounter, -1);
  }

  @DebugLog
  @OnClick(R.id.me_poison_plus) void mePoisionPlus() {
    updateText(mePoisonCounter, 1);
  }

  @DebugLog
  @OnClick(R.id.me_poison_minus) void mePoisionMinus() {
    updateText(mePoisonCounter, -1);
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

  void togglePoisonCounters() {
    if (showingPoison) {
      setPoisonText(R.string.show_poision_counters, hideViews);
    } else {
      setPoisonText(R.string.hide_poision_counters, showViews);
    }
    showingPoison = !showingPoison;
  }

  void setPoisonText(int textId, Action<View> butterKnifeAction) {
    poisonToggle.setTitle(getText(textId));
    ButterKnife.apply(poisonViews, butterKnifeAction);
  }
}
