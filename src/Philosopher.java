import java.util.Random;

public class Philosopher implements Runnable {

    private volatile boolean timeToWork;
    private volatile boolean eating;

    public enum State {
        //Three states a philosopher can have
        Thinking(getRandomInt()),
        Hungry(),
        Eating(getRandomInt());

        //Instance variables
        private static final int finalIndex = State.values().length - 1;

        final int timeToComplete;

        State(int timeToComplete) {
            this.timeToComplete = timeToComplete;
        }

        State getNext() {
            //Since state is an "enum" .ordinal returns the philosopher's current state. The logic below check if the philosopher is at
            //its final state and if it isn't the philosopher switches to the next state. If it is at the last stage,
            //revert to the first state.
            int currIndex = this.ordinal();
            if (currIndex >= finalIndex) {
                return State.values()[0];
            }
            return State.values()[currIndex + 1];
        }
    }

    private int seat;

    private Thread thread;

    private State state;
    private Chopstick rightChop;
    private Chopstick leftChop;

    public Philosopher(int seat, leftChop, rightChop) {
        this.seat = seat;
        state = State.Thinking;
        this.rightChop = rightChop;
        this.leftChop = leftChop;
    }

    public void startThread() {
        thread.start();
    }

    public int getRandomInt() {
        return Math.random()*2000
    }

    private void checkEat() {
        //check left and right chopstick
        //call eat functions
        //careful of race conditions

        //if available...eating = true
    }

    private void eat() {
        state = State.Eating;
        state.getNext();
    }

    //synchronize checkeat and eat?

    @Override
    public void run() {
        synchronized(this) {
            while (timeToWork) {
                while (!eating) {
                    try {
                        checkEat();
                        //wait();
                    } catch (InterruptedException ignored) {}
                }
                eat();
                eating = false;
                notifyAll();
            }
        }
    }
}
