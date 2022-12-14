package com.example.dgpayproject.services;

import com.example.dgpayproject.base.BaseService;
import com.example.dgpayproject.models.dtos.WalletTransfer;
import com.example.dgpayproject.models.entities.TransactionEntity;

import java.util.List;

public interface TransactionService extends BaseService<TransactionEntity, Long> {
    void withdraw(TransactionEntity transaction);
    void deposit(TransactionEntity transaction);
    List<TransactionEntity> findByWalletEntity(Long id);
    void transferWalletToWallet(WalletTransfer walletTransfer);
}
