package atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferenceMain {

    private AtomicReference<Integer> counter = new AtomicReference<>(0);

    static void main() {
        var main = new AtomicReferenceMain();
        Runnable task = () -> {
            while (true) {
                Integer oldValue = main.counter.get();
                Integer newValue = oldValue + 1;

                if (main.counter.compareAndSet(oldValue, newValue)) {
                    break;
                }

                log.debug("Counter: {}", main.counter);
            }
        };
        try (var service = Executors.newFixedThreadPool(200)) {
            for (int i = 0; i < 10_000; i++) {
                service.execute(task);
            }
        }
        log.debug("Counter: {}", main.counter);
    }
}
