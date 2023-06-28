package com.example.specifications.service;

import com.example.specifications.model.entity.User;
import com.example.specifications.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository
                .findByUsername(username).orElseThrow(() -> new UsernameNotFoundException
                        ("Employee Not Found with username: "
                                + username));

        return UserDetailsImpl.build(user);
    }
}
