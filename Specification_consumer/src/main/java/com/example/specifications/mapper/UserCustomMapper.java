package com.example.specifications.mapper;

import com.example.specifications.model.dto.UserCustomDto;
import com.example.specifications.model.entity.UserCustom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserCustomMapper {
    UserCustom toEntity(UserCustomDto userCustomDto);
}
