package com.example.specifications.service.impl;

import com.example.specifications.mapper.RoleMapper;
import com.example.specifications.model.dto.RoleDto;
import com.example.specifications.model.dto.SignupRequest;
import com.example.specifications.model.entity.Status;
import com.example.specifications.model.entity.User;
import com.example.specifications.model.enums.ERole;
import com.example.specifications.repository.UserRepository;
import com.example.specifications.rest.exeption.RoleNotFoundException;
import com.example.specifications.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleMapper roleMapper;
    private final PasswordEncoder encoder;

    @Override
    public User registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.username())) {
            throw new EntityNotFoundException("Error: Username %s is already taken!".formatted(signUpRequest.username()));
        }

        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new EntityNotFoundException("Error: Email %s is already in use!".formatted(signUpRequest.email()));
        }

        List<RoleDto> strRoles = signUpRequest.roles();
        List<RoleDto> roles = strRoles
                .stream()
                .distinct()
                .filter(role -> Arrays
                        .stream(ERole.values())
                        .anyMatch(e -> e.name().equals(role.getName().name())))
                .toList();
        if (roles.isEmpty()) {
            throw new EntityNotFoundException("Roles not found!");
        }

        if (signUpRequest.roles().size() != roles.size()) {
            throw new RoleNotFoundException("One of the roles: %s in the request is incorrect".formatted(strRoles));
        }

        var rolesSave = roleMapper.toEntity(roles);

        User saveUser = User.builder()
                .username(signUpRequest.username())
                .password(encoder.encode(signUpRequest.password()))
                .lastName(signUpRequest.username())
                .firstName(signUpRequest.username())
                .roles(rolesSave)
                .email(signUpRequest.email()).build();
        saveUser.setCreated(new Date());
        saveUser.setStatus(Status.ACTIVE);

        return userRepository.save(saveUser);

    }
}
