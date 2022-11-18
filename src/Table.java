public class Table {

    private static final int NUM_PHILOSOPHERS = 5;
    private static final int NUM_CHOPSTICKS = NUM_PHILOSOPHERS;

    //Table is the main thread
    public static void main(String args[]) {

        Chopstick[] chopsticks = new Chopstick[NUM_CHOPSTICKS];
        for (int i = 0; i < NUM_CHOPSTICKS; i++) {
            chopsticks[i] = new Chopstick(i);
        }

        //Handles seating, assigning chopsticks, and starting the threads.
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            //If we are assigning the last philosopher of our list their chopsticks, hand them the first chopstick.
            if (i == NUM_PHILOSOPHERS-1) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[0]);
                philosophers[i].startThread();
            }
            else {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[i+1]);
                philosophers[i].startThread();
            }
        }



    }
}
