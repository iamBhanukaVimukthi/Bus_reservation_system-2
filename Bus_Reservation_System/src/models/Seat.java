package models;

public class Seat {
    private int seatNumber;
    private String status;
    private String type;

    // Standard Constructor
    public Seat(int seatNumber, String status, String type) {
        this.seatNumber = seatNumber;
        this.status = status;
        this.type = type;
    }

    // Default Constructor (Good practice)
    public Seat() {
        this(0, "Available", "Standard");
    }

    public boolean reserve() {
        if ("Available".equalsIgnoreCase(this.status)) {
            this.status = "Reserved";
            return true;
        }
        return false;
    }

    public void release() {
        this.status = "Available";
    }

    // --- Getters and Setters ---
    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Fixed toString() format
    @Override
    public String toString() {
        return "Seat{" +
                "No=" + seatNumber +
                ", Status='" + status + '\'' +
                ", Type='" + type + '\'' +
                '}';
    }
}