package racecondition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class CancelMain {

    @SneakyThrows
    static void main() {
        Runnable task = () -> {
            log.debug("Start");
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    log.debug("Pl. webszolgáltatás hívása vagy prímszám számolás");
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) { // Az interrupted flag-et false-ra billenti
                    log.debug("Interrupted while sleeping");
                    Thread.currentThread().interrupt(); // Az  interrupted flag-et visszabillenti true-ra
                }
            }
            log.debug("End");
        };

        var thread = new Thread(task);
        thread.start();

        Thread.sleep(Duration.ofSeconds(5));

        thread.interrupt();
    }
}
