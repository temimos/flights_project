package com.team.flights.Repositories;

import com.team.flights.Beans.Trip;
import org.springframework.data.repository.CrudRepository;

public interface TripRepository extends CrudRepository<Trip,Long> {
    Trip findById(long id);
    Trip findByFlightToId(long id);
    Trip findByFlightFromId(long id);
    Trip findByUserId(long id);
}
