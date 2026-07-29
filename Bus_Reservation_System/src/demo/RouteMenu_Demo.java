package demo;

import models.*;
import models.RouteDataLoader;
import datastructures.CustomGraph;
import java.util.Scanner;

public class RouteMenu_Demo {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // 1. Load the pre-configured graph using RouteDataLoader
        CustomGraph<Route> routeGraph = RouteDataLoader.loadRoutes();

        System.out.println("==================================================");
        System.out.println("   REAL-TIME BUS RESERVATION - ROUTE NETWORK");
        System.out.println("==================================================");

        while (true) {
            System.out.println("\n--- ROUTE EXPLORER MENU ---");
            System.out.println("1. Display All Bus Routes & Distances");
            System.out.println("2. Display Total Network Statistics");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1-3): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  Invalid input! Please enter a number between 1 and 3.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n=== ALL DIRECT BUS ROUTES & DISTANCES ===");
                    // Displays the Adjacency List showing source -> destination (distance in km)
                    routeGraph.displayGraph();
                    break;

                case 2:
                    System.out.println("\n=== NETWORK STATISTICS ===");
                    System.out.println("Total Stations/Hubs Connected: " + routeGraph.getVertexCount());
                    break;

                case 3:
                    System.out.println("\nExiting Route Explorer... Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("  Invalid Choice! Please enter a option between 1 and 3.");
            }
        }
    }
}