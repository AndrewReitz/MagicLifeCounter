package co.nodeath.magichealthcounter;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.crashlytics.android.Crashlytics;
import com.inkapplications.preferences.StringPreference;

import co.nodeath.magichealthcounter.ui.ActivityHierarchyServer;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import co.nodeath.magichealthcounter.ui.VersionName;
import dagger.ObjectGraph;
import hugo.weaving.DebugLog;
import timber.log.Timber;

public class MagicLifeCounterApp extends Application {

  @Inject ActivityHierarchyServer activityHierarchyServer;

  private ObjectGraph objectGraph;

  @Override
  public void onCreate() {
    super.onCreate();

    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
          .detectAll()
          .penaltyDeath()
          .build());
      StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
          .detectAll()
          .penaltyDeath()
          .build());
    } else {
      Crashlytics.start(this);
      Timber.plant(new CrashlyticsTree());
    }

    buildObjectGraphAndInject();

    registerActivityLifecycleCallbacks(activityHierarchyServer);
  }

  @DebugLog
  public void buildObjectGraphAndInject() {
    objectGraph = ObjectGraph.create(Modules.list(this));
    objectGraph.inject(this);
  }

  public ObjectGraph getObjectGraph() {
    return objectGraph;
  }

  public void inject(@NotNull Object o) {
    objectGraph.inject(o);
  }

  public static MagicLifeCounterApp get(@NotNull Context context) {
    return (MagicLifeCounterApp) context.getApplicationContext();
  }
}
