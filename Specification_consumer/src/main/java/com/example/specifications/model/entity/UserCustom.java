package com.example.specifications.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;

    private int age;
}
