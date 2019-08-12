package com.team.flights.Repositories;

import com.team.flights.Beans.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface PersonRepository extends CrudRepository<Person, Long> {
    Person findById(long id);

    ArrayList<Person> findAllByTripId(long id);
}
