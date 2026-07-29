package Users;

public class Passenger extends User {

    private static int passengerIdCounter = 1512;

    public Passenger(String name, String email, String phone) {
        super(passengerIdCounter++, name, email, phone);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "ID=" + userId +
                ", Name='" + name + '\'' +
                ", Phone='" + phone + '\'' +
                '}';
    }
}