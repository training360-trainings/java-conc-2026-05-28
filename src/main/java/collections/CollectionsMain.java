package collections;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CollectionsMain {

    @SneakyThrows
    static void main() {
        ConcurrentHashMap<String, Integer> map = //new HashMap<>();
//                Collections.synchronizedMap(new HashMap<>());
            new ConcurrentHashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        var result = map.reduceValues(2, v -> v * 2, Integer::sum);
        log.debug("Result: {}", result);

        Set<String> set = ConcurrentHashMap.newKeySet();

        ArrayBlockingQueue queue = new ArrayBlockingQueue(10);
        var value = queue.poll(1, TimeUnit.SECONDS);
        log.debug("Value: {}", value);
    }
}
