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
    public void run(String... strings) throws Exception {
        //Constructors
        //==============================================================================================================
        Flight flight = new Flight();
        flight.setToLocation("Hong Kong");
        flight.setFromLocation("New York");
        flight.setDate("08/08/2019");
        flight.setDepartingTime("5:00AM");
        flight.setArrivalTime("6:00PM");
        flight.setAvailableSeats(10);
        flight.setFlightClass(2);
        flight.setPrice(298);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setToLocation("Los Angeles");
        flight.setFromLocation("Washington DC");
        flight.setDate("08/10/2019");
        flight.setDepartingTime("9:00AM");
        flight.setArrivalTime("3:00PM");
        flight.setAvailableSeats(5);
        flight.setFlightClass(7);
        flight.setPrice(300);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setFromLocation("Los Angeles");
        flight.setToLocation("Washington DC");
        flight.setDate("08/12/2019");
        flight.setDepartingTime("9:00AM");
        flight.setArrivalTime("3:00PM");
        flight.setAvailableSeats(5);
        flight.setFlightClass(7);
        flight.setPrice(300);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setToLocation("Toronto");
        flight.setFromLocation("Austin");
        flight.setDate("08/21/2019");
        flight.setDepartingTime("4:00PM");
        flight.setArrivalTime("7:00PM");
        flight.setAvailableSeats(1);
        flight.setFlightClass(7);
        flight.setPrice(500);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setToLocation("Japan");
        flight.setFromLocation("San Diego");
        flight.setDate("08/01/2019");
        flight.setDepartingTime("1:00PM");
        flight.setArrivalTime("7:00PM");
        flight.setAvailableSeats(3);
        flight.setFlightClass(1);
        flight.setPrice(489);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setToLocation("Italy");
        flight.setFromLocation("Spain");
        flight.setDate("08/12/2019");
        flight.setDepartingTime("4:00PM");
        flight.setArrivalTime("5:00PM");
        flight.setAvailableSeats(2);
        flight.setFlightClass(1);
        flight.setPrice(375);
        flightRepository.save(flight);

        flight = new Flight();
        flight.setToLocation("Turkey");
        flight.setFromLocation("Sydney");
        flight.setDate("08/15/2019");
        flight.setDepartingTime("7:00AM");
        flight.setArrivalTime("9:00PM");
        flight.setAvailableSeats(30);
        flight.setFlightClass(3);
        flight.setPrice(700);
        flightRepository.save(flight);
    }
}
