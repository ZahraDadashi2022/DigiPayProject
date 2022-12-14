package com.example.dgpayproject.services.impl;

import com.example.dgpayproject.DgPayProjectApplication;
import com.example.dgpayproject.exceptionhandlers.GlobalException;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.models.entities.WalletEntity;
import com.example.dgpayproject.models.enums.WalletStatus;
import com.example.dgpayproject.repositories.WalletRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DgPayProjectApplication.class)
@EnableTransactionManagement()
class WalletServiceImplTest {

    @Autowired
    WalletRepository walletRepository;
    @Autowired
    WalletServiceImpl walletService;
    @Autowired
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        WalletEntity wallet1 = new WalletEntity(1L, "w1", 200.0, WalletStatus.ENABLED);
        walletRepository.save(wallet1);
        WalletEntity wallet2 = new WalletEntity(2L, "w2", 200.0, WalletStatus.DISABLED);
        walletRepository.save(wallet2);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("user", null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    @AfterEach
    void tearDown() {
        walletRepository.deleteAll();
    }

    @Test
    @DisplayName("check save wallet by getting wallet name")
    void save() {
        WalletEntity wallet3 = new WalletEntity();
        wallet3.setId(3L);
        wallet3.setName("w3");
        wallet3.setWalletStatus(WalletStatus.ENABLED);
        walletService.save(wallet3);
        WalletEntity w3 = walletService.findByName("w3");
        assertEquals("w3", w3.getName());
    }

    @Test
    @DisplayName("find wallet by id")
    void findById() {
        WalletEntity wallet5 = new WalletEntity();
        wallet5.setId(5L);
        wallet5.setName("w3");
        wallet5.setWalletStatus(WalletStatus.ENABLED);
        walletService.save(wallet5);
        Long id = wallet5.getId();
        WalletEntity wallet = walletService.findById(id);
        assertTrue(wallet.getName().equals("w3"));
    }

    @Test
    @DisplayName("id does not exist throw exception")
    void findByIdNotExist() {
        WalletEntity wallet6 = new WalletEntity();
        wallet6.setId(6L);
        wallet6.setName("w3");
        wallet6.setWalletStatus(WalletStatus.ENABLED);
        walletService.save(wallet6);
        assertThrows(GlobalException.class, () -> walletService.findById(7L));
    }

    @Test
    @DisplayName("find wallet by name")
    void findByName() {
        WalletEntity wallet7 = new WalletEntity();
        wallet7.setId(5L);
        wallet7.setName("w3");
        wallet7.setWalletStatus(WalletStatus.ENABLED);
        walletService.save(wallet7);
        String name = wallet7.getName();
        WalletEntity wallet = walletService.findByName(name);
        assertTrue(wallet.getName().equals("w3"));
    }

    @Test
    @DisplayName("user log in correctly")
    void getUserFromSecurityContext() {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken("username", null, null);
        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword("123");
        userService.saveUser(user);
        assertTrue(user.getUsername().equals(authenticationToken.getPrincipal()));

    }
}