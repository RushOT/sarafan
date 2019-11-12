package com.remnant.sarafan.repositories;

import com.remnant.sarafan.domain.Message;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{
     
}