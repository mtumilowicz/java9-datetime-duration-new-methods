# java9-duration-new-methods
_Reference_: https://docs.oracle.com/javase/9/docs/api/java/time/Duration.html

# preview
* `long dividedBy(Duration divisor)` -
Returns number of whole times a specified `Duration` 
occurs within this `Duration`.
    * divisor could be positive or negative, not null
    * rounded toward zero
    * `ArithmeticException` if the divisor is zero, or if 
    numeric overflow occurs
* `long toSeconds()` -
Gets the number of seconds in this duration.
    * positive, negative or zero
* `long toDaysPart()` -
Extracts the number of days in the duration. Similar methods
for: hours, minutes, second, millis and nanos.
    * positive, negative or zero
* `Duration truncatedTo(TemporalUnit unit)` -
Returns a copy of this `Duration` truncated to the specified unit.
    * Truncating the duration returns a copy of the original with conceptual fields 
    smaller than the specified unit set to zero.
    * `DateTimeException` if the unit is invalid for truncation
    * `UnsupportedTemporalTypeException` if the unit is not 
    supported

# tests
* `dividedBy`
    ```
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
    ```
* `toSeconds`
    ```
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
    ```
* `toDaysPart`
    ```
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
    ```
* `truncateTo`
    ```
    @Test(expected = UnsupportedTemporalTypeException.class)
    public void truncatedTo_unsupportedTemporalTypeException() {
        Duration.parse("P2DT3H4M").truncatedTo(ChronoUnit.CENTURIES);
    }
    
    @Test
    public void truncatedTo_hours() {
        assertThat(Duration.parse("P2DT3H4M").truncatedTo(ChronoUnit.HOURS), is(Duration.parse("P2DT3H")));
    }
    
    @Test
    public void truncatedTo_days() {
        assertThat(Duration.parse("P2DT3H4M").truncatedTo(ChronoUnit.DAYS), is(Duration.ofDays(2)));
    }
    
    @Test
    public void truncatedTo_halfDays() {
        assertThat(Duration.ofHours(14).truncatedTo(ChronoUnit.HALF_DAYS), is(Duration.ofHours(12)));
    }
    ```