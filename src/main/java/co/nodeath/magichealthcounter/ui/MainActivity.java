package co.nodeath.magichealthcounter.ui;

import android.os.Bundle;
import android.view.ViewGroup;

import co.nodeath.magichealthcounter.R;
import co.nodeath.magichealthcounter.MagicLifeCounterApp;
import co.nodeath.magichealthcounter.ui.AppContainer;
import co.nodeath.magichealthcounter.ui.misc.BaseActivity;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {

    @Inject AppContainer appContainer;

    private ViewGroup container;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MagicLifeCounterApp app = MagicLifeCounterApp.get(this);

        container = appContainer.get(this, app);

        getLayoutInflater().inflate(R.layout.activity_main, container);
    }
}
