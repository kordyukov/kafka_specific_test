package com.example.specifications.model.dto;

import lombok.Data;
import lombok.Value;

@Data
public class UserCustomDto {

    private String firstName;
    private String lastName;
    private String email;

    private int age;
}
