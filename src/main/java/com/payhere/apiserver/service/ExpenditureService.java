package com.payhere.apiserver.service;

import com.payhere.apiserver.domain.Expenditure;
import com.payhere.apiserver.repository.ExpenditureRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ExpenditureService {
    private final ExpenditureRepository expenditureRepository;

    @Autowired
    public ExpenditureService(ExpenditureRepository expenditureRepository) {
        this.expenditureRepository = expenditureRepository;
    }

    public Expenditure save(Expenditure expenditure) {
        return expenditureRepository.save(expenditure);
    }

    public Optional<Expenditure> findById(Long id) {
        return expenditureRepository.findById(id);
    }

    public boolean update(Long id, Expenditure expenditure) {
        if(expenditureRepository.findById(id).isEmpty()) {
            return false;
        }

        expenditure.setId(id);
        expenditureRepository.save(expenditure);
        return true;
    }

    public List<Expenditure> findAll() {
        return expenditureRepository.findAll();
    }
}
