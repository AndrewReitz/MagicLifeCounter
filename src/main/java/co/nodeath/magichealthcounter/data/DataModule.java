package co.nodeath.magichealthcounter.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.inkapplications.preferences.BooleanPreference;
import com.inkapplications.preferences.IntPreference;
import com.inkapplications.preferences.LongPreference;
import com.inkapplications.preferences.StringPreference;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import co.nodeath.magichealthcounter.BuildConfig;
import co.nodeath.magichealthcounter.ui.VersionName;
import dagger.Module;
import dagger.Provides;

@Module(
    complete = false,
    library = true
)
public final class DataModule {
  private static final boolean DEFAULT_SEEN_NAVIGATION_DRAWER = false;
  private static final boolean DEFAULT_SEEN_TRACKER_DRAWER = false;
  private static final String DEFAULT_SCREEN_TIMEOUT = "10"; /* 10 Seconds */

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

  @Provides @Singleton @ScreenTimeout
  StringPreference providesScreenTimeoutDuration(SharedPreferences sharedPreferences) {
    /* String in order to use list preference */
    return new StringPreference(
        sharedPreferences, "screen_timeout", DEFAULT_SCREEN_TIMEOUT
    );
  }

  @Provides @Singleton @VersionName
  StringPreference providesVersionName(SharedPreferences sharedPreferences) {
    return new StringPreference(sharedPreferences, "version_name", BuildConfig.VERSION_NAME);
  }
}
