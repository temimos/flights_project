package com.team.flights.Repositories;

import com.team.flights.Beans.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person,Long> {
    Person findById(long id);
}
