package co.nodeath.magichealthcounter;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public interface Analytics {
  void screen(String screenName, Object... args);

  final class GoogleAnalytics implements Analytics {
    private final Tracker tracker;

    public GoogleAnalytics(Tracker tracker) {
      this.tracker = tracker;
    }

    /** Report a screen view. Should only be placed in the onStart method */
    @Override public void screen(String screenName, Object... args) {
      tracker.setScreenName(formatString(screenName, args));
      tracker.send(new HitBuilders.AppViewBuilder().build());
    }

    private static String formatString(String message, Object... args) {
      // If no varargs are supplied, treat it as a request to log the string without formatting.
      return args.length == 0 ? message : String.format(message, args);
    }
  }
}
