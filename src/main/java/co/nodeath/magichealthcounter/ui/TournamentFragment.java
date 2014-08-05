package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

import static co.nodeath.magichealthcounter.ui.event.TrackerDrawerVisibilityEvent.TrackerDrawerVisibility.SHOW;

public final class TournamentFragment extends TwoPlayerFragment {

  @Inject @Standard ButterKnife.Action<TextView> setStandardLife;
  @Inject @Zero ButterKnife.Action<TextView> setZero;
  @Inject @Show ButterKnife.Action<View> showViews;
  @Inject @SeenTrackerDrawer BooleanPreference seenTrackerDrawer;
  @Inject @Main Handler handler;
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
    MagicLifeCounterApp.get(getActivity()).inject(this);

    ButterKnife.apply(scoreViews, setStandardLife);
    ButterKnife.apply(poisonCounters, setZero);
    ButterKnife.apply(headers, showViews);

    return view;
  }

  @Override public void onResume() {
    super.onResume();
    bus.post(new ActionBarTitleEvent(getString(R.string.tournement)));
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    if (!seenTrackerDrawer.get()) {
      handler.postDelayed(new Runnable() {
        @Override public void run() {
          bus.post(new TrackerDrawerVisibilityEvent(SHOW));
        }
      }, TimeUnit.SECONDS.toMillis(1));
      seenTrackerDrawer.set(true);
    }
  }
}
