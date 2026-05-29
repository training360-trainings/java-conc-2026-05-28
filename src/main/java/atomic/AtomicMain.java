package atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class AtomicMain {

    private AtomicInteger counter = new AtomicInteger();

    static void main() {
        var main = new AtomicMain();
        Runnable task = () -> {
            //var counter = main.counter.incrementAndGet();
            var counter = main.counter.updateAndGet(x -> x + 2);
            log.debug("Counter: {}", counter);
        };
        try (var service = Executors.newFixedThreadPool(200)) {
            for (int i = 0; i < 10_000; i++) {
                service.execute(task);
            }
        }
        log.debug("Counter: {}", main.counter);
    }
}
