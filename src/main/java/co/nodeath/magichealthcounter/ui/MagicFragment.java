package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.InjectViews;
import butterknife.OnClick;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.misc.BaseFragment;
import hugo.weaving.DebugLog;

abstract class MagicFragment extends BaseFragment {

  /** Move These to Dagger? */
  static final ButterKnife.Action<View> FLIP_VIEWS_180_DEGREES = new ButterKnife.Action<View>() {
    @Override public void apply(View view, int index) {
      view.setRotation(180);
    }
  };

  static final ButterKnife.Action<View> SHOW_VIEWS = new ButterKnife.Action<View>() {
    @Override public void apply(View view, int index) {
      view.setVisibility(View.VISIBLE);
    }
  };

  static final ButterKnife.Action<View> HIDE_VIEWS = new ButterKnife.Action<View>() {
    @Override public void apply(View view, int index) {
      view.setVisibility(View.GONE);
    }
  };

  static final ButterKnife.Action<TextView> SET_STANDARD_LIFE = new ButterKnife.Action<TextView>() {
    @Override public void apply(TextView view, int index) {
      view.setTag(20);
      view.setText("20");
    }
  };

  static final ButterKnife.Action<TextView> SET_COMMANDER_LIFE = new ButterKnife.Action<TextView>() {
    @Override public void apply(TextView view, int index) {
      view.setTag(40);
      view.setText("40");
    }
  };

  static final ButterKnife.Action<TextView> SET_ZERO = new ButterKnife.Action<TextView>() {
    @Override public void apply(TextView view, int index) {
      view.setTag(0);
      view.setText("0");
    }
  };

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

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_main, container, false);
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
    textView.setText(String.valueOf(result));
    textView.setTag(result);
  }
}
