package com.team.flights.Beans;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="Flight_Data")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "to_location")
    private String toLocation;

    @Column(name = "from_location")
    private long fromLocation;

    @Column(name = "date")
    private String date;

    @Column(name = "departing_time")
    private String departingTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @Column(name = "flight_class")
    private long flightClass;

    @Column(name = "avaliable_seats")
    private long avaliableSeats;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "trip_id")
    private long tripId;
}
