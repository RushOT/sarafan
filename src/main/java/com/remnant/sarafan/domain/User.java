package com.remnant.sarafan.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    private String id;
    private String name;
    private String gender;
    private String locale;
    private String userpic;
    private String email;
    private LocalDateTime lastVisit;
}