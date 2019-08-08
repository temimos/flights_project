package com.team.flights.Controllers;

import com.team.flights.Beans.Flight;
import com.team.flights.Beans.Person;
import com.team.flights.Beans.Trip;
import com.team.flights.Beans.User;
import com.team.flights.CustomUserDetails;
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
import java.util.ArrayList;
import java.util.Arrays;

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
        if (result.hasErrors()){
            return "registration";
        } else {
            user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
            userRepository.save(user);
            model.addAttribute("created",  true);
        }
        return "login";
    }

    @RequestMapping("/form")
    public String nameForm(Principal principal, Model model) {
        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        Trip trip = new Trip();
        trip.setPassengers(4);
        trip.setType(7L);

        tripRepository.save(trip);

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
        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
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
                    return "payment";
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
}
