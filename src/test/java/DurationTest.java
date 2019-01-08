import org.junit.Test;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mtumilowicz on 2019-01-08.
 */
public class DurationTest {

    @Test(expected = ArithmeticException.class)
    public void dividedBy_zero() {
        Duration.ofDays(10).dividedBy(Duration.ZERO);
    }

    @Test
    public void dividedBy_negative() {
        assertThat(Duration.ofDays(10).dividedBy(Duration.ofDays(-1)), is(-10L));
    }

    @Test
    public void dividedBy_positive() {
        assertThat(Duration.ofDays(10).dividedBy(Duration.ofDays(1)), is(10L));
    }

    @Test
    public void dividedBy_round() {
        assertThat(Duration.ofDays(10).dividedBy(Duration.ofDays(3)), is(3L));
    }

    @Test
    public void toSeconds_zero() {
        assertThat(Duration.ofSeconds(0).toSeconds(), is(0L));
    }

    @Test
    public void toSeconds_positive() {
        assertThat(Duration.ofSeconds(5).toSeconds(), is(5L));
    }

    @Test
    public void toSeconds_negative() {
        assertThat(Duration.ofSeconds(-3).toSeconds(), is(-3L));
    }

    @Test
    public void toSeconds_lessThanSecond() {
        assertThat(Duration.ofMillis(100).toSeconds(), is(0L));
    }

    @Test
    public void toDaysPart_zero() {
        assertThat(Duration.ofSeconds(0).toDaysPart(), is(0L));
    }

    @Test
    public void toDaysPart_positive() {
        assertThat(Duration.ofDays(5).toDaysPart(), is(5L));
    }

    @Test
    public void toDaysPart_negative() {
        assertThat(Duration.ofDays(-3).toDaysPart(), is(-3L));
    }

    @Test
    public void toDaysPart_lessThanDay() {
        assertThat(Duration.ofHours(23).toDaysPart(), is(0L));
    }

    @Test
    public void toDaysPart_round() {
        assertThat(Duration.ofHours(50).toDaysPart(), is(2L));
    }
}
