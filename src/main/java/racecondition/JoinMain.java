package racecondition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class JoinMain {

    private Random random = new Random();

    @SneakyThrows
    public void sleep() {
        Thread.sleep(Duration.ofSeconds(random.nextInt(5)));
        log.debug("Done sleeping");
    }

    @SneakyThrows
    static void main() {
        var main = new JoinMain();
        Runnable task = main::sleep;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var thread = new Thread(task);
            threads.add(thread);
            thread.start();
        }

        for (var thread : threads) {
            // Timeout megadása mindig kötelező
            boolean result = thread.join(Duration.ofSeconds(10));
            log.debug("End, result: {}", result);
        }


    }

}
