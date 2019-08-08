package com.team.flights.Repositories;

import com.team.flights.Beans.Flight;
import org.springframework.data.repository.CrudRepository;

public interface FlightRepository extends CrudRepository<Flight,Long> {
    Flight findById(long id);
    Flight findByUserId(long id);
    Flight findByTripId(long id);
}
