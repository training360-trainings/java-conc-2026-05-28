package racecondition;

import annotations.BadPractice;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
@BadPractice("Deadlock")
public class FriendMain {

    @AllArgsConstructor
    @Getter
    static class Friend {
        private String name;

        public synchronized void bow(Friend other) {
            log.debug("{} bows to {}", name, other.name);
            other.bowBack(this);
        }

        public synchronized void bowBack(Friend other) {
            log.debug("{} bows back to {}", other.name, name);
        }
    }

    static void main() {
        Friend alice = new Friend("Alice");
        Friend bob = new Friend("Bob");

        Runnable task1 = () -> alice.bow(bob);
        Runnable task2 = () -> bob.bow(alice);

        new Thread(task1).start();
        new Thread(task2).start();
    }
}
