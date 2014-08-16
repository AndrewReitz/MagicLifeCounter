package co.nodeath.magichealthcounter.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.fest.assertions.api.Assertions.assertThat;

public final class DieRollerTest {

  private DieRoller dieRoller;

  @Before public void setup() {
    dieRoller = new DieRoller(new Random(100));
  }

  @Test public void should_return_value_between_1_and_20() {
    // Arrange
    SortedSet<Integer> container = new TreeSet<>();

    // Act
    for (int i = 0; i < 100000; i++) {
      container.add(dieRoller.rollDie(1, 20));
    }

    // Assert
    assertThat(container.last()).isEqualTo(20);
    assertThat(container.first()).isEqualTo(1);

    for (int i = 1; i <= 20; i++) {
      assertThat(container.contains(i)).isTrue();
    }
  }

  @Test public void should_return_value_between_6_and_10() {
    // Arrange
    SortedSet<Integer> container = new TreeSet<>();

    // Act
    for (int i = 0; i < 100000; i++) {
      container.add(dieRoller.rollDie(6, 10));
    }

    // Assert
    assertThat(container.last()).isEqualTo(10);
    assertThat(container.first()).isEqualTo(6);

    for (int i = 6; i <= 10; i++) {
      assertThat(container.contains(i)).isTrue();
    }
  }
}
