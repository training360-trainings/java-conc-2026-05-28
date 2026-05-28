package racecondition;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CheckThenActMain {

    private List<Integer> numbers = new ArrayList<>();

    private final Object lock = new Object();

    public void addIfAbsent(int n) {
        synchronized (lock) {
//        if (!numbers.contains(n)) {
            numbers.add(n);
//        }
            log.debug("numbers: {}", numbers.size());
        }
    }

    static void main() {
        var main = new CheckThenActMain();
        for (int i = 0; i < 10_000; i++) {
            final var j = i;
            Runnable task = () -> main.addIfAbsent(j);
            new Thread(task).start();
        }

    }
}
