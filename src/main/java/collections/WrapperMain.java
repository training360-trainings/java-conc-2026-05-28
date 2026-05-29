package collections;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collections;

@Slf4j
public class WrapperMain {

    static void main() {
        // Működő, de lassú megoldás, vannak gyorsabbak
        var numbers = Collections.synchronizedList(new ArrayList<Integer>());
        log.debug("Class: {}", numbers.getClass().getName());
    }
}
