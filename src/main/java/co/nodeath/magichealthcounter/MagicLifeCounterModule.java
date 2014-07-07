package co.nodeath.magichealthcounter;

import android.app.Application;

import com.squareup.otto.Bus;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import co.nodeath.magichealthcounter.data.DataModule;
import co.nodeath.magichealthcounter.ui.CasualFragment;
import co.nodeath.magichealthcounter.ui.MainActivity;
import co.nodeath.magichealthcounter.ui.TournamentFragment;
import co.nodeath.magichealthcounter.ui.UiModule;
import dagger.Module;
import dagger.Provides;

@Module(
    includes = {
        UiModule.class,
        DataModule.class
    },
    injects = {
        MagicLifeCounterApp.class,
        MainActivity.class,
        TournamentFragment.class,
        CasualFragment.class
    }
)
public final class MagicLifeCounterModule {
  private final MagicLifeCounterApp app;

  public MagicLifeCounterModule(@NotNull MagicLifeCounterApp app) {
    this.app = app;
  }

  @Provides @Singleton Application provideApplication() {
    return app;
  }

  @Provides @Singleton Bus provideBus() {
    return new Bus();
  }
}
