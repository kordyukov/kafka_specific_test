package com.example.specifications.model.dto;

import java.util.List;

public record SignupRequest(String username,
                            String email,
                            List<RoleDto> roles,
                            String password) {

}
