package co.nodeath.magichealthcounter;

import android.app.Application;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.github.fernandodev.easyratingdialog.library.EasyRatingDialog;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import co.nodeath.magichealthcounter.data.DataModule;
import co.nodeath.magichealthcounter.ui.CasualFragment;
import co.nodeath.magichealthcounter.ui.CoinFlipDialog;
import co.nodeath.magichealthcounter.ui.D20Dialog;
import co.nodeath.magichealthcounter.ui.MainActivity;
import co.nodeath.magichealthcounter.ui.SettingsFragment;
import co.nodeath.magichealthcounter.ui.TournamentFragment;
import co.nodeath.magichealthcounter.ui.UiModule;
import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(
    includes = {
        UiModule.class,
        DataModule.class
    },
    injects = {
        MagicLifeCounterApp.class,
        SettingsFragment.class,
        D20Dialog.class,
        CoinFlipDialog.class
    },
    library = true
)
public final class MagicLifeCounterModule {
  private final MagicLifeCounterApp app;

  public MagicLifeCounterModule(@NonNull MagicLifeCounterApp app) {
    this.app = app;
  }

  @Provides @Singleton Application provideApplication() {
    return app;
  }

  @Provides @Singleton Bus provideBus() {
    return new Bus();
  }

  @Provides @Singleton EasyRatingDialog provideRatingDailog() {
    return new EasyRatingDialog(app);
  }

  @Provides @Singleton Tracker provideTracker() {
    GoogleAnalytics analytics = GoogleAnalytics.getInstance(app);
    return analytics.newTracker(BuildConfig.GA_TRACKING_ID);
  }

  @Provides @Singleton Analytics provideAnalytics(Tracker tracker) {
    return new Analytics.GoogleAnalytics(tracker);
  }
}
