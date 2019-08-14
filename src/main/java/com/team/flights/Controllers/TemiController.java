package com.team.flights.Controllers;

import com.team.flights.Beans.Flight;
import com.team.flights.Beans.Person;
import com.team.flights.Beans.Trip;
import com.team.flights.Beans.User;
import com.team.flights.CustomUserDetails;
import com.team.flights.Repositories.*;
import com.team.flights.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


@Controller
public class TemiController {
    @Autowired
    SSUserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    RoleRepository roleRepository;

    //    @RequestMapping("/")
//    public String listCourses(Model model) {
//        model.addAttribute("flightsto",getToLocations() );
//        return "index";
//    }
    ArrayList<String> getToLocations() {
        ArrayList<String> locations = new ArrayList<>();
        for (Flight flight : flightRepository.findAll()) {
            if (!locations.contains(flight.getToLocation())) {
                locations.add(flight.getToLocation());
            }
        }
        return locations;
    }

    ArrayList<String> getFromLocations() {
        ArrayList<String> locations = new ArrayList<>();
        for (Flight flight : flightRepository.findAll()) {
            if (!locations.contains(flight.getFromLocation())) {
                locations.add(flight.getFromLocation());
            }
        }
        return locations;
    }

    @RequestMapping("/summary")
    public String contactform(Model model, Principal principal) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user", ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser());

        Iterable<Flight> flights = new ArrayList<>();
        ArrayList<Flight> flightsForReal = new ArrayList<>();
        flights = flightRepository.findAll();
        for (Flight flight : flights) {
            if (user.getId() == flight.getUserId()) {
                flightsForReal.add(flight);
            }
        }
        model.addAttribute("flights", flightsForReal);

        ArrayList<Trip> trips = tripRepository.findAllByUserId(user.getId());
        model.addAttribute("list", trips);
        HashMap<Long, String> datesTo = new HashMap<>();
        HashMap<Long, String> datesFrom = new HashMap<>();
        for (Trip trip : trips) {
            long toId = trip.getFlightToId();
            long fromId = trip.getFlightFromId();
        }
        model.addAttribute("to", datesTo);
        model.addAttribute("from", datesFrom);
        return "summary";
    }

//    @RequestMapping("/boardingPass")
//    public String prettyPass(Principal principal, Model model) {
//        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
//        ArrayList<Trip> trips = tripRepository.findAllByUserId(user.getId());
//        model.addAttribute("list", trips);
//
//
//        HashMap<Long, String> datesTo = new HashMap<>();
//        HashMap<Long, String> datesFrom = new HashMap<>();
//        for (Trip trip : trips) {
//            long toId = trip.getFlightToId();
//            long fromId = trip.getFlightFromId();
//
//            if (toId != 0) {
//                datesTo.put(toId, flightRepository.findById(toId).getDate());
//            }
//            if (fromId != 0) {
//                datesFrom.put(fromId, flightRepository.findById(fromId).getDate());
//            }
////            Trip triped = tripRepository.findById(toId);
////            model.addAttribute("toLoc", flightRepository.findById(triped.getFlightToId()).getToLocation());
//
//
//        }
//        model.addAttribute("to", datesTo);
//        model.addAttribute("from", datesFrom);
//
//
//
//        return "prettypass";
//    }
//
//    @RequestMapping("/prettyPass")
//    public String boardingPass(Principal principal, Model model) {
//        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
//        ArrayList<Trip> trips = tripRepository.findAllByUserId(user.getId());
//        model.addAttribute("list", trips);
//        HashMap<Long, String> datesTo = new HashMap<>();
//        HashMap<Long, String> datesFrom = new HashMap<>();
//        for (Trip trip : trips) {
//            long toId = trip.getFlightToId();
//            long fromId = trip.getFlightFromId();
//            if (toId != 0) {
//                datesTo.put(toId, flightRepository.findById(toId).getDate());
//            }
//            if (fromId != 0) {
//                datesFrom.put(fromId, flightRepository.findById(fromId).getDate());
//            }
//        }
////        Flight flight = flightRepository.findAllByToLocation(to()).get();
//        model.addAttribute("to", datesTo);
//        model.addAttribute("from", datesFrom);
//
//
//        Iterable<Flight> flights = new ArrayList<>();
//        ArrayList<Flight> flightsForReal = new ArrayList<>();
//        flights = flightRepository.findAll();
//        for (Flight flight : flights) {
//            if (user.getId() == flight.getUserId()) {
//                flightsForReal.add(flight);
//            }
//
//            HashMap <String, String> map=getHashMap();
//            String airport = map.get(flight.getToLocation()) ;
//        }
//        model.addAttribute("flights", flightsForReal);
//
//        return "prettypass";
//    }

    public static HashMap<String, String> getHashMap() {

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("Washington DC", "DCA");
        map.put("Baltimore", "BWI");
        map.put("New York", "JFK");

        map.put("Los Angeles","LAX");
        map.put( "Detroit", "DTW");
        map.put("Portland" , "PTX");
        map.put("Salt Lake City","SLC" );
        map.put( "Paris", "CDG");
        map.put("Warsaw", "WAW" );
        map.put("Lisbon", "LIS" );
        map.put("Madrid","MAD" );
        map.put( "London", "LCA");
        map.put( "Rome", "FCO");
        map.put("Athens", "ATH" );
        map.put( "Berlin", "BER");
        map.put( "Stockholm", "ARN");
        map.put("Tokyo" , "HND");
        map.put("Bern", "BRN" );
        map.put( "Moscow", "DME");
        map.put( "Amsterdam", "AMS");
        map.put( "Hong Kong", "HKG");
        map.put("Seoul", "ICN" );
        map.put( "New Delhi", "DEL");
        map.put("Beijing", "PEK" );
        map.put(  "Sydney", "SYD");
        map.put("Wellington", "WLG" );
        map.put( "Boston","BOS");




        System.out.println("map = " + map);
        return map;

    }


    @RequestMapping("/viewTicket/{id}")
    public String viewTicket(@PathVariable("id") long id, Principal principal, Model model) {
        Trip trip = tripRepository.findById(id);
        Flight flightDeparture = flightRepository.findById(trip.getFlightFromId());
        Flight flightReturn = flightRepository.findById(trip.getFlightToId());
        HashMap <String, String> map=getHashMap();
        String airport = map.get(flightDeparture.getFromLocation());
        String airpotto = map.get(flightDeparture.getToLocation());
        String arrivalTime= flightDeparture.getArrivalTime();
        String depTime= flightDeparture.getDepartingTime();
        String depTime2= flightDeparture.getDepartingTime();


        model.addAttribute("air", airport);
        model.addAttribute("airp", airpotto);
        model.addAttribute("flight", flightDeparture);
        model.addAttribute("aTime", arrivalTime);

        model.addAttribute("depTime", depTime);
        model.addAttribute("flightReturn", flightReturn);

        ArrayList<Person> names = personRepository.findAllByTripId(trip.getId());
        model.addAttribute("names", names);

        return "prettypass";
    }
}
//
//String tolocation= "Baltimore";
//String fLocation= "DC";
//
//        if (flightRepository.findAllByToLocationAndFromLocation(tolocation, fLocation))=
//        {
//            model.addAttribute("message",
//                    "BWI");
//        }else{
//
//            model.addAttribute("message",
//                                "THP");
//        }
//
//        return "prettypass";






