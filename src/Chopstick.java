public class Chopstick {
    private int location;
    private boolean occupied;

    public Chopstick(int location) {
        this.location = location;
        occupied = false;
    }

    public void setOccupied() {
        synchronized (this) {
            try {
                if (!occupied) {
                    occupied = true;
                }
            } catch (Exception ignore) {}
        }
    }

    public void setUnoccupied() {
        synchronized (this) {
            try {
                if (occupied) {
                    occupied = false;
                }
            } catch (Exception ignored) {}
        }
    }
}