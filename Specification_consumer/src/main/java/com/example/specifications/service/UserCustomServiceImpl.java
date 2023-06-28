package com.example.specifications.service;

import com.example.specifications.filter.UserSpecification;
import com.example.specifications.model.entity.UserCustom;
import com.example.specifications.repository.UserCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCustomServiceImpl implements UserCustomService {
    private final UserCustomRepository userCustomRepository;

    @Override
    public List<UserCustom> findAll(UserSpecification spec) {
        return userCustomRepository.findAll(spec);
    }

    @Override
    public List<UserCustom> findAll() {
        return userCustomRepository.findAll();
    }

    @Override
    public UserCustom save(UserCustom userCustom) {
        return userCustomRepository.save(userCustom);
    }

    @Override
    public List<UserCustom> findById(Long id) {
        return userCustomRepository.findAll();
    }
}
