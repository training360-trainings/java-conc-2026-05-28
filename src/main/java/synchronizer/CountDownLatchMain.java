package synchronizer;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

@Slf4j
public class CountDownLatchMain {

    @SneakyThrows
    static void main() {
        CountDownLatch latch = new CountDownLatch(5);

        Runnable task = () -> {
            try {
                Thread.sleep(Duration.ofSeconds(2));
                latch.countDown();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("Task finished");
        };

        try (var service = Executors.newFixedThreadPool(2)) {
            for (int i = 0; i < 5; i++) {
                service.execute(task);
            }

            log.debug("Tasks started");
            latch.await();
            log.debug("All tasks finished");
        }
    }
}
