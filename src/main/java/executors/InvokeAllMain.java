package executors;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class InvokeAllMain {

    @SneakyThrows
    static void main() {

        List<Callable<String>> tasks = new java.util.ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var j = i;
            Callable<String> task = () -> {
                Thread.sleep(Duration.ofMillis(500));
                if (j % 3 == 0) {
                    throw new RuntimeException("Error");
                }
                return "Hello " + j;
            };
            tasks.add(task);
        }

        try (var service = Executors.newFixedThreadPool(6)) {
            List<Future<String>> result = service.invokeAll(tasks);
            for (var future : result) {
                log.debug("Result: {}", future.get());
            }
        }

    }
}
