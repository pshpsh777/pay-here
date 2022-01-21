package com.payhere.apiserver.Service;

import com.payhere.apiserver.domain.User;
import com.payhere.apiserver.repository.MemoryUserRepository;
import com.payhere.apiserver.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceTest {
    private UserService userService;
    private MemoryUserRepository userRepository;

    @BeforeEach
    void BeforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    void AfterEach() {
        userRepository.clear();
    }

    @Test
    @DisplayName(value = "회원가입")
    void join() {
        // given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        // when
        long newId = userService.join(user);

        // then
        User getUser = userService.findUser(newId).get();
        Assertions.assertThat(user).isEqualTo(getUser);
    }

    @Test
    @DisplayName(value = "중복회원 검사 (이메일)")
    void duplicatedJoin() {
        //given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        //when
        long newId1 = userService.join(user);

        //then
        assertThrows(IllegalStateException.class, ()->userService.join(user));
    }

    @Test
    @DisplayName(value = "회원정보 업데이트")
    void updateUser() {
        //given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        User user1 = new User();
        user1.setName("soo");
        user1.setPassword("5555");
        user1.setEmail("smile@gate.com");

        //when
        long newId1 = userService.join(user);
        User updatedUser = userService.updateUser(newId1, user1);

        //then
        Assertions.assertThat(updatedUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
    @DisplayName(value = "회원삭제")
    void deleteUser() {
        //given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaa@aaa.com");

        //when
        long newId1 = userService.join(user);
        userService.deleteUser(newId1);
        List<User> users = userService.findUsers();

        //then
        Assertions.assertThat(users.size()).isEqualTo(0);
    }
}