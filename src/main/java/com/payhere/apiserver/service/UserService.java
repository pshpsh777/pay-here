package com.payhere.apiserver.service;

import com.payhere.apiserver.domain.User;
import com.payhere.apiserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public long join(User user) {
        isDuplicatedUser(user); // 중복회원 검증
        User result = userRepository.save(user);
        return result.getId();
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findUser(long id) {
        return userRepository.findById(id);
    }

    public User updateUser(long id, User user) {
        Optional<User> result = userRepository.findById(id);

        if(result.isEmpty())
            return null;

        result.get().setName(user.getName());
        result.get().setPassword(user.getPassword());
        result.get().setEmail(user.getEmail());

        return userRepository.save(result.get());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private void isDuplicatedUser(User user) {
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원 " + user.getEmail());
                });
    }
}