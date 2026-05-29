package executors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ScheduledMain {

    @SneakyThrows
    static void main() {
         try (var service = Executors.newScheduledThreadPool(2)) {
             Runnable task = () -> log.debug("Hello");
             service.scheduleAtFixedRate(task, 2, 1, TimeUnit.SECONDS);
             Thread.sleep(Duration.ofSeconds(5));
         } // finally close() -> // shutdown()
    }
}
