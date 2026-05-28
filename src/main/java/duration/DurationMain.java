package duration;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Slf4j
public class DurationMain {
    static void main() {
        log.debug("Starting...");
        var duration = Duration.of(1, ChronoUnit.HOURS);
        var ms = duration.toMillis();
        log.debug("Duration: {}, {}", duration, ms);
    }
}
