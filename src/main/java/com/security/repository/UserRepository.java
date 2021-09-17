package com.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.security.model.User;

@Component
public interface UserRepository extends JpaRepository<User, Long> {
	
	   Optional<User> findByEmail(String email);

}
