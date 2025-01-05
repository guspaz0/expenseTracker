package com.henry.expenseTracker.repository;

import com.henry.expenseTracker.entity.User;
import com.henry.expenseTracker.entity.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User sampleUser;

    @BeforeEach
    void setUp(){
        sampleUser = User.builder()
                .name("jhon Doe")
                .email("jhon.doe@gmail.com")
                .password("1234")
                .userRole(UserRole.ROLE_ADMIN)
                .currency("ARS")
                .country("AR")
                .build();
    }

    @DisplayName("Save user in Database")
    @Test
    void testGuardarUsuario(){
        //given

        //when
        User userSaved = userRepository.save(sampleUser);

        //then
        assertThat(userSaved).isNotNull();
        assertThat(userSaved.getId()).isGreaterThan(0);
    }

    @DisplayName("List all user from Database")
    @Test
    void testListAllUser(){
        //given
        User sampleUser2 = User.builder()
                .name("Juan Carlos")
                .email("juan.carlos@gmail.com")
                .password("1234")
                .userRole(UserRole.ROLE_ADMIN)
                .currency("ARS")
                .country("AR")
                .build();
        userRepository.save(sampleUser2);
        userRepository.save(sampleUser);
        //when
        List<User> userList = userRepository.findAll();

        //then
        assertThat(userList).isNotNull();
        assertThat(userList.size()).isGreaterThanOrEqualTo(2);
    }

    @DisplayName("find user By Id in Database")
    @Test
    void testFindByIdUser(){
        userRepository.save(sampleUser);

        User userDb = userRepository.findById(sampleUser.getId()).get();

        assertThat(userDb).isNotNull();
    }

    @DisplayName("update user in db")
    @Test
    void testUpdateUser(){
        userRepository.save(sampleUser);

        User userDb = userRepository.findById(sampleUser.getId()).get();
        userDb.setEmail("actualzar@email.com");
        userDb.setName("Jaime cruz");
        User userUpdated = userRepository.save(userDb);

        assertThat(userUpdated).isNotNull();
        assertThat(userUpdated.getEmail()).isEqualTo("actualzar@email.com");
        assertThat(userUpdated.getName()).isEqualTo("Jaime cruz");
    }


    @DisplayName("delete user in db")
    @Test
    void testDeleteUser(){
        userRepository.save(sampleUser);

        userRepository.deleteById(sampleUser.getId());
        Optional<User> userDeleted = userRepository.findById(sampleUser.getId());

        assertThat(userDeleted).isEmpty();
    }
}
