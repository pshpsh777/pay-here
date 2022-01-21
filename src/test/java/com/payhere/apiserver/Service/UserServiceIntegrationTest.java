package com.payhere.apiserver.Service;

import com.payhere.apiserver.domain.User;
import com.payhere.apiserver.repository.UserRepository;
import com.payhere.apiserver.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import javax.transaction.Transactional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {
    @Autowired private UserService userService;
    @Autowired private UserRepository userRepository;

    @Test
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
    void updateUser() {
        //given
        User user = new User();
        user.setName("soo");
        user.setPassword("1234");
        user.setEmail("aaab@aaa.com");

        User user1 = new User();
        user1.setName("soo");
        user1.setPassword("5555");
        user1.setEmail("pay@here.com");

        //when
        long newId1 = userService.join(user);
        User updatedUser = userService.updateUser(newId1, user1);

        //then
        Assertions.assertThat(updatedUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(updatedUser.getEmail()).isEqualTo(user1.getEmail());
    }

    @Test
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