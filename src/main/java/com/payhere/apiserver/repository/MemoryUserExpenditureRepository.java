package com.payhere.apiserver.repository;

import com.payhere.apiserver.domain.Expenditure;
import com.payhere.apiserver.domain.UserExpenditure;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryUserExpenditureRepository implements UserExpenditureRepository{
    private static Map<Long, UserExpenditure> store = new HashMap<>();
    private static long sequence = 1L;

    @Override
    public UserExpenditure save(UserExpenditure userExpenditure) {
        if(store.get(userExpenditure.getId()) == null){
            userExpenditure.setId(++sequence);
        }
        store.put(userExpenditure.getId(), userExpenditure);
        return userExpenditure;
    }

    @Override
    public Optional<UserExpenditure> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<UserExpenditure> findAllByUserIdAndIsDeleted(Long userId, boolean isDeleted) {
        return store.values().stream()
                .filter(userExpenditure -> userExpenditure.getUserId().equals(userId))
                .filter(userExpenditure -> userExpenditure.getIsDeleted().equals(isDeleted))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public Optional<UserExpenditure> findByUserIdAndExpenditureId(Long userId, Long expenditureId) {
        return store.values().stream()
                .filter(userExpenditure -> userExpenditure.getUserId().equals(userId))
                .filter(userExpenditure -> userExpenditure.getExpenditureId().equals(expenditureId))
                .findAny();
    }

    public void clear() {
        store.clear();
    }
}
