package virtual;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class VirtualMain {

    @SneakyThrows
    static void main() {
//    ExecutorService executor = Executors.newFixedThreadPool(16);
        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();
        var start = System.currentTimeMillis();
        try (executor) {
            Runnable runnable = () -> {
                try {
                    // IO block
                    Thread.sleep(Duration.ofMillis(100));
                } catch (InterruptedException e) {
                    // Intentionally
                }
            };
            for (int i = 0; i < 1_000; i++) {
                executor.execute(runnable);
            }
        }
        executor.awaitTermination(1, TimeUnit.MINUTES);
        log.debug("Total time: {} ms", System.currentTimeMillis() - start);
    }
}
