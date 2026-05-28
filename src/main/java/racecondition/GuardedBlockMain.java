package racecondition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class GuardedBlockMain {

    private boolean condition = false;

    @SneakyThrows
    public synchronized void waitForCondition() {
        while (!condition) {
            log.debug("Waiting for condition...");
            wait();
            log.debug("Condition met.");
        }
    }

    public synchronized void setCondition() {
        condition = true;
        notify();
    }

    @SneakyThrows
    static void main() {
        var main = new GuardedBlockMain();
        Runnable waitingTask = main::waitForCondition;
        new Thread(waitingTask).start();

        Thread.sleep(Duration.ofSeconds(2));
        Runnable settingTask = main::setCondition;
        new Thread(settingTask).start();

        log.debug("End.");
    }
}
