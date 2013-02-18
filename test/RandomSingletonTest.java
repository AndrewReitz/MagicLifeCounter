import org.junit.Test;

import co.nodeath.magichealthcounter.RandomSingleton;

import static org.junit.Assert.assertTrue;


/**
 * @author pieces029
 */
public class RandomSingletonTest {

    @Test
    public void shouldReturnAValueBetweenZeroAndN() {
        RandomSingleton r = RandomSingleton.getInstance();

        for (int i = 0; i < 100; i++) {
            int actual = r.getRandom(20);

            assertTrue(actual < 20);
            assertTrue(actual > -1);
        }
    }
}