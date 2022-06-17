package com.ek.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ek.api.models.RoleType;
import com.ek.api.models.Role;
import com.ek.api.repository.RoleRepository;

@SpringBootApplication
public class UserSecurityApp implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(UserSecurityApp.class, args);
	}

	@Autowired
	RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
		Optional<Role> adminRole = roleRepository.findByName(RoleType.ROLE_ADMIN);
		if (!adminRole.isPresent()) {
			Role role = new Role(RoleType.ROLE_ADMIN);
			roleRepository.save(role);
		}

	}

}
