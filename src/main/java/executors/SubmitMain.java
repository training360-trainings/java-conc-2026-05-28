package executors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.*;

@Slf4j
public class SubmitMain {

    @SneakyThrows
    static void main() {
        try (var service = Executors.newFixedThreadPool(2)) {
            final String name = "Jack";
            Callable<Integer> task = () -> {
                log.debug("Task running... {}", name);
                Thread.sleep(Duration.ofSeconds(10));
                return 1;
            };

            Future<Integer> result = service.submit(task);
            log.debug("Ott vagyunk már? {}", result.isDone());
            try {
                var value = result.get(5, TimeUnit.SECONDS);
                log.debug("Értéke: {}", value);
            } catch (TimeoutException e) {
                log.error("Timeout", e);
            }

        }
    }
}
