package com.example.dgpayproject.repositories;

import com.example.dgpayproject.base.BaseRepository;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.models.entities.WalletEntity;

import java.util.List;

public interface WalletRepository extends BaseRepository<WalletEntity,Long> {
    List<WalletEntity> findByUserEntity(UserEntity userEntity);
     WalletEntity findByName(String name);


}
