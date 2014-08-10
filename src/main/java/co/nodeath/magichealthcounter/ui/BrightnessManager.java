package co.nodeath.magichealthcounter.ui;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import javax.inject.Inject;

import static android.provider.Settings.System.SCREEN_BRIGHTNESS;
import static android.provider.Settings.System.getInt;

public final class BrightnessManager {

  private static final float SCREEN_BRIGHTNESS_MAX = 255f;

  private final Activity activity;

  /** Store the initial brightness so we can set it back */
  private final int initialBrightness;

  @Inject BrightnessManager(Activity activity) {
    this.activity = activity;
    this.initialBrightness = getCurrentBrightness();
  }

  /**
   * Get the activity this manager manages brightness
   * @return value between 0 and 255
   */
  int getCurrentBrightness() {
    return getInt(activity.getContentResolver(), SCREEN_BRIGHTNESS, 0);
  }

  /** Restore brightness back to what it was when this manager was created */
  void restoreBrightness() {
    setBrightness(initialBrightness);
  }

  /**
   * Set the brightness of the activity
   * @param value between 0 and 255
   */
  void setBrightness(int value) {
    Window win = activity.getWindow();
    WindowManager.LayoutParams attrs = win.getAttributes();
    attrs.screenBrightness = value / SCREEN_BRIGHTNESS_MAX;
    win.setAttributes(attrs);
  }
}
