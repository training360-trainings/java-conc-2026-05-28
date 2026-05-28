package racecondition;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LivelockMain {

    static class Friend {

        private String name;

        private boolean moveAllowed = false;

        public Friend(String name) {
            this.name = name;
        }

        public synchronized void allowMove() {
            log.debug("{} is allowed to move", name);
            moveAllowed = true;
        }

        public synchronized void disallowMove() {
            log.debug("{} is not allowed to move", name);
            moveAllowed = false;
        }

        public synchronized boolean canMove() {
            return moveAllowed;
        }
    }

    @AllArgsConstructor
    static class MovementTask implements Runnable {

        private final Friend friend1;

        private final Friend friend2;

        @Override
        public void run() {
            while (true) {
                if (friend1.canMove()) {
                    // Próbál mozogni, de látja, hogy a másik mozog
                    friend1.disallowMove(); // Megáll, hogy elengedje a másikat
                    friend2.allowMove(); // Engedi a másikat mozogni
                }
            }
        }
    }

    static void main() {
        var alice = new Friend("Alice");
        var bob = new Friend("Bob");

        var aliceTask = new MovementTask(alice, bob);
        var bobTask = new MovementTask(bob, alice);

        alice.allowMove(); // Start with Alice being allowed to move

        new Thread(aliceTask).start();
        new Thread(bobTask).start();
    }
}
