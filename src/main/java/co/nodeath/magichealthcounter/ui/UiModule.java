package co.nodeath.magichealthcounter.ui;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import javax.inject.Singleton;

import butterknife.ButterKnife;
import dagger.Module;
import dagger.Provides;

@Module(
    complete = false,
    library = true
)
public class UiModule {
  @Provides @Singleton AppContainer provideAppContainer() {
    return AppContainer.DEFAULT;
  }

  @Provides @Singleton ActivityHierarchyServer provideActivityHierarchyServer() {
    return ActivityHierarchyServer.NONE;
  }

  @Provides @Singleton @Main Handler provideMainUiHandler() {
    return new Handler(Looper.getMainLooper());
  }

  @Provides @Singleton @Flip180 ButterKnife.Action<View> provideFlipViews180DegreesAction() {
    return new ButterKnife.Action<View>() {
      @Override public void apply(View view, int index) {
        view.setRotation(180);
      }
    };
  }

  @Provides @Singleton @Show ButterKnife.Action<View> provideShowViewsAction() {
    return new ButterKnife.Action<View>() {
      @Override public void apply(View view, int index) {
        view.setVisibility(View.VISIBLE);
      }
    };
  }

  @Provides @Singleton @Hide ButterKnife.Action<View> provideHideViewsAction() {
    return new ButterKnife.Action<View>() {
      @Override public void apply(View view, int index) {
        view.setVisibility(View.GONE);
      }
    };
  }

  @Provides @Singleton @Standard ButterKnife.Action<TextView> provideSetStandardLifeAction() {
    return new ButterKnife.Action<TextView>() {
      @Override public void apply(TextView view, int index) {
        view.setTag(20);
        view.setText("20");
      }
    };
  }

  @Provides @Singleton @Commander ButterKnife.Action<TextView> provideSetCommanderLifeAction() {
    return new ButterKnife.Action<TextView>() {
      @Override public void apply(TextView view, int index) {
        view.setTag(40);
        view.setText("40");
      }
    };
  }

  @Provides @Singleton @Zero ButterKnife.Action<TextView> provideSetZeroAction() {
    return new ButterKnife.Action<TextView>() {
      @Override public void apply(TextView view, int index) {
        view.setTag(0);
        view.setText("0");
      }
    };
  }
}
