package com.team.flights.Controllers;

import com.team.flights.Beans.Flight;
import com.team.flights.Beans.Person;
import com.team.flights.Beans.Trip;
import com.team.flights.Beans.User;
import com.team.flights.CustomUserDetails;
import com.team.flights.Helper.TravelHelper;
import com.team.flights.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@Controller
public class JosephController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    FlightRepository flightRepository;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
            userRepository.save(user);
            model.addAttribute("created", true);
        }
        return "login";
    }

    @RequestMapping("/form")
    public String nameForm(Principal principal, Model model) {
        Trip trip = new Trip();
        return setNameData(model, trip, tripRepository);
    }

    static String setNameData(Model model, Trip trip, TripRepository tripRepository) {

        ArrayList<Person> persons = new ArrayList<>();

        persons.add(new Person("", trip.getId()));

        if (trip.getPassengers() == 1) {
            model.addAttribute("btnText", "Finish - View Order");
        } else {
            model.addAttribute("btnText", "Add Person");
        }

        model.addAttribute("num", 0);
        model.addAttribute("total", trip.getPassengers());
        model.addAttribute("list", persons);
        model.addAttribute("id", trip.getId());

        return "form";
    }

    @PostMapping("/addName")
    public String addName(@RequestParam(name = "id") long id,
                          @RequestParam(name = "person") String name, Principal principal, Model model) {
        Person person = new Person();
        person.setName(name);
        person.setTripId(id);
        personRepository.save(person);
        Trip trip = tripRepository.findById(id);
        if (trip == null) {
            return "/";
        } else {
            ArrayList<Person> finished = personRepository.findAllByTripId(trip.getId());
            ArrayList<Person> persons = new ArrayList<>();
            long size = 0;
            if (finished != null) {
                size = finished.size();
                persons.addAll(finished);
                if (finished.size() >= trip.getPassengers()) {
                    model.addAttribute("people", finished);
                    model.addAttribute("trip", trip);
                    model.addAttribute("id", trip.getId());
                    return "creditcard";
                }
            }
            //for (int i = 0; i < size; i++) {
            persons.add(new Person("", trip.getId()));
            //}
            if (size + 1 >= trip.getPassengers()) {
                model.addAttribute("btnText", "Finish - View Order");
            } else {
                model.addAttribute("btnText", "Add Person");
            }

            model.addAttribute("num", size);
            model.addAttribute("total", trip.getPassengers());
            model.addAttribute("list", persons);
            model.addAttribute("id", trip.getId());
        }
        return "form";
    }

    @PostMapping("/processFinalize")
    public String addName(@RequestParam(name = "id") long id, Principal principal, Model model) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        Trip trip = tripRepository.findById(id);
        trip.setUserId(user.getId());
        tripRepository.save(trip);
        return "redirect:/boardingPass";
    }

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
        return "boardingpass";
    }

//    @RequestMapping("/viewTicket/{id}")
//    public String viewTicket(@PathVariable("id") long id, Principal principal, Model model) {
//        Trip trip = tripRepository.findById(id);
//        Flight flightTo = flightRepository.findById(trip.getFlightToId());
//        Flight flightFrom = flightRepository.findById(trip.getFlightFromId());
//        return "prettypass";
//    }

    @RequestMapping("/search")
    public String loadFlightPage(@RequestParam(name = "type", required = false) long tripType,
                                 @RequestParam(name = "class", required = false) long flightClass,
                                 @RequestParam(name = "passengers", required = false) long passengers,
                                 @RequestParam(name = "destFrom", required = false) String destFrom,
                                 @RequestParam(name = "destTo", required = false) String destTo,
                                 @RequestParam(name = "fromDate", required = false) String fromDate,
                                 @RequestParam(name = "toDate", required = false) String toDate,
                                 Model model, Principal principal) {

        Trip trip = new Trip();
        trip.setPassengers(passengers);
        trip.setType(flightClass);
        tripRepository.save(trip);

//        fromDate = fromDate.split("-")[1] + "/" + fromDate.split("-")[2] + "/" + fromDate.split("-")[0];
//        toDate = toDate.split("-")[1] + "/" + toDate.split("-")[2] + "/" + toDate.split("-")[0];

        ArrayList<Flight> flights = new ArrayList<>();
        for (Flight flight : flightRepository.findAll()) {
            if (flight.getAvailableSeats() >= trip.getPassengers() &&
                    TravelHelper.isAvailable(trip.getType(), flight.getFlightClass()) &&
                    flight.getDate().contains(fromDate) &&
                    flight.getFromLocation().contains(destFrom) &&
                    flight.getToLocation().contains(destTo)) {
                flights.add(flight);
            }
        }

        model.addAttribute("destFrom", destFrom);
        model.addAttribute("destTo", destTo);
        model.addAttribute("toDate", toDate);
        model.addAttribute("flights", flights);
        model.addAttribute("tripId", trip.getId());
        if (tripType == 1) {
            model.addAttribute("on", "Departure");
        } else {
            model.addAttribute("on", "One-Way");
        }
        return "flight";
    }

    @RequestMapping("/")
    public String listCourses(Model model) {
        model.addAttribute("flightsto", TravelHelper.getToLocations(flightRepository.findAll()));
        model.addAttribute("flightsfrom", TravelHelper.getFromLocations(flightRepository.findAll()));
        model.addAttribute("date", LocalDate.now());
        return "index";
    }
}
