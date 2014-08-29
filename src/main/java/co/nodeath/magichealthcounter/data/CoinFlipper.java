package co.nodeath.magichealthcounter.data;

import java.util.Random;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

public final class CoinFlipper {
  public enum CoinFlip {
    HEADS,
    TAILS
  }

  private Random random;

  @Inject CoinFlipper(Random random) {
    this.random = checkNotNull(random);
  }

  public CoinFlip flipCoin() {
    return random.nextBoolean() ? CoinFlip.HEADS : CoinFlip.TAILS;
  }
}
