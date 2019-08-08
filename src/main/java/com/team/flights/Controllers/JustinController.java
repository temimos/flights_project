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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

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
        System.out.println(id);
        Trip trip = tripRepository.findById(id);
        String data = name+" ,"+creditcardnumber+" ,"+cvv+" ,"+expirationdate;
        trip.setCreditCard(data);
        model.addAttribute("id", trip.getId());
        tripRepository.save(trip);
        return "/finalize";
    }
    //------------------------------------------------------------------------------------------------------------------
    @RequestMapping("/finalize")
    public String loadFinalizePage(Principal principal, Model model,@RequestParam(name = "id",required=false) long id) {
        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken) principal).getPrincipal()).getUser();
        Trip trip = tripRepository.findById(id);
        model.addAttribute("trips", trip);
        model.addAttribute("user",user);
        model.addAttribute("users", user);
        return "finalize";
    }
}
