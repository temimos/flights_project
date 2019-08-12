package com.team.flights.Repositories;

import com.team.flights.Beans.Trip;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TripRepository extends CrudRepository<Trip, Long> {
    Trip findById(long id);

    Trip findByFlightToId(long id);

    Trip findByFlightFromId(long id);

    ArrayList<Trip> findAllByUserId(long id);
}
