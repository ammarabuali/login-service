package org.example.loginapi.mapper;

import org.example.loginapi.entity.UserEntity;
import org.example.loginapi.entity.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
 
@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDto toDto(UserEntity userEntity);
    UserEntity toEntity(UserDto userDto);
}