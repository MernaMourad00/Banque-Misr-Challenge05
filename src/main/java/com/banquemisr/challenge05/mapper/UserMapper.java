package com.banquemisr.challenge05.mapper;

import com.banquemisr.challenge05.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    Users toEntity(Long id);
}