package models;

import Users.Passenger;
import java.util.Date;

public class Booking implements Comparable<Booking> {

    private static int nextBookingId = 1;

    private int bookingId;
    private Date bookingDate;
    private String status;
    private Seat seat;
    private Bus bus;
    private Passenger passenger;


    public Booking(Seat seat, Bus bus, Passenger passenger) {

        this.bookingId = nextBookingId++;
        this.bookingDate = new Date();
        this.status = "PENDING";
        this.seat = seat;
        this.bus = bus;
        this.passenger = passenger;
    }


    public int getBookingId() {
        return bookingId;
    }


    public boolean confirmBooking() {
        if (status.equals("PENDING")) {
            status = "CONFIRMED";
            return true;
        }
        return false;
    }

    public Seat getSeat() {
        return seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }


    public boolean cancelBooking() {
        if (status.equals("CONFIRMED")) {
            status = "CANCELLED";
            return true;
        }
        return false;
    }

    @Override
    public int compareTo(Booking other) {

        return this.bookingId - other.bookingId;

    }
}