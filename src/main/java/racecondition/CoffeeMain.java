package racecondition;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class CoffeeMain {

    static class CoffeeMachine {

        private final Object lock = new Object();

        @SneakyThrows
        public void makeCoffee(String employeeName) {
            synchronized (lock) {
                log.debug("Making coffee for {}", employeeName);
                Thread.sleep(Duration.ofSeconds(2));
                log.debug("Coffee made for {}", employeeName);
            }
        }
    }

    static void main() {
        var coffeeMachine = new CoffeeMachine();
        for (int i = 0; i < 5; i++) {
            var employeeName = "Employee-" + i;
            Runnable task = () -> coffeeMachine.makeCoffee(employeeName);
            new Thread(task).start();
        }
    }
}
