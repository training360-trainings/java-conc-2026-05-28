package threads;

import annotations.BadPractice;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
@BadPractice("""
        Túl alacsony szint, helyette inkább Executor, ForkJoinPool vagy 
        CompletableFuture vagy structured concurrency
        """)
public class RunnableMain {

    @BadPractice("""
        InterruptedException kezelését ki kell egészíteni
        """)
    static void main() {
        Runnable runnable = () ->
            {
//                try {
                    log.debug("Start.");
                    try {
                        Thread.sleep(Duration.ofSeconds(1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    throw new IllegalStateException("Test exception");
                    //log.debug("End.");
//                } catch (Exception e) {
//                    log.error("Exception in thread", e);
//                }
            };

        Thread.setDefaultUncaughtExceptionHandler((t, e) ->
                    log.error("Uncaught default exception", e));
        for (int i = 0; i < 5; i++) {
            var thread = new Thread(runnable);
            thread.setName("SleeperThread-" + i);
            thread.setUncaughtExceptionHandler((t, e) ->
                    log.error("Uncaught exception", e));
            // thread.setDaemon(true);
            thread.start();
            // Mindig a start() metódust kell használni, a run metódus NEM indít új szálat!
            //            thread.run();
        }

        log.debug("Main.");
    }
}
