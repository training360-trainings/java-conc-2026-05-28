package completable;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

@Slf4j
public class CompletableFutureMain {

    @SneakyThrows
    static void main() {
        Runnable task = () -> {
            try {
                Thread.sleep(Duration.ofSeconds(2));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.debug("Task finished");
        };
        var result = CompletableFuture
                .runAsync(task)
                .thenAccept(v -> log.debug("Task finished"));
        result.get();
        log.debug("Main finished");
    }
}
