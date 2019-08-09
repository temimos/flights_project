package com.team.flights.Controllers;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
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
    @RequestMapping("/payment")
    public String loadPaymentPage() {
        return "creditcard";
    }

    @PostMapping("/processpayment")
    public String processPaymentForm(@RequestParam(value = "name",required=false) String name,
                                     @RequestParam(value = "creditcardnumber",required=false) String creditcardnumber,
                                     @RequestParam(value = "cvv",required=false) String cvv,
                                     @RequestParam(value = "expirationdate",required=false) String expirationdate,
                                     @RequestParam(name = "id",required=false) long id,
                                     Model model, Principal principal){
        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        Trip trip = tripRepository.findById(id);
        String data = name+", "+creditcardnumber+", "+cvv+", "+expirationdate;
        model.addAttribute("trips", trip);
        trip.setCreditCard(data);
        model.addAttribute("users",user);
        model.addAttribute("id", trip.getId());
        tripRepository.save(trip);
        return "finalize";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/flight")
    public String loadFlightPage(Model model) {
        model.addAttribute("flights",flightRepository.findAll());
        Trip trip = new Trip();
        tripRepository.save(trip);
        model.addAttribute("tripId",trip.getId());
        model.addAttribute("on","departure");
        return "flight";
    }

    @PostMapping("/flightprocess")
    public String processFlight(@RequestParam(name = "id",required=false) long id,
                                @RequestParam(name = "tripId",required=false) long tripId,
                                @RequestParam(name = "on",required=false) String on, Model model, Principal principal) {
        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        Trip trip = tripRepository.findById(tripId);
        if (on.equals("departure")) {
            trip.setFlightFromId(id);
            tripRepository.save(trip);
            model.addAttribute("flights", flightRepository.findAll());
            model.addAttribute("tripId", trip.getId());
            model.addAttribute("on", "return");
            return "flight";
        } else {
            trip.setFlightToId(id);
            tripRepository.save(trip);
            model.addAttribute("id", trip.getId());

            return setNameData(model, trip, tripRepository);
        }
    }

    //------------------------------------------------------------------------------------------------------------------

}
