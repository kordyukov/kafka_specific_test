package com.example.specifications.mapper;

import com.example.specifications.model.dto.RoleDto;
import com.example.specifications.model.entity.Role;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    List<Role> toEntity(List<RoleDto> roleName);
}
