import java.util.concurrent.Semaphore;

//Class Description: This is the Chopstick class that checks where n-chopsticks are within the table.
//The philosophers can either acquire a chopstick or release it back to the table.
//Both the acquire() and release() methods must be synchronized to avoid starvation among the
//philosophers.

//Some of ideas for how to structure chopsticks was found on this page: https://www.javatpoint.com/dining-philosophers-problem-and-solution-in-java.
//Mainly acquire, release, and how to create the mutex.

public class Chopstick {

    public Semaphore mutex = new Semaphore(1);
    /*
    private int location;
    private boolean acquired;

    public Chopstick(int location) {
        this.location = location;
        acquired = false;
    }

     */

    //When a philosopher acquires a chopstick, they must check if the chopstick to their left or right
    //is also available. If it is, acquire() will be called.
    public void acquire() {
        try {
            mutex.acquire();
        }
        catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    //When the philosopher has eaten successfully, they must release their chopstick back to the table
    //in order for other philosophers to get a turn at eating.
    public void release() {
        mutex.release();
    }
    //Checks if the chopstick is free or not
    public boolean isFree()
    {
    //The method returns the current number of permits available in the semaphore and
    //returns true if available permits is greater than 0, else returns false
        return mutex.availablePermits() > 0;
    }
}