package racecondition;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@BadPractice("""
        Ha synchronized, akkor a legjobb egy külön lock, Object típusú,
        mert az követhető, és semmi mást nem tud.
        
        De inkább synchronized-ot se használjunk, hanem szálbiztos 
        kollekciót, vagy a Lock interfészt.
        """)
public class ReadModifyWriteMain {

    private int counter = 0;

    private final Object lock = new Object();

    // public synchronized void increment() {
    public void increment() {
        synchronized (lock) {
            counter++;
            log.debug("Incremented counter: {}", counter);
        }
    }

    @SneakyThrows
    static void main() {
        var main = new ReadModifyWriteMain();
        Runnable task = main::increment;
        for (int i = 0; i < 10_000; i++) {
            new Thread(task).start();
        }
        Thread.sleep(Duration.ofSeconds(1));
        log.debug("Counter: {}", main.counter);
    }
}
