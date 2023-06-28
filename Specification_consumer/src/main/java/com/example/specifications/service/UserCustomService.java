package com.example.specifications.service;

import com.example.specifications.filter.UserSpecification;
import com.example.specifications.model.entity.UserCustom;

import java.util.List;

public interface UserCustomService {
    List<UserCustom> findAll(UserSpecification spec);

    List<UserCustom> findAll();

    UserCustom save(UserCustom userCustom);

    List<UserCustom> findById(Long id);
}
