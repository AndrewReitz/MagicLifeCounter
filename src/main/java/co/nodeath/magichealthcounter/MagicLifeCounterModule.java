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

  /**
   * Allow the app context to be injected but require that it be annotated with
   * {@link ForApplication}
   * to explicitly differentiate it from an activity context.
   */
  @Provides @Singleton @ForApplication Application provideApplication() {
    return app;
  }

  @Provides @Singleton Bus provideBus() {
    return new Bus();
  }
}
