package com.banquemisr.challenge05.mapper;

import com.banquemisr.challenge05.entity.Task;
import com.banquemisr.challenge05.model.dto.TaskRequestDto;
import com.banquemisr.challenge05.model.dto.TaskResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface TaskMapper {

    @Mapping(source = "userId", target = "users.id")
    Task toEntity(TaskRequestDto dto);

    @Mapping(source = "users.email", target = "userEmail")
    TaskResponseDto toDto(Task task);

}