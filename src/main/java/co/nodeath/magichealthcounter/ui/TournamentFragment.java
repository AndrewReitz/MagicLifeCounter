package co.nodeath.magichealthcounter.ui;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.inkapplications.preferences.BooleanPreference;
import com.squareup.otto.Bus;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.data.SeenTrackerDrawer;
import co.nodeath.magichealthcounter.ui.event.ActionBarTitleEvent;
import co.nodeath.magichealthcounter.ui.event.TrackerDrawerVisibilityEvent;
import co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent;
import co.nodeath.magichealthcounter.ui.misc.BaseActivity;

import static co.nodeath.magichealthcounter.ui.event.TrackerDrawerVisibilityEvent.TrackerDrawerVisibility.SHOW;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Value;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Value.MINUS_FIVE;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Value.MINUS_ONE;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Value.PLUS_FIVE;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Value.PLUS_ONE;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Who;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Who.ME;
import static co.nodeath.magichealthcounter.ui.event.UpdateScoreEvent.Who.YOU;

public final class TournamentFragment extends TwoPlayerFragment {

  @Inject @Standard ButterKnife.Action<TextView> setStandardLife;
  @Inject @Zero ButterKnife.Action<TextView> setZero;
  @Inject @Show ButterKnife.Action<View> showViews;
  @Inject @SeenTrackerDrawer BooleanPreference seenTrackerDrawer;
  @Inject @Main Handler handler;
  @Inject Activity activity;
  @Inject Bus bus;

  @InjectViews({R.id.me, R.id.you}) List<View> headers;

  public static TournamentFragment newInstance() {
    return new TournamentFragment();
  }

  @Override public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);
    ButterKnife.inject(this, view);

    return view;
  }

  @Override public void onResume() {
    super.onResume();
    bus.post(new ActionBarTitleEvent(getString(R.string.tournement)));
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    BaseActivity.get(this).inject(this);

    if (!seenTrackerDrawer.get()) {
      handler.postDelayed(new Runnable() {
        @Override public void run() {
          Toast.makeText(activity, getString(R.string.first_seen_match_hstory_drawer),
              Toast.LENGTH_LONG).show();
          bus.post(new TrackerDrawerVisibilityEvent(SHOW));
        }
      }, TimeUnit.MILLISECONDS.toMillis(500));
      seenTrackerDrawer.set(true);
    }

    ButterKnife.apply(scoreViews, setStandardLife);
    ButterKnife.apply(poisonCounters, setZero);
    ButterKnife.apply(headers, showViews);
  }

  @Override void themMinusOneClick() {
    super.themMinusOneClick();
    updateYouScore(MINUS_ONE);
  }

  @Override void themMinusFiveClick() {
    super.themMinusFiveClick();
    updateYouScore(MINUS_FIVE);
  }

  @Override void themPlusOneClick() {
    super.themPlusOneClick();
    updateYouScore(PLUS_ONE);
  }

  @Override void themPlusFiveClick() {
    super.themPlusFiveClick();
    updateYouScore(PLUS_FIVE);
  }

  @Override void meMinusOneClick() {
    super.meMinusOneClick();
    updateMeScore(MINUS_ONE);
  }

  @Override void meMinusFiveClick() {
    super.meMinusFiveClick();
    updateMeScore(MINUS_FIVE);
  }

  @Override void mePlusOneClick() {
    super.mePlusOneClick();
    updateMeScore(PLUS_ONE);
  }

  @Override void mePlusFiveClick() {
    super.mePlusFiveClick();
    updateMeScore(PLUS_FIVE);
  }

  private void updateYouScore(Value value) {
    bus.post(new UpdateScoreEvent(YOU, value));
  }

  private void updateMeScore(Value value) {
    bus.post(new UpdateScoreEvent(ME, value));
  }
}
