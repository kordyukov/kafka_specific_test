package com.example.specifications.service;

import com.example.specifications.model.dto.SignupRequest;
import com.example.specifications.model.entity.User;

public interface UserService {
    User registerUser(SignupRequest signUpRequest);
}
