package models;

import Users.Passenger;
import datastructures.CustomHashTable;
import datastructures.CustomQueue;
import datastructures.CustomStack;
import datastructures.CustomBST;

public class BookingService {

    private CustomHashTable<Integer, Booking> bookings;
    private CustomQueue<Passenger> waitingList;
    private CustomStack<Booking> cancelledBookings;
    private CustomBST<Booking> bookingTree;


    public BookingService() {

        bookings = new CustomHashTable<>(20);
        waitingList = new CustomQueue<>();
        cancelledBookings = new CustomStack<>();
        bookingTree = new CustomBST<>();

    }



    // Create Booking
    public Booking createBooking(Passenger passenger, Bus bus, Seat seat) {


        // Check seat availability
        if (!"Available".equalsIgnoreCase(seat.getStatus())) {

            waitingList.enqueue(passenger);

            System.out.println(
                    "Seat unavailable. Passenger added to waiting list."
            );

            return null;
        }



        // Reserve seat
        if (seat.reserve()) {


            Booking booking =
                    new Booking(
                            seat,
                            bus,
                            passenger
                    );


            booking.confirmBooking();



            // Store booking using booking ID as key
            bookings.put(
                    booking.getBookingId(),
                    booking
            );


            // Add to BST
            bookingTree.insert(booking);



            System.out.println(
                    "Booking created. ID: "
                            + booking.getBookingId()
            );


            return booking;
        }


        return null;
    }


    // Cancel Booking
    public boolean cancelBooking(int bookingId) {


        Booking booking = findBooking(bookingId);


        if (booking == null) {
            return false;
        }



        booking.getSeat().release();



        if (booking.cancelBooking()) {


            // Remove active booking
            bookings.remove(bookingId);



            // Store cancelled booking history
            cancelledBookings.push(booking);



            // Assign waiting passenger
            assignWaitingPassenger(
                    booking.getSeat()
            );


            return true;
        }


        return false;
    }

    public Booking findBooking(int bookingId) {

        return bookings.get(bookingId);

    }



    // Find booking by ID
    public Booking findBookingByPassenger(Passenger passenger) {

        for (Booking booking : bookings.values()) {

            if (booking.getPassenger().equals(passenger)) {
                return booking;
            }
        }

        return null;
    }




    private void assignWaitingPassenger(Seat seat) {


        if (!waitingList.isEmpty()) {


            Passenger passenger =
                    waitingList.dequeue();



            if (seat.reserve()) {


                Booking booking =
                        new Booking(
                                seat,
                                null,
                                passenger
                        );


                booking.confirmBooking();



                bookings.put(
                        booking.getBookingId(),
                        booking
                );


                bookingTree.insert(booking);
            }
        }
    }





    public CustomHashTable<Integer, Booking> getBookings() {
        return bookings;
    }


    public CustomQueue<Passenger> getWaitingList() {
        return waitingList;
    }


    public CustomStack<Booking> getCancelledBookings() {
        return cancelledBookings;
    }


    public CustomBST<Booking> getBookingTree() {
        return bookingTree;
    }
}