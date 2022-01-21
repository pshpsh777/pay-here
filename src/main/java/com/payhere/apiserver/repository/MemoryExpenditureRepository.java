package com.payhere.apiserver.repository;

import com.payhere.apiserver.domain.Expenditure;

import java.util.*;

public class MemoryExpenditureRepository implements ExpenditureRepository{

    private static Map<Long, Expenditure> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Expenditure save(Expenditure expenditure) {
        if(store.get(expenditure.getId()) == null){
            expenditure.setId(++sequence);
        }

        store.put(expenditure.getId(), expenditure);
        return expenditure;
    }

    @Override
    public Optional<Expenditure> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public void deleteById(Long id) {
        store.remove(id);
    }

    @Override
    public List<Expenditure> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clear() {
        store.clear();
    }
}
