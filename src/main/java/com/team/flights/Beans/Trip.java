package com.team.flights.Beans;

import javax.persistence.*;

@Entity
@Table(name="Flight_Data")
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "passengers")
    private long passengers;

    @Column(name = "credit_card")
    private String creditCard;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "flight_id")
    private long flightId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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

    public long getFlightId() {
        return flightId;
    }

    public void setFlightId(long flightId) {
        this.flightId = flightId;
    }

    public Trip(String type, long passengers, String creditCard, long userId, long flightId) {
        this.type = type;
        this.passengers = passengers;
        this.creditCard = creditCard;
        this.userId = userId;
        this.flightId = flightId;
    }
    public Trip() {
    }
}
