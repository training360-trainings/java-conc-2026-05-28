package atomic;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class AtomicReferenceRecordMain {

    record Counter(int value) {
        Counter increment() {
            return new Counter(value + 1);
        }
    }

    private AtomicReference<Counter> counter = new AtomicReference<>(new Counter(0));

    static void main() {
        var main = new AtomicReferenceRecordMain();
        Runnable task = () -> {
            while (true) {
                Counter oldValue = main.counter.get();
                Counter newValue = oldValue.increment();

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
