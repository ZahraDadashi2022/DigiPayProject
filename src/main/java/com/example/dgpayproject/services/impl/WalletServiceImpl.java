package com.example.dgpayproject.services.impl;

import com.example.dgpayproject.exceptionhandlers.GlobalException;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.models.entities.WalletEntity;
import com.example.dgpayproject.models.enums.WalletStatus;
import com.example.dgpayproject.repositories.WalletRepository;
import com.example.dgpayproject.services.UserService;
import com.example.dgpayproject.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    public static final Logger LOGGER = LoggerFactory.getLogger(WalletServiceImpl.class);

    private final WalletRepository walletRepository;
    private final UserService userService;

    @Transactional
    @Override
    public void save(WalletEntity walletEntity) {
        if (walletEntity.getId() == null) {
            LOGGER.info("saving new wallet:{} to db", walletEntity);
            walletEntity.setBalance(0.0);
            walletEntity.setWalletStatus(WalletStatus.ENABLED);
        }
        WalletEntity savedWalletEntity = walletRepository.save(walletEntity);
        UserEntity user = getUserFromSecurityContext();
        savedWalletEntity.setUserEntity(user);
    }

    @Transactional
    @Override
    public List<WalletEntity> findByUserEntity() {
        LOGGER.info("fetching all wallets of user logged in and sorting them by created date");
        UserEntity userEntity = getUserFromSecurityContext();
        return walletRepository.findByUserEntity(userEntity)
                .stream()
                .sorted(Comparator.comparing(WalletEntity::getCreatedDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public WalletEntity findByName(String name) {
        if (name == null) throw new GlobalException("wallet name can not be null");
        LOGGER.info("finding wallet name by wallet name in database:{}", name);
        return walletRepository.findByName(name);
    }

    @Transactional
    @Override
    public WalletEntity findById(Long id) {
        LOGGER.info("fetching wallet from database by given id");
        return walletRepository.findById(id).orElseThrow(() -> new GlobalException("id not found"));
    }

    public UserEntity getUserFromSecurityContext() {
        String username = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUser(username);
    }
}
