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
* `long toDaysPart()` -
Extracts the number of days in the duration. Similar methods
for: hours, minutes, second, millis and nanos.
* `Duration truncatedTo(TemporalUnit unit)`