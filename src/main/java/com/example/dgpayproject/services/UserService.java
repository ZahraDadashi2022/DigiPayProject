package com.example.dgpayproject.services;

import com.example.dgpayproject.models.entities.UserEntity;
import java.util.List;

public interface UserService {

    void saveUser(UserEntity userEntity);
    UserEntity getUser(String username);
}
