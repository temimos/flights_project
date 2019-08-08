package com.team.flights.Loaders;

import com.team.flights.Beans.Flight;
import com.team.flights.Repositories.*;
import com.team.flights.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class FlightLoader implements CommandLineRunner {
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

    @Override
    public void run(String...strings) throws Exception {
        //Constructors
        //==============================================================================================================
        Flight flight = new Flight();
        flight.setToLocation("Hong Kong");
        flight.setFromLocation("New York");
        flight.setDate("8/8/2019");
        flight.setDepartingTime("5:00AM");
        flight.setArrivalTime("6:00PM");
        flight.setAvailableSeats(10);
        flight.setFlightClass(2);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setToLocation("Los Angeles");
        flight.setFromLocation("Washington DC");
        flight.setDate("8/10/2019");
        flight.setDepartingTime("9:00AM");
        flight.setArrivalTime("3:00PM");
        flight.setAvailableSeats(5);
        flight.setFlightClass(7);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setToLocation("Toronto");
        flight.setFromLocation("Austin");
        flight.setDate("8/21/2019");
        flight.setDepartingTime("4:00PM");
        flight.setArrivalTime("7:00PM");
        flight.setAvailableSeats(1);
        flight.setFlightClass(7);
        flightRepository.save(flight);
    }
}
