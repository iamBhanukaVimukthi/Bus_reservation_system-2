package models;

import datastructures.CustomGraph;
import java.util.ArrayList;
import java.util.List;

public class RouteNetwork {

    // Attribute matching the UML diagram
    public CustomGraph<Route> cityGraph;

    public RouteNetwork() {
        this.cityGraph = new CustomGraph<>();
    }

    // Method to load a Route object into the internal CustomGraph
    public void loadRoute(Route route) {
        if (route != null) {
            cityGraph.addVertex(route);
        }
    }

    // Method to load a array/list of Route objects at once
    public void loadRoutes(Route[] routes) {
        for (Route r : routes) {
            loadRoute(r);
        }
    }

    public List<Route> findShortestPath(String source, String dest) {
        List<Route> path = new ArrayList<>();
        System.out.println("Finding shortest path from " + source + " to " + dest + "...");
        return path;
    }

    public List<Route> exploreAllPaths(String source) {
        List<Route> reachableRoutes = new ArrayList<>();
        System.out.println("Exploring all reachable routes starting from " + source + "...");
        return reachableRoutes;
    }
}