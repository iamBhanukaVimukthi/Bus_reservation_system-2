
import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

import Services.AuthenticationService;
import Users.Admin;
import Users.Conductor;
import Users.Passenger;
import Users.User;
import datastructures.CustomBST;
import datastructures.CustomQueue;
import datastructures.CustomStack;
import models.Booking;
import models.Bus;
import models.Route;
import models.RouteDataLoader;
import models.RouteNetwork;
import models.Schedule;
import models.Seat;

public class Main {

    // --- SYSTEM SERVICES & DATA STORES ---
    private static AuthenticationService authService = new AuthenticationService();
    private static RouteNetwork routeNetwork;
    private static CustomBST<Schedule> scheduleTree = new CustomBST<>();
    private static CustomQueue<Booking> bookingQueue = new CustomQueue<>();
    private static CustomStack<Booking> bookingStack = new CustomStack<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Bootstrap Route, Bus, and Schedule Data
        bootstrapSystemData();

        System.out.println("==================================================");
        System.out.println("   REAL-TIME BUS RESERVATION MANAGEMENT SYSTEM   ");
        System.out.println("==================================================");

        while (true) {
            // ----------------------------------------------------
            // 1. UNIFIED AUTHENTICATION SCREEN
            // ----------------------------------------------------
            while (authService.getCurrentUser() == null) {
                System.out.println("\n--- SYSTEM LOGIN PORTAL ---");
                System.out.println("1. Login (Unified)");
                System.out.println("2. Register New Customer Account");
                System.out.println("3. Exit System");
                System.out.print("Select choice (1-3): ");

                int choice;
                try {
                    choice = Integer.parseInt(input.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("  Invalid input! Enter a number.");
                    continue;
                }

                if (choice == 1) {
                    System.out.println("\n--- LOGIN ---");
                    System.out.print("Enter Username / User ID: ");
                    String username = input.nextLine().trim();

                    System.out.print("Enter Password: ");
                    String password = input.nextLine().trim();

                    authService.login(username, password);

                } else if (choice == 2) {
                    System.out.println("\n--- NEW CUSTOMER REGISTRATION ---");
                    System.out.print("Full Name: ");
                    String name = input.nextLine().trim();

                    System.out.print("Email: ");
                    String email = input.nextLine().trim();

                    System.out.print("Phone Number: ");
                    String phone = input.nextLine().trim();

                    System.out.print("Choose Username: ");
                    String username = input.nextLine().trim();

                    // ADDED: Prompt for Password
                    System.out.print("Set Password: ");
                    String password = input.nextLine().trim();

                    // Pass password into AuthenticationService
                    authService.register(name, email, phone, username, password);


                } else if (choice == 3) {
                    System.out.println("\nExiting System... Goodbye!");
                    input.close();
                    return;
                } else {
                    System.out.println("  Invalid Option!");
                }
            }

            // ----------------------------------------------------
            // 2. DASHBOARD LOOP BASED ON AUTHENTICATED USER ROLE
            // ----------------------------------------------------
            User currentUser = authService.getCurrentUser();

            while (authService.getCurrentUser() != null) {
                System.out.println("\n==================================================");
                System.out.println(" LOGGED IN USER: " + currentUser.getName().toUpperCase());
                System.out.println("==================================================");

                if (currentUser instanceof Admin) {
                    showAdminDashboard(input);
                } else if (currentUser instanceof Conductor) {
                    showConductorDashboard(input);
                } else if (currentUser instanceof Passenger) {
                    showCustomerDashboard(input, (Passenger) currentUser);
                }
            }
        }
    }

    // --- ROLE-BASED DASHBOARDS ---

    private static void showAdminDashboard(Scanner input) {
        System.out.println("\n--- ADMINISTRATOR CONTROLS ---");
        System.out.println("1. View Route Network Graph (CustomGraph)");
        System.out.println("2. Display All Trip Schedules (CustomBST)");
        System.out.println("3. View Full Booking History (CustomStack)");
        System.out.println("4. Undo Last Booking Operation (Stack Pop)");
        System.out.println("5. Logout");
        System.out.print("Select choice (1-5): ");

        int choice = parseInputChoice(input);
        switch (choice) {
            case 1 -> routeNetwork.cityGraph.displayGraph();
            case 2 -> scheduleTree.display();
            case 3 -> {
                System.out.println("\n--- BOOKING HISTORY STACK ---");
                bookingStack.displayStack();
            }
            case 4 -> {
                System.out.println("\n--- UNDOING LAST BOOKING ACTION ---");
                bookingStack.pop();
            }
            case 5 -> authService.logout();
            default -> System.out.println("Invalid option!");
        }
    }

