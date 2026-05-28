package threadlocal;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class ThreadLocalMain {

    private final ThreadLocal<String> orderId = new ThreadLocal<>();

    static void main() {
        var main = new ThreadLocalMain();
        Runnable task = main::processOrder;
        for (int i = 0; i < 5; i++) {
            new Thread(task).start();
        }
    }

    private void processOrder() {
        var id = UUID.randomUUID().toString();
        log.debug("Process order id: {}", id);
        orderId.set(id);
        saveOrder();
    }

    private void saveOrder() {
        var id = orderId.get();
        log.debug("Save order id: {}", id);
    }
}
