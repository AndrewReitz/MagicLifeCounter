package co.nodeath.magichealthcounter.ui.event;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class UpdateScoreEvent {

  public enum Who {
    ME("Me"),
    YOU("You");

    final String value;

    private Who(String value) {
      this.value = value;
    }


    @Override public String toString() {
      return value;
    }
  }

  public enum Value {
    PLUS_FIVE("+5"),
    PLUS_ONE("+1"),
    MINUS_FIVE("-5"),
    MINUS_ONE("-1");

    final String value;

    private Value(String value) {
      this.value = value;
    }


    @Override public String toString() {
      return value;
    }
  }

  public final Who who;
  public final Value value;
  public final Date timeOccurred;

  private static final DateFormat TIME_FORMAT = DateFormat.getTimeInstance();

  public UpdateScoreEvent(Who who, Value value) {
    this.who  = who;
    this.value = value;

    timeOccurred = Calendar.getInstance().getTime();
  }

  public String getFormattedTime() {
    return TIME_FORMAT.format(timeOccurred);
  }

  @Override public String toString() {
    return String.format("%s: %s %s", who.value, value.value, getFormattedTime());
  }
}
