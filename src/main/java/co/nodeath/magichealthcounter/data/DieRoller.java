package co.nodeath.magichealthcounter.data;

import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;

public final class DieRoller {
  private final Random random;

  DieRoller(Random random) {
    this.random = checkNotNull(random);
  }

  /** Return a random number between min and max inclusive */
  public int rollDie(int min, int max) {
    return random.nextInt(max - min + 1) + min;
  }
}
