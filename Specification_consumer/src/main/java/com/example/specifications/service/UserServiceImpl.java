package com.example.specifications.service;

import com.example.specifications.filter.UserSpecification;
import com.example.specifications.model.entity.UserCustom;
import com.example.specifications.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserCustom> findAll(UserSpecification spec) {
        return userRepository.findAll(spec);
    }

    @Override
    public UserCustom save(UserCustom userCustom) {
        return userRepository.save(userCustom);
    }
}
