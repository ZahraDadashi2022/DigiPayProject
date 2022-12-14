package com.example.dgpayproject.repositories;

import com.example.dgpayproject.base.BaseRepository;
import com.example.dgpayproject.models.entities.TransactionEntity;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.models.entities.WalletEntity;

import java.util.Date;
import java.util.List;

public interface TransactionRepository extends BaseRepository<TransactionEntity,Long> {
List<TransactionEntity> findByWalletEntity(WalletEntity walletEntity);
}