    private static void showConductorDashboard(Scanner input) {
        System.out.println("\n--- CONDUCTOR CONTROLS ---");
        System.out.println("1. Process Next Passenger (Queue Dequeue)");
        System.out.println("2. Display Waiting List Queue (CustomQueue)");
        System.out.println("3. View Trip Schedules (CustomBST)");
        System.out.println("4. Logout");
        System.out.print("Select choice (1-4): ");

        int choice = parseInputChoice(input);
        switch (choice) {
            case 1 -> {
                System.out.println("\n--- DEQUEUING NEXT RESERVATION ---");
                bookingQueue.dequeue();
            }
            case 2 -> {
                System.out.println("\n--- PASSENGER WAITING LIST QUEUE ---");
                bookingQueue.displayQueue();
            }
            case 3 -> scheduleTree.display();
            case 4 -> authService.logout();
            default -> System.out.println("Invalid option!");
        }
    }

    private static void showCustomerDashboard(Scanner input, Passenger passenger) {
        System.out.println("\n--- CUSTOMER PORTAL ---");
        System.out.println("1. Explore Route Network Graph");
        System.out.println("2. Search Schedules & Departures (BST)");
        System.out.println("3. Reserve a Seat");
        System.out.println("4. Logout");
        System.out.print("Select choice (1-4): ");

        int choice = parseInputChoice(input);
        switch (choice) {
            case 1 -> routeNetwork.cityGraph.displayGraph();
            case 2 -> scheduleTree.display();
            case 3 -> handleNewBooking(input, passenger);
            case 4 -> authService.logout();
            default -> System.out.println("Invalid option!");
        }
    }

    // --- RESERVATION HANDLER ---

    private static void handleNewBooking(Scanner input, Passenger passenger) {
        System.out.println("\n================ CREATE RESERVATION ================");
        System.out.println("Available Trips:");
        scheduleTree.display();

        System.out.print("\nSelect Bus ID (e.g., 101 or 102): ");
        int busId = parseInputChoice(input);

        Bus selectedBus = new Bus(busId, (busId == 101 ? "ND-1111" : "CA-2222"), 40, "Active", "AC Express");

        System.out.print("Enter Seat Number (1-40): ");
        int seatNo = parseInputChoice(input);

        Seat seat = new Seat(seatNo, "Available", "Standard");
        selectedBus.addSeat(seat);

        // Reserve seat and create booking
        if (seat.getStatus().equalsIgnoreCase("Available") && seat.reserve()) {
            Booking booking = new Booking(seat, selectedBus, passenger);
            booking.confirmBooking();

            // Push to data structures
            bookingQueue.enqueue(booking);
            bookingStack.push(booking);

            System.out.println("\n[SUCCESS] Booking Confirmed Successfully!");
            System.out.println(booking);
        } else {
            System.out.println("\n[ERROR] Selected seat is unavailable!");
        }
    }

    // --- HELPER UTILITIES ---

    private static void bootstrapSystemData() {
        // Load graph network
        routeNetwork = RouteDataLoader.initializeRouteNetwork();

        // Instantiate buses
        Bus bus101 = new Bus(101, "ND-1111", 40, "Active", "AC Express");
        Bus bus102 = new Bus(102, "CA-2222", 50, "Active", "Standard");

        // Instantiate routes
        Route r120 = new Route(120, "Pettah", "Horana", 41.0);
        Route r350 = new Route(350, "Galle", "Matara", 44.3);

        // Populate Schedule BST
        scheduleTree.insert(new Schedule(5001, Time.valueOf("06:30:00"), Time.valueOf("08:00:00"), new Date(), bus101, r120));
        scheduleTree.insert(new Schedule(5002, Time.valueOf("09:15:00"), Time.valueOf("10:45:00"), new Date(), bus102, r350));
    }

    private static int parseInputChoice(Scanner input) {
        try {
            return Integer.parseInt(input.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}