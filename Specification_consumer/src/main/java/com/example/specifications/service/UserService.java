package com.example.specifications.service;

import com.example.specifications.filter.UserSpecification;
import com.example.specifications.model.entity.UserCustom;

import java.util.List;

public interface UserService {
    List<UserCustom> findAll(UserSpecification spec);

    UserCustom save(UserCustom userCustom);
}
