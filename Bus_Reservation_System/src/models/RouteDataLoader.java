package models;

import datastructures.*;
public class RouteDataLoader {

    // Method expected by your demo class
    public static CustomGraph<Route> loadRoutes() {
        RouteNetwork network = initializeRouteNetwork();
        return network.cityGraph;
    }

    // Method that creates and populates the RouteNetwork
    public static RouteNetwork initializeRouteNetwork() {
        RouteNetwork network = new RouteNetwork();

        Route r103 = new Route(103, "Fort", "Narahenpita / Borella", 6.4);
        Route r120 = new Route(120, "Pettah", "Piliyandala / Kesbewa / Horana", 41.0);
        Route r122 = new Route(122, "Pettah", "Avissawella", 61.0);
        Route r125 = new Route(125, "Pettah", "Padukka / Ingiriya", 56.0);
        Route r138_1 = new Route(1381, "Fort", "Kirillawala", 22.0);
        Route r138_2 = new Route(1382, "Pettah", "Mattegoda", 25.0);
        Route r138_4 = new Route(1384, "Pettah", "Athurugiriya", 29.0);
        Route r170 = new Route(170, "Pettah", "Athurugiriya (via 170)", 21.0);
        Route r180 = new Route(180, "Pettah", "Nittambuwa", 40.0);
        Route r193 = new Route(193, "Town Hall", "Kadawatha", 17.0);
        Route r200 = new Route(200, "Pettah", "Gampaha", 31.0);
        Route r224 = new Route(224, "Pettah", "Pugoda", 41.0);
        Route r225 = new Route(225, "Pettah", "Kirindiwala", 45.0);
        Route r230 = new Route(230, "Pettah", "Kiribathgoda", 18.5);
        Route r234 = new Route(234, "Pettah", "Delgoda", 25.0);
        Route r255 = new Route(255, "Kottawa", "Mt Lavinia", 17.0);
        Route r341 = new Route(341, "Maharagama", "Piliyandala", 7.5);
        Route r345 = new Route(345, "Maharagama", "Katuwawala", 3.2);
        Route r350 = new Route(350, "Galle", "Matara", 44.3);

        network.loadRoute(r103);
        network.loadRoute(r120);
        network.loadRoute(r122);
        network.loadRoute(r125);
        network.loadRoute(r138_1);
        network.loadRoute(r138_2);
        network.loadRoute(r138_4);
        network.loadRoute(r170);
        network.loadRoute(r180);
        network.loadRoute(r193);
        network.loadRoute(r200);
        network.loadRoute(r224);
        network.loadRoute(r225);
        network.loadRoute(r230);
        network.loadRoute(r234);
        network.loadRoute(r255);
        network.loadRoute(r341);
        network.loadRoute(r345);
        network.loadRoute(r350);

        return network;
    }
}