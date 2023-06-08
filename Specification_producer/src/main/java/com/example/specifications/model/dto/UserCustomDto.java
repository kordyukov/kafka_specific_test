package com.example.specifications.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;

@Data
@AllArgsConstructor
public class UserCustomDto {

    private String firstName;
    private String lastName;
    private String email;

    private int age;
}
