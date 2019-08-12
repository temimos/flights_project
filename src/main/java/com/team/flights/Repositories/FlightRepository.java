package com.team.flights.Repositories;

import com.team.flights.Beans.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface FlightRepository extends CrudRepository<Flight,Long> {

    long countByState(String state);
    Iterable<Flight> findAllByToLocation(String tolocation);
    Iterable<Flight> findAllByFromLocation(String fLocation);

    ArrayList<Flight> findAll();
//    ArrayList<Flight> findAllByToLocation(String location);
//    ArrayList<Flight> findAllByFromLocation(String location);
    Flight findById(long id);
    Flight findByUserId(long id);
    Flight findByTripId(long id);
}
