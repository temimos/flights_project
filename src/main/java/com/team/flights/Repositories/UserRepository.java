package com.team.flights.Repositories;

import com.team.flights.Beans.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String name);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.firstName = :firstName WHERE user.id = :id")
//    int updateFirstName(@Param("id") long id, @Param("firstName") String firstName);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.lastName = :lastName WHERE user.id = :id")
//    int updateLastName(@Param("id") long id, @Param("lastName") String lastName);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.birthDate = :birthDate WHERE user.id = :id")
//    int updateBirthDate(@Param("id") long id, @Param("birthDate") String birthDate);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.country = :country WHERE user.id = :id")
//    int updateCountry(@Param("id") long id, @Param("country") String country);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.email = :email WHERE user.id = :id")
//    int updateEmail(@Param("id") long id, @Param("email") String email);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.phone = :phone WHERE user.id = :id")
//    int updatePhone(@Param("id") long id, @Param("phone") String phone);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.username = :username WHERE user.id = :id")
//    int updateUsername(@Param("id") long id, @Param("username") String username);
//    //------------------------------------------------------------------------------------------------------------------
//    @Modifying
//    @Transactional
//    @Query("UPDATE User user SET user.password = :password WHERE user.id = :id")
//    int updatePassword(@Param("id") long id, @Param("password") String password);
//    //------------------------------------------------------------------------------------------------------------------

}
