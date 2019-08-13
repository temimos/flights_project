package com.team.flights.Controllers;

import com.team.flights.Beans.Flight;
import com.team.flights.Beans.Trip;
import com.team.flights.Beans.User;
import com.team.flights.CustomUserDetails;
import com.team.flights.Helper.TravelHelper;
import com.team.flights.Repositories.*;
import com.team.flights.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;

import static com.team.flights.Controllers.JosephController.setNameData;

@Controller
public class JustinController {

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

    //------------------------------------------------------------------------------------------------------------------
    @PostMapping("/processpayment")
    public String processPaymentForm(@RequestParam(value = "name", required = false) String name,
                                     @RequestParam(value = "creditcardnumber", required = false) String creditcardnumber,
                                     @RequestParam(value = "cvv", required = false) String cvv,
                                     @RequestParam(value = "expirationdate", required = false) String expirationdate,
                                     @RequestParam(name = "id", required = false) long id,
                                     Model model, Principal principal) {
        boolean error = false;
        creditcardnumber = creditcardnumber.replace("-", "");
        if (creditcardnumber.length() != 16) {
            error = true;
        } else if (cvv.length() != 3) {
            error = true;
        } else if (name.length() <= 2) {
            error = true;
        } else if (expirationdate.length() <= 2) {
            error = true;
        }
        if (error) {
            model.addAttribute("id", id);
            model.addAttribute("error", "Incorrect credit card information");
            model.addAttribute("name", name);
            model.addAttribute("creditcardnumber", creditcardnumber);
            model.addAttribute("cvv", cvv);
            model.addAttribute("expirationdate", expirationdate);
            return "creditcard";
        }
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        Trip trip = tripRepository.findById(id);
        model.addAttribute("toLoc", flightRepository.findById(trip.getFlightToId()).getToLocation());
        model.addAttribute("fromLoc", flightRepository.findById(trip.getFlightToId()).getFromLocation());
        String data = name + ", " + creditcardnumber + ", " + cvv + ", " + expirationdate;
        model.addAttribute("trips", trip);
        trip.setCreditCard(data);
        model.addAttribute("users", user);
        model.addAttribute("id", trip.getId());
        model.addAttribute("classMap", TravelHelper.getClassMap());
        model.addAttribute("persons", personRepository.findAll());
        tripRepository.save(trip);
        return "finalize";
    }

    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/flight")
    public String loadFlightPage(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        Trip trip = new Trip();
        tripRepository.save(trip);
        model.addAttribute("tripId", trip.getId());
        model.addAttribute("on", "departure");
        return "flight";
    }

    @PostMapping("/flightprocess")
    public String processFlight(@RequestParam(name = "destFrom", required = false) String destFrom,
                                @RequestParam(name = "destTo", required = false) String destTo,
                                @RequestParam(name = "toDate", required = false) String toDate,
                                @RequestParam(name = "id", required = false) long id,
                                @RequestParam(name = "tripId", required = false) long tripId,
                                @RequestParam(name = "on", required = false) String on, Model model, Principal principal) {
        Trip trip = tripRepository.findById(tripId);

        if (on.equals("Departure")) {
            trip.setFlightFromId(id);
            tripRepository.save(trip);

            ArrayList<Flight> flights = new ArrayList<>();
            for (Flight flight : flightRepository.findAll()) {
                if (flight.getAvailableSeats() >= trip.getPassengers() &&
                        TravelHelper.isAvailable(trip.getType(), flight.getFlightClass()) &&
                        flight.getDate().contains(toDate) &&
                        flight.getFromLocation().contains(destTo) &&
                        flight.getToLocation().contains(destFrom)) {
                    flights.add(flight);
                }
            }

            model.addAttribute("flights", flights);
            model.addAttribute("tripId", trip.getId());
            model.addAttribute("on", "Return");
            model.addAttribute("classMap", TravelHelper.getClassMap());
            return "flight";
        } else {
            trip.setFlightToId(id);
            tripRepository.save(trip);
            model.addAttribute("id", trip.getId());

            return setNameData(model, trip, tripRepository);
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/admin")
    public String adminPage(Model model) {
        Flight flight = new Flight();
        flight.setDate(LocalDate.now().toString());
        model.addAttribute("flight", flight);
        model.addAttribute("flightsto", TravelHelper.getToLocations(flightRepository.findAll()));
        model.addAttribute("flightsfrom", TravelHelper.getFromLocations(flightRepository.findAll()));
        model.addAttribute("date", LocalDate.now());
        return "admin";
    }

    @PostMapping("/adminprocess")
    public String adminProcessPage(@Valid Flight flight, BindingResult result) {
        if (result.hasErrors()) {
            return "admin";
        }
        flightRepository.save(flight);
        return "redirect:/";
    }

    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/myprofile")
    public String profilePage(Model model, Principal principal) {
        User user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
//        Long id = user.getId();
//        user = userRepository.findById(id).get();
        model.addAttribute("users", user);
        return "myprofile";
    }

    @RequestMapping("/update/{id}")
    public String updateProfileForm(@PathVariable("id") long id, Model model, Principal principal) {
        model.addAttribute("user", userRepository.findById(id).get());
        model.addAttribute("id",id);
        return "editprofile";
    }

    @PostMapping("/processProfile")
    public String updateProfileProcessForm(@Valid User user,
                                           @RequestParam(value = "firstName", required = false) String firstName,
                                           @RequestParam(value = "lastName", required = false) String lastName,
                                           @RequestParam(value = "birthDate", required = false) String birthDate,
                                           @RequestParam(value = "country", required = false) String country,
                                           @RequestParam(value = "email", required = false) String email,
                                           @RequestParam(value = "phone", required = false) String phone,
                                           @RequestParam(value = "username", required = false) String username,
                                           @RequestParam(value = "password", required = false) String password,
                                           @RequestParam(name = "id", required = false) long id,
                                           BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            return "editprofile";
        }
        user = ((CustomUserDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setBirthDate(birthDate);
        user.setCountry(country);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUsername(username);
//        userRepository.updateFirstName(id,firstName);
//        userRepository.updateLastName(id,lastName);
//        userRepository.updateBirthDate(id,birthDate);
//        userRepository.updateCountry(id,country);
//        userRepository.updateEmail(id,email);
//        userRepository.updatePhone(id,phone);
//        userRepository.updateUsername(id,username);
//        userRepository.updatePassword(id,password);
        userRepository.save(user);
        model.addAttribute("users", user);
        return "myProfile";
    }
    //------------------------------------------------------------------------------------------------------------------
}
