package com.example.dgpayproject.mappers;

import com.example.dgpayproject.base.BaseMapper;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.models.dtos.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserConverter extends BaseMapper<UserEntity,UserDto> {

}
