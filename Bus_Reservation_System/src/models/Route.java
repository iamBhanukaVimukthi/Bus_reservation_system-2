package models;

import java.util.Objects;

public class Route {
    private int routeId;
    private String source;
    private String destination;
    private double distance;

    public Route(int routeId, String source, String destination, double distance) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
    }

    public double calculateDistance() {
        return this.distance;
    }

    public void addStop() {
        // Business logic to add an intermediate stop
    }

    public void removeStop() {
        // Business logic to remove an intermediate stop
    }

    // Getters and Setters
    public int getRouteId() { return routeId; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public double getDistance() { return distance; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return routeId == route.routeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId);
    }

    @Override
    public String toString() {
        return "Route #" + routeId + " [" + source + " -> " + destination + " (" + distance + " km)]";
    }
}