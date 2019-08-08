package com.team.flights.Controllers;

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
        Trip trip = tripRepository.findByUserId(user.getId());
        if (trip == null) {
            return "/";
        } else {
            ArrayList<Person> finished = personRepository.findAllByTripId(trip.getId());
            ArrayList<Person> persons = new ArrayList<>();
            long size = 1;
            if (finished != null) {
                size = Math.min((finished.size() + 1), trip.getPassengers());
            }
            if (size > trip.getPassengers()) {

                model.addAttribute("people", finished);
                model.addAttribute("trip", trip);
                return "summary";
            }
            for (int i = 0; i < size; i++) {
                persons.add(new Person());
            }
            model.addAttribute("list", persons);
        }
        return "form";
    }
}
