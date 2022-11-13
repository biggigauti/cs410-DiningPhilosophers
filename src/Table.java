public class Table {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final int NUM_CHOPSTICKS = NUM_PHILOSOPHERS-1;

    //Table is the main thread
    public static void main(String args[]) {

        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            philosophers[i] = new Philosopher(i);
            philosophers[i].startThread();
        }

        

    }
}
