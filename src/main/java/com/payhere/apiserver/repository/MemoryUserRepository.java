package com.payhere.apiserver.repository;

import com.payhere.apiserver.domain.User;

import java.util.*;

public class MemoryUserRepository implements UserRepository{
    private static Map<Long, User> store = new HashMap<>();
    private static long sequence = 1L;

    @Override
    public User save(User user) {
        if(store.get(user.getId()) == null){
            user.setId(++sequence);
        }
        store.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return store.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}


