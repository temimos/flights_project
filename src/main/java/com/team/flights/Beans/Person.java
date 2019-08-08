package com.team.flights.Beans;

import javax.persistence.*;

@Entity
@Table(name="Person_Data")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "trip_id")
    private long tripId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public Person(String name, long tripId) {
        this.name = name;
        this.tripId = tripId;
    }

    public Person(){
    }
}

