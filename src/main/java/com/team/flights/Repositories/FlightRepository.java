package com.team.flights.Repositories;

import com.team.flights.Beans.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FlightRepository extends CrudRepository<Flight,Long> {

    Iterable<Flight> findAllByToLocation(String tolocation);
    Iterable<Flight> findAllByFromLocation(String fLocation);

    ArrayList<Flight> findAll();
//    ArrayList<Flight> findAllByToLocation(String tolocation);
//    ArrayList<Flight> findAllByFromLocation(String fLocation);
    Flight findById(long id);
    Flight findByUserId(long id);
    Flight findByTripId(long id);
}
