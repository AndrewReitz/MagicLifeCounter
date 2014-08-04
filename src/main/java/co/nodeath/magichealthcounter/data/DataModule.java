package co.nodeath.magichealthcounter.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.inkapplications.preferences.BooleanPreference;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
    complete = false,
    library = true
)
public final class DataModule {
  private static final boolean DEFAULT_SEEN_NAVIGATION_DRAWER = false;
  private static final boolean DEFAULT_SEEN_TRACKER_DRAWER = false;

  @Provides @Singleton SharedPreferences provideSharedPreferences(Application app) {
    return PreferenceManager.getDefaultSharedPreferences(app);
  }

  @Provides @Singleton @SeenNavDrawer
  BooleanPreference provideSeenNavDrawer(SharedPreferences sharedPreferences) {
    return new BooleanPreference(
        sharedPreferences, "seen_navigation_drawer", DEFAULT_SEEN_NAVIGATION_DRAWER
    );
  }

  @Provides @Singleton @SeenTrackerDrawer
  BooleanPreference provideSeenTrackerDrawer(SharedPreferences sharedPreferences) {
    return new BooleanPreference(
        sharedPreferences, "seen_tracker_drawer", DEFAULT_SEEN_TRACKER_DRAWER
    );
  }
}
