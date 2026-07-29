package models;

import datastructures.CustomLinkedList;

public class Bus implements Comparable<Bus> {
    private int busId;
    private String busNumber;
    private int capacity;
    private String status;
    private String type;
    private CustomLinkedList<Seat> seats;

    public Bus(int busId, String busNumber, int capacity, String status, String type) {
        this.busId = busId;
        this.busNumber = busNumber;
        this.capacity = capacity;
        this.status = status;
        this.type = type;
        this.seats = new CustomLinkedList<>();
    }

    public int getBusId() {
        return busId;
    }

    public String getBusNumber() {
        return busNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    // Admin calls this method to add each declared seat
    public void addSeat(Seat seat) {
        if (seats.getSize() < capacity) {
            this.seats.add(seat);
        }
    }

    public CustomLinkedList<Seat> getSeats() {
        return seats;
    }

    // --- REQUIRED FOR CustomAVLTree ---
    @Override
    public int compareTo(Bus other) {
        // Sort buses in the AVL tree by busId
        return Integer.compare(this.busId, other.busId);
    }

    // --- REQUIRED FOR avlTree.display() ---
    @Override
    public String toString() {
        return "Bus ID: " + busId +
                " | Number: " + busNumber +
                " | Capacity: " + capacity +
                " | Type: " + type +
                " | Status: " + status;
    }
}