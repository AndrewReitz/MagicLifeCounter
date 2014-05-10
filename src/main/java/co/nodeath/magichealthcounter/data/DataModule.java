package co.nodeath.magichealthcounter.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.inkapplications.preferences.BooleanPreference;

import javax.inject.Singleton;

import co.nodeath.magichealthcounter.ForApplication;
import dagger.Module;
import dagger.Provides;

@Module(
    complete = false,
    library = true
)
public final class DataModule {
  private static final boolean DEFAULT_SEEN_NAVIGATION_DRAWER = false;

  @Provides @Singleton SharedPreferences provideSharedPreferences(@ForApplication Application app) {
    return PreferenceManager.getDefaultSharedPreferences(app);
  }

  @Provides @Singleton @SeenNavDrawer
  BooleanPreference provideSeenNavDrawer(SharedPreferences sharedPreferences) {
    return new BooleanPreference(
        sharedPreferences, "seen_navigation_drawer", DEFAULT_SEEN_NAVIGATION_DRAWER
    );
  }
}
