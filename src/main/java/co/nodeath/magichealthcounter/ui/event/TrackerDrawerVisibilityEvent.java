package co.nodeath.magichealthcounter.ui.event;

public final class TrackerDrawerVisibilityEvent {

  public enum TrackerDrawerVisibility {
    SHOW,
    HIDE
  }

  public final TrackerDrawerVisibility visibility;

  public TrackerDrawerVisibilityEvent(TrackerDrawerVisibility visibility) {
    this.visibility = visibility;
  }
}
