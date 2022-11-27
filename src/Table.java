//Class Description: This is the table class that determines how many threads the program will have
//It deals with assigning chopsticks to philosophers and where each philosopher is sitting.

public class Table {

    //Class variables
    private static final int NUM_PHILOSOPHERS = 5;
    private static final int NUM_CHOPSTICKS = NUM_PHILOSOPHERS;

    //Table is the main thread
    public static void main(String args[]) {

        Chopstick[] chopsticks = new Chopstick[NUM_CHOPSTICKS];
        for (int i = 0; i < NUM_CHOPSTICKS; i++) {
            chopsticks[i] = new Chopstick();
        }

        //Handles seating, assigning chopsticks, and starting the threads.
        Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
        for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
            //If we are assigning the last philosopher of our list their chopsticks, hand them the first chopstick.
            /*
            if (i == NUM_PHILOSOPHERS-1) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[0]);
                philosophers[i].startThread();
            }
            else {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[i+1]);
                philosophers[i].startThread();
            }
             */
            philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % NUM_PHILOSOPHERS]);
            //begins the execution of the thread
            philosophers[i].startThread();
        }

        //Deadlock safety code acquired from: https://www.javatpoint.com/dining-philosophers-problem-and-solution-in-java
        //The lines below until "Thread.sleep(2000); were acquired from the link above.
        try {
        //thread sleep for 2 sec or 2000ms
            Thread.sleep(2000);
        //check for deadlock condition
            boolean deadlock = true;
        //for each loop iterates over chopsticks
            for (Chopstick cs : chopsticks) {
        //checks if chopstick is free or not
                if (cs.isFree()) {
                    deadlock = false;
                    break;
                } //end of if
            } //end of for loop
        //deadlock occurs if sleep time is 2000ms it means each philosopher is eating
            if (deadlock) {
                Thread.sleep(2000);
                System.out.println("The philosophers have all eaten");
                System.exit(-1);

                //stop threads
                for (Philosopher p : philosophers) {
                    p.stopThread();
                }

                //wait for threads to stop
                for (Philosopher p : philosophers) {
                    p.waitToStop();
                }

                System.out.println("The philosophers have all eaten");

                System.exit(-1);
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
