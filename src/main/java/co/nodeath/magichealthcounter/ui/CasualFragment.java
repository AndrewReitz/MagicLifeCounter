package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectViews;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.misc.BaseFragment;

import static butterknife.ButterKnife.Action;

public class CasualFragment extends BaseFragment {

  static final Action<View> FLIPVIEW = new Action<View>() {
    @Override public void apply(View view, int index) {
      view.setRotation(180);
    }
  };

  @InjectViews({
      R.id.them_minus_1,
      R.id.them_minus_5,
      R.id.them_plus_1,
      R.id.them_plus_5,
      R.id.them_score
  }) List<View> themViews;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_casual, container, false);
    ButterKnife.inject(this, view);

    ButterKnife.apply(themViews, FLIPVIEW);

    return view;
  }
}
