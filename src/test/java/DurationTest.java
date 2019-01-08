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
}
