package com.team.flights.Helper;

import com.team.flights.Beans.Flight;

import java.util.ArrayList;

public class TravelHelper {

//    1 = E
//    2 = F
//    3 = B
//    4 = E + F
//    5 = F + B
//    6 = E + B
//    7 = E + F + B

    public static long convertToNumber(String name) {
        name = name.toLowerCase();
        if (name.startsWith("economy")) {
            return 1;
        } else if (name.startsWith("first")) {
            return 2;
        } else if (name.startsWith("business")) {
            return 3;
        } else {
            return -1;
        }
    }

    public static String convertToString(long number) {
        if (number == 1) {
            return "Economy";
        } else if (number == 2) {
            return "First Class";
        } else if (number == 3) {
            return "Business";
        } else {
            return "Unknown";
        }
    }

    public static boolean isAvailable(long request, long planeType) {
        ArrayList<Long> eco = new ArrayList<>();
        eco.add(1L);
        eco.add(4L);
        eco.add(6L);
        eco.add(7L);
        ArrayList<Long> first = new ArrayList<>();
        first.add(2L);
        first.add(4L);
        first.add(5L);
        first.add(7L);
        ArrayList<Long> business = new ArrayList<>();
        business.add(3L);
        business.add(5L);
        business.add(6L);
        business.add(7L);
        if (eco.contains(request) && eco.contains(planeType)) {
            return true;
        }
        else if (first.contains(request) && first.contains(planeType)) {
            return true;
        }
        else if (business.contains(request) && business.contains(planeType)) {
            return true;
        }
        return false;
    }

    public static ArrayList<String> getToLocations(ArrayList<Flight> flights) {
        ArrayList<String> locations = new ArrayList<>();
        for (Flight flight : flights) {
            if (!locations.contains(flight.getToLocation())) {
                locations.add(flight.getToLocation());
            }
        }
        return locations;
    }

    public static ArrayList<String> getFromLocations(ArrayList<Flight> flights) {
        ArrayList<String> locations = new ArrayList<>();
        for (Flight flight : flights) {
            if (!locations.contains(flight.getFromLocation())) {
                locations.add(flight.getFromLocation());
            }
        }
        return locations;
    }
}
