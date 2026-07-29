package demo;

import datastructures.CustomHashTable;
import datastructures.CustomHashSet;
import Users.Passenger;
import models.Booking;
import models.Bus;
import models.Seat;

public class Member6_Demo {

    public static void main(String[] args) {


        // ---------- PART 1: HASH TABLE ----------

        CustomHashTable<Integer, Booking> bookingTable =
                new CustomHashTable<>(8);


        Passenger p1 =
                new Passenger("Alice Smith", "alice@email.com", "0771234567");

        Passenger p2 =
                new Passenger("Bob Jones", "bob@email.com", "0777654321");

        Passenger p3 =
                new Passenger("Charlie Brown", "charlie@email.com", "0711122334");



        Bus bus =
                new Bus(101, "BUS-12", 40, "Active", "Standard");



        Seat seat1 =
                new Seat(1, "Available", "Window");

        Seat seat2 =
                new Seat(2, "Available", "Aisle");

        Seat seat3 =
                new Seat(3, "Available", "VIP");



        bus.addSeat(seat1);
        bus.addSeat(seat2);
        bus.addSeat(seat3);



        // Reserve seats

        seat1.reserve();
        seat2.reserve();
        seat3.reserve();



        // Booking automatically generates booking IDs

        Booking b1 =
                new Booking(seat1, bus, p1);

        Booking b2 =
                new Booking(seat2, bus, p2);

        Booking b3 =
                new Booking(seat3, bus, p3);



        // Confirm bookings

        b1.confirmBooking();
        b2.confirmBooking();
        b3.confirmBooking();



        // Store in HashTable

        bookingTable.put(b1.getBookingId(), b1);
        bookingTable.put(b2.getBookingId(), b2);
        bookingTable.put(b3.getBookingId(), b3);



        System.out.println("--- Fast Lookup by Booking ID ---");


        int searchBookingId = b2.getBookingId();


        Booking found =
                bookingTable.get(searchBookingId);



        System.out.println(
                "Searching for Booking #" + searchBookingId +
                        " -> \n  " +
                        (found != null ? found : "Not found")
        );



        System.out.println("\n--- Testing Booking Cancellation & Seat Release ---");


        if (found != null) {

            found.cancelBooking();

            found.getSeat().release();


            System.out.println(
                    "Updated Seat Status: "
                            + found.getSeat().getStatus()
            );
        }



        System.out.println("\n--- Bucket Distribution ---");

        bookingTable.printBucketDistribution();


        System.out.println(
                "\nTotal bookings stored: "
                        + bookingTable.getSize()
        );




        // ---------- PART 2: SET ADT ----------


        System.out.println(
                "\n--- Set ADT: Preventing Duplicate Seat Booking ---"
        );


        CustomHashSet<String> bookedSeats =
                new CustomHashSet<>();


        String seatKey1 =
                "BUS" + bus.getBusId() + "_SEAT_" + seat1.getSeatNumber();

        String seatKey2 =
                "BUS" + bus.getBusId() + "_SEAT_" + seat2.getSeatNumber();

        String duplicateSeatKey =
                "BUS" + bus.getBusId() + "_SEAT_" + seat1.getSeatNumber();



        System.out.println(
                "Booking " + seatKey1 + " -> " +
                        (bookedSeats.add(seatKey1)
                                ? "SUCCESS"
                                : "REJECTED (already booked)")
        );


        System.out.println(
                "Booking " + seatKey2 + " -> " +
                        (bookedSeats.add(seatKey2)
                                ? "SUCCESS"
                                : "REJECTED (already booked)")
        );


        System.out.println(
                "Booking " + duplicateSeatKey + " again -> " +
                        (bookedSeats.add(duplicateSeatKey)
                                ? "SUCCESS"
                                : "REJECTED (already booked)")
        );


        System.out.println(
                "\nTotal unique seats booked: "
                        + bookedSeats.size()
        );
    }
}