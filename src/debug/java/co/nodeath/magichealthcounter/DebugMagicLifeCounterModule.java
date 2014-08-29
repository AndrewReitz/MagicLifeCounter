package co.nodeath.magichealthcounter;

import javax.inject.Singleton;

import co.nodeath.magichealthcounter.data.DebugDataModule;
import co.nodeath.magichealthcounter.ui.DebugUiModule;
import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module(
    addsTo = MagicLifeCounterModule.class,
    includes = {
        DebugUiModule.class,
        DebugDataModule.class
    },
    overrides = true,
    library = true
)
public final class DebugMagicLifeCounterModule {
  @Provides @Singleton Analytics provideAnalytics() {
    return new Analytics() {
      @Override public void screen(String screenName, Object... args) {
        Timber.d("Logging Analytics Screen: %s", String.format(screenName, args));
      }
    };
  }
}
