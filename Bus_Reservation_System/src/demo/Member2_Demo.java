package demo;

import java.util.Scanner;

import models.Booking;
import models.Bus;
import models.RouteNetwork;
import models.RouteDataLoader;
import models.Seat;
import Users.Passenger;
import datastructures.CustomQueue;
import datastructures.CustomStack;

public class Member2_Demo {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        CustomQueue<Booking> queue = new CustomQueue<>();
        CustomStack<Booking> stack = new CustomStack<>();

        // 1. Initialize RouteNetwork using RouteDataLoader
        RouteNetwork network = RouteDataLoader.initializeRouteNetwork();

        // Sample buses registered in the system
        Bus bus1 = new Bus(101, "ND-1111", 40, "Active", "AC Express");
        Bus bus2 = new Bus(102, "CA-2222", 50, "Active", "Standard");

        System.out.println("=== REAL-TIME BUS RESERVATION SYSTEM ===");

        while (true) {

            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add New Booking");
            System.out.println("2. Display Available Routes (From RouteDataLoader)");
            System.out.println("3. Display Waiting List (Queue)");
            System.out.println("4. Display Booking History (Stack)");
            System.out.println("5. Undo Last Booking (Stack Pop)");
            System.out.println("6. Process Next Booking (Queue Dequeue)");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine(); // Clear buffer

            if (choice == 1) {

                // STEP 1: Display dynamically loaded routes from RouteNetwork graph
                System.out.println("\n-- STEP 1: Select Active Route --");
                System.out.println("Available Destinations & Network Graph:");
                network.cityGraph.displayGraph();

                System.out.print("\nEnter Target Route Choice (1 for Route 120 [Pettah->Horana], 2 for Route 350 [Galle->Matara]): ");
                int routeChoice = input.nextInt();
                input.nextLine();

                Bus selectedBus = (routeChoice == 2) ? bus2 : bus1;

                // STEP 2: Select Seat
                System.out.println("\n-- STEP 2: Select Seat --");
                System.out.print("Enter Seat Number (1-40): ");
                int seatNo = input.nextInt();
                input.nextLine();

                Seat seat = new Seat(seatNo, "Available", "Standard");
                selectedBus.addSeat(seat);

                // STEP 3: Enter Passenger Details
                System.out.println("\n-- STEP 3: Passenger Details --");
                System.out.print("Passenger Name: ");
                String name = input.nextLine();

                System.out.print("Email: ");
                String email = input.nextLine();

                System.out.print("Phone Number: ");
                String phone = input.nextLine();

                Passenger passenger = new Passenger(name, email, phone);

                // STEP 4: Confirm Booking
                if (seat.getStatus().equalsIgnoreCase("Available") && seat.reserve()) {

                    Booking booking = new Booking(seat, selectedBus, passenger);
                    booking.confirmBooking();

                    queue.enqueue(booking);
                    stack.push(booking);

                    System.out.println("\nBooking Confirmed Successfully!");
                    System.out.println(booking);

                } else {
                    System.out.println("\nError: Seat " + seatNo + " is already reserved!");
                }

            } else if (choice == 2) {
                System.out.println("\n--- ROUTE NETWORK (LOADED FROM ROUTEDATALOADER) ---");
                network.cityGraph.displayGraph();

            } else if (choice == 3) {
                System.out.println("\n--- Waiting List (Queue) ---");
                queue.displayQueue();

            } else if (choice == 4) {
                System.out.println("\n--- Booking History (Stack) ---");
                stack.displayStack();

            } else if (choice == 5) {
                System.out.println("\n--- Undoing Last Booking (Stack Pop) ---");
                stack.pop();

            } else if (choice == 6) {
                System.out.println("\n--- Processing Next Booking (Queue Dequeue) ---");
                queue.dequeue();

            } else if (choice == 7) {
                System.out.println("Exiting System... Thank you!");
                break;

            } else {
                System.out.println("Invalid Choice! Please try again.");
            }
        }

        input.close();
    }
}