package com.team.flights.Controllers;

import com.team.flights.Beans.Flight;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String contactform(  Model model, Principal principal) {
        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        model.addAttribute("user",((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser());

        Iterable<Flight> flights = new ArrayList<>();
        ArrayList<Flight> flightsForReal = new ArrayList<>();
        flights=flightRepository.findAll();
        for (Flight flight : flights){
            if(user.getId() == flight.getUserId()){
                flightsForReal.add(flight);
            }
        }
        model.addAttribute("flights",flightsForReal);
        return "summary";
    }

//    @RequestMapping("/prettypass")
//    public String prettyPass(Principal principal, Model model) {
//        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
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
//        model.addAttribute("to", datesTo);
//        model.addAttribute("from", datesFrom);
//        return "prettypass";
//    }

    @RequestMapping("/boardingPass")
    public String boardingPass(Principal principal, Model model) {
        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        ArrayList<Trip> trips = tripRepository.findAllByUserId(user.getId());
        model.addAttribute("list", trips);
        HashMap<Long, String> datesTo = new HashMap<>();
        HashMap<Long, String> datesFrom = new HashMap<>();
        for (Trip trip : trips) {
            long toId = trip.getFlightToId();
            long fromId = trip.getFlightFromId();
            if (toId != 0) {
                datesTo.put(toId, flightRepository.findById(toId).getDate());
            }
            if (fromId != 0) {
                datesFrom.put(fromId, flightRepository.findById(fromId).getDate());
            }
        }
        model.addAttribute("to", datesTo);
        model.addAttribute("from", datesFrom);
        return "prettypass";
    }
}




