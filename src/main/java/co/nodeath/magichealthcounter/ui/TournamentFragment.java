package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;

public final class TournamentFragment extends TwoPlayerFragment {

  @Inject @Standard ButterKnife.Action<TextView> setStandardLife;
  @Inject @Zero ButterKnife.Action<TextView> setZero;
  @Inject @Show ButterKnife.Action<View> showViews;
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
}
