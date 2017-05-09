package RobotClasses;

public class Box {
    private int size;
    private boolean isFull = false;

    public Box(int size, boolean isFull) {
        this.size = size;
        this.isFull = isFull;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }
}
