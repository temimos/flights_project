package com.team.flights.Repositories;

import com.team.flights.Beans.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);

    @Query("UPDATE User u SET u.address = :address WHERE u.id = :userId")
    int updateAddress(@Param("userId") int userId, @Param("address") String address);
}
