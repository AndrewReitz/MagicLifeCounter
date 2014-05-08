package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.ui.misc.BaseActivity;

import static butterknife.ButterKnife.findById;

public class MainActivity extends BaseActivity {

  @Inject AppContainer appContainer;

  private ViewGroup container;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    MagicLifeCounterApp app = MagicLifeCounterApp.get(this);

    container = appContainer.get(this, app);

    getLayoutInflater().inflate(R.layout.activity_main, container);

    ViewGroup drawer = findById(this, R.id.navigation_drawer);
    LayoutInflater.from(this).inflate(R.layout.drawer_layout, drawer);

    ButterKnife.inject(this);
  }
}
