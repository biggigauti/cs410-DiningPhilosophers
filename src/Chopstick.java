public class Chopstick {
    private int location;
    private boolean acquired;

    public Chopstick(int location) {
        this.location = location;
        acquired = false;
    }

    public void acquire() {
        synchronized (this) {
            try {
                if (!occupied) {
                    occupied = true;
                }
            } catch (Exception ignore) {}
        }
    }

    public void release() {
        synchronized (this) {
            try {
                if (occupied) {
                    occupied = false;
                }
            } catch (Exception ignored) {}
        }
    }
}