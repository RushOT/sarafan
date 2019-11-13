package com.remnant.sarafan.repositories;

import com.remnant.sarafan.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<User, String>{
    
}