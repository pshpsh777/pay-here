package com.payhere.apiserver.repository;

import com.payhere.apiserver.domain.Expenditure;

import java.util.List;
import java.util.Optional;

public interface ExpenditureRepository{
    Expenditure save(Expenditure expenditure);
    Optional<Expenditure> findById(Long id);
    void deleteById(Long id);
    List<Expenditure> findAll();
}
