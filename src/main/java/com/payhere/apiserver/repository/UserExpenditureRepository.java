package com.payhere.apiserver.repository;

import com.payhere.apiserver.domain.User;
import com.payhere.apiserver.domain.UserExpenditure;

import java.util.List;
import java.util.Optional;

public interface UserExpenditureRepository {
    UserExpenditure save(UserExpenditure userExpenditure);
    Optional<UserExpenditure> findById(Long id);
    List<UserExpenditure> findAllByUserIdAndIsDeleted(Long userId, boolean isDeleted);
    Optional<UserExpenditure> findByUserIdAndExpenditureId(Long userId, Long expenditureId);
    void deleteById(Long id);
}
