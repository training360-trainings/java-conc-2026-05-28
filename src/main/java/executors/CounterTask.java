package executors;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@RequiredArgsConstructor
public class CounterTask implements Callable<List<Integer>> {

    private final int max;

    @Override
    public List<Integer> call() throws Exception {
        List<Integer> result = new java.util.ArrayList<>();
        for (int i = 0; i < max; i++) {
            result.add(i);
        }
        return result;
    }

    static void main() {
        var task = new CounterTask(10);
        try (var service = Executors.newSingleThreadExecutor()) {
            service.submit(task);
        }

    }
}
