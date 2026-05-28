package threads;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@BadPractice("""
    Sose használjatok alkalmazásban Thread.sleep-et,
    csak hosszú folyamat szimulálására használjuk a demókban. 
    """)
public class ThreadMain {

    @SneakyThrows
    static void main() {
        log.debug("Thread: {}", Thread.currentThread().getName());
        Thread.sleep(Duration.ofSeconds(1));
    }
}
