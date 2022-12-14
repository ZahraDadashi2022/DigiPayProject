package com.example.dgpayproject.services.impl;

import com.example.dgpayproject.exceptionhandlers.GlobalException;
import com.example.dgpayproject.models.dtos.WalletTransfer;
import com.example.dgpayproject.models.entities.TransactionEntity;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.models.entities.WalletEntity;
import com.example.dgpayproject.models.enums.TransactionStatus;
import com.example.dgpayproject.models.enums.WalletStatus;
import com.example.dgpayproject.repositories.TransactionRepository;
import com.example.dgpayproject.services.TransactionService;
import com.example.dgpayproject.services.UserService;
import com.example.dgpayproject.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    public static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);

    private final TransactionRepository transactionRepository;
    private final WalletService walletService;
    private final UserService userService;

    @Transactional
    @Override
    public void save(TransactionEntity transactionEntity) {
        LOGGER.info("saving new transaction in database:{}", transactionEntity);
        UserEntity user = getUserFromSecurityContext();
        String walletName = transactionEntity.getWalletEntity().getName();
        WalletEntity wallet = walletService.findByName(walletName);
        transactionEntity.setUserEntity(user);
        transactionEntity.setWalletEntity(wallet);
        transactionRepository.save(transactionEntity);
        deposit(transactionEntity);
    }

    @Override
    public void deposit(TransactionEntity transaction) {
        LOGGER.info("deposit wallet in database:{}", transaction.getWalletEntity());
        Double amount = transaction.getAmount();
        WalletEntity wallet = transaction.getWalletEntity();
        transaction.setTransactionStatus(TransactionStatus.DEPOSIT);
        transactionRepository.save(transaction);
        if (wallet.getWalletStatus() == WalletStatus.ENABLED) {
            wallet.setBalance(wallet.getBalance() + amount);
            walletService.save(wallet);
        } else throw new GlobalException("input.is.not.true");
    }

    @Transactional
    @Override
    public List<TransactionEntity> findByWalletEntity(Long id) {
        LOGGER.info("fetching all transactions of desired wallet for the user logged in");
        if (id == null) throw new GlobalException("id.does.not.exist");
        WalletEntity wallet = walletService.findById(id);
        return transactionRepository.findByWalletEntity(wallet);
    }

    @Transactional
    @Override
    public void withdraw(TransactionEntity transaction) {
        LOGGER.info("withdraw wallet in database:{}", transaction.getWalletEntity());
        WalletEntity walletEntity = transaction.getWalletEntity();
        if (walletEntity.getName() == null)
            throw new GlobalException("wallet.does.not.exist");
        else {
            Double amount = transaction.getAmount();
            UserEntity user = getUserFromSecurityContext();
            transaction.setUserEntity(user);
            String walletName = walletEntity.getName();
            WalletEntity wallet = walletService.findByName(walletName);
            Integer version = wallet.getVersion();
            Long id = wallet.getId();
            Double balance = wallet.getBalance();
            Double remainedBalance = balance - amount;
            if (wallet.getWalletStatus().equals(WalletStatus.ENABLED)
                   && balance >= 0 && transaction.getAmount() <= balance) {
                walletEntity.setBalance(remainedBalance);
                walletEntity.setId(id);
                walletEntity.setVersion(version);
                walletEntity.setWalletStatus(WalletStatus.ENABLED);
                walletService.save(walletEntity);
                transaction.setAmount(amount);
                transaction.setTransactionStatus(TransactionStatus.WITHDRAW);
                transaction.setWalletEntity(wallet);
                transactionRepository.save(transaction);
                LOGGER.info(" wallet balance is decreased in database:{}", transaction.getWalletEntity());

            } else {
                walletEntity.setBalance(0.0);
                throw new GlobalException("balance.in.wallet.is.zero.You.are.not.allowed.to.withdraw");
            }
        }
    }
    public UserEntity getUserFromSecurityContext() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUser(username);
    }


    @Transactional
    @Override
    public void transferWalletToWallet(WalletTransfer walletTransfer) {
        LOGGER.info("transfer wallet to wallet:{}", walletTransfer);
        UserEntity user = getUserFromSecurityContext();
        WalletEntity sourceWallet = walletService.findByName(walletTransfer.getSourceWallet());
        WalletEntity targetWallet = walletService.findByName(walletTransfer.getTargetWallet());
        if (sourceWallet.getUserEntity().equals(user) && targetWallet.getUserEntity().equals(user)) {
            TransactionEntity firstTransaction = new TransactionEntity();
            firstTransaction.setWalletEntity(sourceWallet);
            firstTransaction.setTransactionStatus(TransactionStatus.WITHDRAW);
            firstTransaction.setDescription("send from source wallet: " + walletTransfer.getSourceWallet());
            firstTransaction.setAmount(walletTransfer.getAmount());
            withdraw(firstTransaction);
            TransactionEntity secondTransaction = new TransactionEntity();
            secondTransaction.setWalletEntity(targetWallet);
            secondTransaction.setTransactionStatus(TransactionStatus.DEPOSIT);
            secondTransaction.setDescription("target wallet receives" + walletTransfer.getTargetWallet());
            secondTransaction.setAmount(walletTransfer.getAmount());
            deposit(secondTransaction);
        }
    }
}
