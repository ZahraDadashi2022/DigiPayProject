package com.example.dgpayproject.services;

import com.example.dgpayproject.base.BaseService;
import com.example.dgpayproject.models.dtos.WalletTransfer;
import com.example.dgpayproject.models.entities.WalletEntity;

import java.util.List;

public interface WalletService extends BaseService<WalletEntity, Long> {
    List<WalletEntity> findByUserEntity();
    WalletEntity findByName(String name);
    WalletEntity findById(Long id);

    }
