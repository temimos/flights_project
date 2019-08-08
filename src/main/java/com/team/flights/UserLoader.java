package com.team.flights;

import com.team.flights.Beans.Role;
import com.team.flights.Beans.User;
import com.team.flights.Repositories.RoleRepository;
import com.team.flights.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class UserLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        Role userRole = roleRepository.save(new Role("USER"));
        Role adminRole = roleRepository.save(new Role("ADMIN"));

        User user = new User("John", "Smith", "12/19/2019", "United States",
                "john@gmail.com", "301-345-6789", "john", "password");
        user.setRoles(Arrays.asList(userRole));
        userRepository.save(user);

        user = new User("Admin", "Admin", "05/08/2019", "United States",
                "admin@admin.com", "301-777-8888", "admin", "password");
        user.setRoles(Arrays.asList(adminRole));
        userRepository.save(user);
    }
}
