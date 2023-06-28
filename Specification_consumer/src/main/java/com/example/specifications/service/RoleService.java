package com.example.specifications.service;

import com.example.specifications.model.entity.Role;

public interface RoleService {
    Role findByName(String name);
}
