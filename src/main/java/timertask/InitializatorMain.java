package timertask;

import annotations.BadPractice;

import java.util.HashMap;
import java.util.Map;

@BadPractice("Példa a anonymous inner classra, inicializátorral")
public class InitializatorMain {

    static void main() {
        Map<String, String> map = new HashMap<>() {{put("a", "b");}};
        System.out.println(map.get("a"));

        Map<String, String> map2 = new HashMap<>(Map.of("a", "b"));
        map2.put("c", "d");
    }
}
