package com.example.dgpayproject.repositories;
import com.example.dgpayproject.base.BaseRepository;
import com.example.dgpayproject.models.entities.UserEntity;



public interface UserRepository extends BaseRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);

}
