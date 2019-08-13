package com.team.flights.Beans;

import javax.persistence.*;

@Entity
@Table(name="Flight_Data")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "to_location")
    private String toLocation;

    @Column(name = "from_location")
    private String fromLocation;

    @Column(name = "date")
    private String date;

    @Column(name = "departing_time")
    private String departingTime;

    @Column(name = "arrival_time")
    private String arrivalTime;

    @Column(name = "flight_class")
    private long flightClass;

    @Column(name = "available_seats")
    private long availableSeats;

    @Column(name = "price")
    private long price;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "trip_id")
    private long tripId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDepartingTime() {
        return departingTime;
    }

    public void setDepartingTime(String departingTime) {
        this.departingTime = departingTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public long getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(long flightClass) {
        this.flightClass = flightClass;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(long availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Flight(String toLocation, String fromLocation, String date, String departingTime, String arrivalTime,
                  long flightClass, long availableSeats, long userId, long tripId){
        this.toLocation = toLocation;
        this.fromLocation = fromLocation;
        this.date = date;
        this.departingTime = departingTime;
        this.arrivalTime = arrivalTime;
        this.flightClass = flightClass;
        this.availableSeats = availableSeats;
        this.userId = userId;
        this.tripId = tripId;
    }
    public Flight() {
    }

}
