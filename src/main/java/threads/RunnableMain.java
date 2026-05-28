package threads;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class RunnableMain {

    static void main() {
        Runnable runnable = () ->
            {
                log.debug("Start.");
                try {
                    Thread.sleep(Duration.ofSeconds(1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("End.");
            };

        var thread = new Thread(runnable);
        // thread.setDaemon(true);
        thread.start();

        log.debug("Main.");
    }
}
