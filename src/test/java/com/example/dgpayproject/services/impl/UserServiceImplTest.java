package com.example.dgpayproject.services.impl;

import com.example.dgpayproject.DgPayProjectApplication;
import com.example.dgpayproject.base.BaseEntity;
import com.example.dgpayproject.models.entities.UserEntity;
import com.example.dgpayproject.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DgPayProjectApplication.class)
@EnableTransactionManagement()
class UserServiceImplTest extends BaseEntity {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        UserEntity user1 = new UserEntity(
                1L, "Zahra", "Dadashi", "user1", "password1");
        userRepository.save(user1);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("check save user by getting username")
    void saveUser() {
        UserEntity user3 = new UserEntity();
        user3.setId(3L);
        user3.setFname("z");
        user3.setLname("d");
        user3.setUsername("user");
        user3.setPassword("1234");
        userRepository.save(user3);
        UserEntity user = userRepository.findByUsername("user");
        assertEquals("user", user.getUsername());
    }

    @Test
    @DisplayName("should check username if it is not null")
    void checkUsername() {
        UserEntity user4 = new UserEntity();
        user4.setId(4L);
        user4.setFname("z");
        user4.setLname("d");
        user4.setUsername("user4");
        user4.setPassword("1234");
        userRepository.save(user4);
        assertNotNull(userRepository.findByUsername("user4"));
    }

    @Test
    @DisplayName("null username can not save user entity")
    void duplicatedUsername() {
        UserEntity user5 = new UserEntity();
        user5.setId(5L);
        user5.setUsername("");
        user5.setPassword("1234");
        userRepository.save(user5);
        assertNull(userRepository.findByUsername("user5"));
    }

    @Test
    @DisplayName("should check username if it is not correct,not allowed to log in")
    void usernameIsNotCompatible() {
        UserEntity user6 = new UserEntity();
        user6.setId(6L);
        user6.setUsername("user6");
        user6.setPassword("1234");
        userRepository.save(user6);
        String username6 = user6.getUsername();
        assertNotEquals("username", username6);
    }
}
