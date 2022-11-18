import java.util.Random;

public class Philosopher implements Runnable {

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
    private boolean thinking;
    private boolean eating;

    private Thread thread;

    public Philosopher(int seat) {
        this.seat = seat;
        thinking = true;
        eating = false;
    }

    public void startThread() {
        thread.start();
    }

    public int getRandomInt() {
        return Math.random()*2000
    }

    @Override
    public void run() {

    }
}
