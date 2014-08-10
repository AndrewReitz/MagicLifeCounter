package co.nodeath.magichealthcounter.ui;

import android.app.Activity;
import android.content.Context;

import co.nodeath.magichealthcounter.MagicLifeCounterModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.google.common.base.Preconditions.checkNotNull;

@Module(
    injects = {
        MainActivity.class,
        TournamentFragment.class
    },
    addsTo = MagicLifeCounterModule.class,
    library = true
)
public class ActivityModule {
  private final Activity activity;

  public ActivityModule(Activity activity) {
    this.activity = checkNotNull(activity);
  }

  @Provides @Singleton Activity provideActivityContext() {
    return activity;
  }
}
