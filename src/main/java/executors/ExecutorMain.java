package executors;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecutorMain {
    static void main() {
        try (ExecutorService service = Executors.newFixedThreadPool(5)) {
//        try (ExecutorService service = Executors.newSingleThreadExecutor()) {
            Runnable task = () -> {
                log.debug("Task running...");
                try {
                    Thread.sleep(Duration.ofSeconds(2));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("Task done");
            };
            for (int i = 0; i < 10; i++) {
                service.execute(task);
            }
        }
        // service.shutdown();
    }
}
