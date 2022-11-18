public class Table {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final int NUM_CHOPSTICKS = NUM_PHILOSOPHERS;

    //Table is the main thread
    public static void main(String args[]) {

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i);
            philosophers[i].startThread();
        }

        Chopstick[] chopsticks = new Chopstick[NUM_CHOPSTICKS];
        for (int i = 0; i < NUM_CHOPSTICKS; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        

    }
}
