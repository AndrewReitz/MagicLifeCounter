package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.nodeath.magichealthcounter.ui.misc.BaseFragment;

public final class MultiPlayerFragment extends BaseFragment {


  public static MultiPlayerFragment newInstance() {
    return new MultiPlayerFragment();
  }

  @Override public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = super.onCreateView(inflater, container, savedInstanceState);

    return view;
  }
}
