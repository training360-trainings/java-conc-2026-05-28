package timertask;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.sql.Date;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@BadPractice("""
        Timer-t kerüljük, régi API-ja is van, pl. Date.
        Helyette ScheduledThreadPoolExecutor osztályt használjuk!
        """)
public class TimerTaskMain {


    @SneakyThrows
    static void main() {
        var task = new TimerTask() {
            @Override
            public void run() {
                log.debug("Running task...");
            }
        };
        log.debug("Starting...");
        var timer = new Timer();
        //timer.schedule(task, Duration.ofSeconds(2).toMillis(), Duration.ofSeconds(1).toMillis());
        timer.schedule(task, Date.from(LocalDateTime.parse("2026-05-28 13:27")
                .toInstant(java.time.ZoneOffset.UTC)));
        Thread.sleep(Duration.ofSeconds(5));
        timer.cancel();
    }
}
