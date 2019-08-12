package com.team.flights.Loaders;

import com.team.flights.Beans.Flight;
import com.team.flights.Repositories.*;
import com.team.flights.SSUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Random;

@Component
public class FlightLoader implements CommandLineRunner {
    @Autowired
    SSUserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TripRepository tripRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void run(String...strings) throws Exception {
        //Constructors
        //==============================================================================================================
//        Flight flight = new Flight();
//        flight.setToLocation("Hong Kong");
//        flight.setFromLocation("New York");
//        flight.setDate("08/08/2019");
//        flight.setDepartingTime("5:00AM");
//        flight.setArrivalTime("6:00PM");
//        flight.setAvailableSeats(10);
//        flight.setFlightClass(2);
//        flight.setPrice(298);
//        flightRepository.save(flight);
//
//        flight = new Flight();
//        flight.setToLocation("Los Angeles");
//        flight.setFromLocation("Washington DC");
//        flight.setDate("08/10/2019");
//        flight.setDepartingTime("9:00AM");
//        flight.setArrivalTime("3:00PM");
//        flight.setAvailableSeats(5);
//        flight.setFlightClass(7);
//        flight.setPrice(300);
//        flightRepository.save(flight);
//
//        flight = new Flight();
//        flight.setFromLocation("Los Angeles");
//        flight.setToLocation("Washington DC");
//        flight.setDate("08/12/2019");
//        flight.setDepartingTime("9:00AM");
//        flight.setArrivalTime("3:00PM");
//        flight.setAvailableSeats(5);
//        flight.setFlightClass(7);
//        flight.setPrice(300);
//        flightRepository.save(flight);
//
//        flight = new Flight();
//        flight.setToLocation("Toronto");
//        flight.setFromLocation("Austin");
//        flight.setDate("08/21/2019");
//        flight.setDepartingTime("4:00PM");
//        flight.setArrivalTime("7:00PM");
//        flight.setAvailableSeats(1);
//        flight.setFlightClass(7);
//        flight.setPrice(500);
//        flightRepository.save(flight);
//
//        flight = new Flight();
//        flight.setToLocation("Japan");
//        flight.setFromLocation("San Diego");
//        flight.setDate("08/01/2019");
//        flight.setDepartingTime("1:00PM");
//        flight.setArrivalTime("7:00PM");
//        flight.setAvailableSeats(3);
//        flight.setFlightClass(1);
//        flight.setPrice(489);
//        flightRepository.save(flight);
//
//        flight = new Flight();
//        flight.setToLocation("Italy");
//        flight.setFromLocation("Spain");
//        flight.setDate("08/12/2019");
//        flight.setDepartingTime("4:00PM");
//        flight.setArrivalTime("5:00PM");
//        flight.setAvailableSeats(2);
//        flight.setFlightClass(1);
//        flight.setPrice(375);
//        flightRepository.save(flight);
//
//        flight = new Flight();
//        flight.setToLocation("Turkey");
//        flight.setFromLocation("Sydney");
//        flight.setDate("08/15/2019");
//        flight.setDepartingTime("7:00AM");
//        flight.setArrivalTime("9:00PM");
//        flight.setAvailableSeats(30);
//        flight.setFlightClass(3);
//        flight.setPrice(700);
//        flightRepository.save(flight);

        String[] locations = {"Washington DC", "Los Angeles", "New York", "Detroit", "Portland", "Salt Lake City",
            "Paris", "Warsaw", "Lisbon", "Madrid", "London", "Rome", "Athens", "Berlin", "Stockholm",
            "Tokyo", "Bern", "Moscow", "Amsterdam", "Hong Kong", "Seoul", "New Delhi", "Beijing", "Sydney",
                "Wellington", "Boston"};

        String[] depart = {"8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM",
                "4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM", "9:00PM", "10:00PM", "11:00PM", "12:00AM"};

        String[] arrive = {"8:00AM", "9:00AM", "10:00AM", "11:00AM", "12:00PM", "1:00PM", "2:00PM", "3:00PM",
                "4:00PM", "5:00PM", "6:00PM", "7:00PM", "8:00PM", "9:00PM", "10:00PM", "11:00PM", "12:00AM",
                "1:00AM", "2:00AM", "3:00AM", "4:00AM", "5:00AM", "6:00AM", "7:00AM"};

        LocalDate date = LocalDate.now();
        Random r = new Random();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("src/main/resources/sql.txt"), StandardCharsets.UTF_8))) {
            writer.write("use flights;\r\n" +
            "ALTER TABLE `flights`.`flight_data` CHANGE COLUMN `id` `id` BIGINT(20) NOT NULL AUTO_INCREMENT;\r\n" +
            "ALTER TABLE `flights`.`flight_data` CHANGE COLUMN `trip_id` `trip_id` BIGINT(20) NOT NULL,CHANGE COLUMN `user_id` `user_id` BIGINT(20) NOT NULL;\r\n" +
            "ALTER TABLE `flights`.`flight_data` CHANGE COLUMN `trip_id` `trip_id` BIGINT(20) NOT NULL DEFAULT 0,CHANGE COLUMN `user_id` `user_id` BIGINT(20) NOT NULL DEFAULT 0 ;\r\n");

            for (int i = 0; i < 31; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int h = 0; h < locations.length; h++) {
                        for (int k = 0; k < locations.length; k++) {
                            if (k != h) {
//                                Flight flight = new Flight(locations[k], locations[h], date.toString(), depart[r.nextInt(depart.length)],
//                                        arrive[r.nextInt(arrive.length)], r.nextInt(7) + 1, r.nextInt(41) + 10,
//                                        r.nextInt(421) + 80);
//                                flightRepository.save(flight);
//                                System.out.println("Saved object " + ((i * 2600) + (j * 26) + (k + 1)));
                                writer.write("INSERT INTO flight_data (to_location,from_location,date,price,available_seats,departing_time,arrival_time,flight_class) " +
                                        "VALUES (\"" + locations[k] + "\",\"" + locations[h] +
                                        "\",\"" + date.toString() + "\"," + (r.nextInt(421) + 80) + "," +
                                        (r.nextInt(41) + 10) + ",\"" + depart[r.nextInt(depart.length)] +
                                        "\",\"" + arrive[r.nextInt(arrive.length)] + "\"," + (r.nextInt(7) + 1) + ");\r\n");
                            }
                        }
                    }
                }
                date = date.plusDays(1);
            }
        }
    }
}
