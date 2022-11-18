public class Chopstick {
    private int location;
    private boolean acquired;

    public Chopstick(int location) {
        this.location = location;
        acquired = false;
    }

    public void acquire() {
        synchronized (this) {
            if (!acquired) {
                acquired = true;
            }
        }
    }

    public void release() {
        synchronized (this) {
            acquired = false;
        }
    }
}