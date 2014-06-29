package co.nodeath.magichealthcounter;

import android.app.Application;
import android.content.Context;

import co.nodeath.magichealthcounter.ui.ActivityHierarchyServer;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import dagger.ObjectGraph;
import hugo.weaving.DebugLog;
import timber.log.Timber;

public class MagicLifeCounterApp extends Application {

  @Inject ActivityHierarchyServer activityHierarchyServer;

  private ObjectGraph objectGraph;

  @Override
  public void onCreate() {
    super.onCreate();

    // Logging Setup
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    } else {
      // Place Prod Logging here
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
