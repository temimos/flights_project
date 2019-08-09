package com.team.flights.Beans;

import javax.persistence.*;

@Entity
@Table(name="Trip_Data")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private long type;

    @Column(name = "passengers")
    private long passengers;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "flightTo_id")
    private long flightToId;

    @Column(name = "flightFrom_id")
    private long flightFromId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

    public long getPassengers() {
        return passengers;
    }

    public void setPassengers(long passengers) {
        this.passengers = passengers;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getFlightToId() {
        return flightToId;
    }

    public void setFlightToId(long flightToId) {
        this.flightToId = flightToId;
    }

    public long getFlightFromId() {
        return flightFromId;
    }

    public void setFlightFromId(long flightFromId) {
        this.flightFromId = flightFromId;
    }
    public Trip(long type, long passengers, String creditCard, long userId, long flightToId, long flightFromId) {
        this.type = type;
        this.passengers = passengers;
        this.creditCard = creditCard;
        this.userId = userId;
        this.flightToId = flightToId;
        this.flightFromId = flightFromId;
    }
    public Trip() {
    }
}
