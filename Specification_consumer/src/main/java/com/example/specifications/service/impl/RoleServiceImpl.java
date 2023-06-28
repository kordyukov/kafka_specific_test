package com.example.specifications.service.impl;

import com.example.specifications.model.entity.Role;
import com.example.specifications.repository.RoleRepository;
import com.example.specifications.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException
                        ("Error: Role: %s is not found.".formatted(name)));
    }
}
