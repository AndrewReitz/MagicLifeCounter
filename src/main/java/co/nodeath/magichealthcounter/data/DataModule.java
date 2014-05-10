package co.nodeath.magichealthcounter.data;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import co.nodeath.magichealthcounter.ForApplication;
import dagger.Module;
import dagger.Provides;

@Module(
    complete = false,
    library = true
)
public final class DataModule {
  @Provides @Singleton SharedPreferences provideSharedPreferences(@ForApplication Application app) {
    return PreferenceManager.getDefaultSharedPreferences(app);
  }


}
