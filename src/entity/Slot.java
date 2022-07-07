package entity;

public class Slot {
    private int number;
    private boolean available;


    public Slot() {
    }

    public Slot(int number, boolean available) {
        this.number = number;
        this.available = available;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
