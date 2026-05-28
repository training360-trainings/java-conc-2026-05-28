package threads;

import annotations.BadPractice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@BadPractice("Sose származzunk le a Thread osztályból, mindig Runnable implementálás")
public class ThreadExtendsMain {

    static void main() {
        var thread = new Thread() {
            @Override
            public void run() {
                log.debug("Start");
            }
        };
        thread.start();
    }
}
