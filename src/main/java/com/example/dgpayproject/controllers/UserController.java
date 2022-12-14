package com.example.dgpayproject.controllers;

import com.example.dgpayproject.mappers.UserConverter;
import com.example.dgpayproject.models.dtos.UserDto;
import com.example.dgpayproject.services.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final UserConverter userConverter;

    @PostMapping
    public void saveUser(@Valid @RequestBody UserDto userDto) {
        LOGGER.info("saving user is done successfully");
        userService.saveUser(userConverter.convertDtoToT(userDto));
    }
}
