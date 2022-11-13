public class Philosopher implements Runnable {

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

    @Override
    public void run() {

    }
}
