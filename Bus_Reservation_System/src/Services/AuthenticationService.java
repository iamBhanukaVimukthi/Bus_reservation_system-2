package Services;

import java.util.HashMap;
import java.util.Map;
import datastructures.CustomHashSet;
import Users.Admin;
import Users.Conductor;
import Users.Passenger;
import Users.User;

public class AuthenticationService {

    private CustomHashSet<String> usernames;
    private Map<String, String> userPasswords;
    private Map<String, Passenger> passengerStore;

    private Admin adminAccount;
    private Conductor conductorAccount;
    private User currentUser;

    public AuthenticationService() {
        this.usernames = new CustomHashSet<>();
        this.userPasswords = new HashMap<>();
        this.passengerStore = new HashMap<>();

        // Hardcoded staff accounts
        this.adminAccount = new Admin(1, "admin", "admin@system.com", "admin123", 111222333);
        this.conductorAccount = new Conductor(2, "conductor", "conductor@system.com", "cond123", 444555666);

        usernames.add("admin");
        userPasswords.put("admin", "admin123");

        usernames.add("conductor");
        userPasswords.put("conductor", "cond123");
    }

    /**
     * Creates a Passenger using only (name, email, phone).
     * The Passenger constructor auto-generates the passenger ID starting at 1512.
     */
    public boolean register(String name, String email, String phone, String username, String password) {

        if (usernames.contains(username)) {
            System.out.println("[REGISTRATION FAILED] Username '" + username + "' is already taken!");
            return false;
        }

        if (password == null || password.trim().isEmpty()) {
            System.out.println("[REGISTRATION FAILED] Password cannot be empty!");
            return false;
        }

        // 1. Create Passenger passing ONLY (name, email, phone)
        Passenger newPassenger = new Passenger(name, email, phone);

        // 2. Save account credentials and passenger object
        usernames.add(username);
        userPasswords.put(username, password);
        passengerStore.put(username, newPassenger);

        System.out.println("[REGISTRATION SUCCESS] " + newPassenger + " registered under username: " + username);
        return true;
    }

    public boolean login(String username, String password) {

        if ("admin".equalsIgnoreCase(username) && "admin123".equals(password)) {
            this.currentUser = adminAccount;
            System.out.println("[LOGIN SUCCESS] Logged in as ADMINISTRATOR.");
            return true;
        }

        if ("conductor".equalsIgnoreCase(username) && "cond123".equals(password)) {
            this.currentUser = conductorAccount;
            System.out.println("[LOGIN SUCCESS] Logged in as CONDUCTOR.");
            return true;
        }

        if (usernames.contains(username) && password.equals(userPasswords.get(username))) {
            this.currentUser = passengerStore.get(username);
            System.out.println("[LOGIN SUCCESS] Logged in as " + currentUser);
            return true;
        }

        System.out.println("[LOGIN FAILED] Invalid Username or Password!");
        return false;
    }

    public void logout() {
        if (currentUser != null) {
            System.out.println("User logged out successfully.");
            this.currentUser = null;
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public CustomHashSet<String> getUsernames() {
        return usernames;
    }
}