//Class Description: This is the philosopher class that is in charge of the state that each
//philosopher can be in, how long each state takes to complete, and the position of the
//philosopher at the table.

public class Philosopher implements Runnable {

    private volatile boolean eating;

    public enum State {
        //Three states a philosopher can have
        Thinking(getRandomInt()),
        Hungry(-1),
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

    //Instance variables
    private Thread thread;

    private State state;

    private Chopstick rightChop;
    private Chopstick leftChop;
    int threadNum;

    /**
     * Constructor for the Philosophers. Each philosopher gets an identifier (threadNum) and their left and right chopstick.
     * @param threadNum
     * @param leftChop
     * @param rightChop
     */
    public Philosopher(int threadNum, Chopstick leftChop, Chopstick rightChop) {
        this.threadNum = threadNum;
        thread = new Thread(this, "Philosopher[" + threadNum + "]");
        state = State.Thinking;
        this.rightChop = rightChop;
        this.leftChop = leftChop;
    }

    private volatile boolean timeToWork;

    //Starts the threads when called.
    public void startThread() {
        timeToWork = true;
        System.out.println("Starting thread ["+threadNum+"]");
        thread.start();
    }

    //Stops the threads when called.
    public void stopThread() {
        timeToWork = false;
    }

    /**
     * The waitToStop() method runs thread.join() which waits for the threads to die before continuing.
     */
    public void waitToStop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.err.println(thread.getName() + " stop malfunction");
        }
    }

    //Creates a random int by getting a random float from 0-1 out of the Math.random() function
    //and then multiplying by 2000 to get an int.
    public static int getRandomInt() {
        return (int)(Math.random()*2000);
    }

    //Changes current state to eating then gets next stage.
    private void eat() {
        state = State.Eating;
        System.out.println("Philosopher ["+threadNum+"] is currently eating...");
        state.getNext();
    }

    //Run method for Philosophers. This code is executed when the threads start.
    //Utilizes acquire and release
    @Override
    public void run() {
        //timeToWork turns on when threads start. Turn off when threads stop.
        //The idea for the structure of this code was found on: https://www.javatpoint.com/dining-philosophers-problem-and-solution-in-java.
        while (timeToWork) {
            leftChop.acquire();
            System.out.println("Philosopher ["+threadNum+"] grabs the left chopstick");
            rightChop.acquire();
            System.out.println("Philosopher ["+threadNum+"] grabs the right chopstick");

            eat();

            leftChop.release();
            System.out.println("Philosopher ["+threadNum+"] releases the left chopstick");
            rightChop.release();
            System.out.println("Philosopher ["+threadNum+"] releases the right chopstick");
        }
    }
}

