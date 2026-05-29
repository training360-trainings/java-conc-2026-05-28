package atomic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CounterMain {

    static void main() {
        var counter = new AtomicReferenceRecordMain.Counter(12);
        var counter2 = new AtomicReferenceRecordMain.Counter(12);
        log.debug("{}", counter.equals(counter2));

    }
}
