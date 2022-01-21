package com.payhere.apiserver.service;

import com.payhere.apiserver.domain.Expenditure;
import com.payhere.apiserver.domain.UserExpenditure;
import com.payhere.apiserver.repository.ExpenditureRepository;
import com.payhere.apiserver.repository.SpringDataJpaExpenditureRepository;
import com.payhere.apiserver.repository.UserExpenditureRepository;
import com.payhere.apiserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserExpenditureService {
    private UserExpenditureRepository userExpenditureRepository;
    private ExpenditureRepository expenditureRepository;

    @Autowired
    public UserExpenditureService(UserExpenditureRepository userExpenditureRepository, ExpenditureRepository expenditureRepository) {
        this.userExpenditureRepository = userExpenditureRepository;
        this.expenditureRepository = expenditureRepository;
    }

    // todo: 모든 userId 파라미터 받는 함수에서 user존재여부를 userRepository에서 검사해야할듯..

    public UserExpenditure saveExpenditure(Long userId, Expenditure expenditure) {
        Expenditure newExpenditure = expenditureRepository.save(expenditure);

        if(newExpenditure == null) {
            return null;
        }

        UserExpenditure userExpenditure  = new UserExpenditure();
        userExpenditure.setUserId(userId);
        userExpenditure.setExpenditureId(newExpenditure.getId());
        userExpenditure.setIsDeleted(false);

        UserExpenditure addedUserExpenditure = userExpenditureRepository.save(userExpenditure);

        return addedUserExpenditure;
    }

    public Expenditure updateUserExpenditure(Long userId, Expenditure expenditure) {
        Optional<UserExpenditure> existUserExpenditure = userExpenditureRepository.findByUserIdAndExpenditureId(userId,expenditure.getId());
        if(existUserExpenditure.isEmpty())
            return null;

        Long expenditureId = expenditure.getId();
        Expenditure existExpenditure = expenditureRepository.findById(expenditureId).get();

        if(existExpenditure == null) {
            return null;
        }

        existExpenditure.setAmounts(expenditure.getAmounts());
        existExpenditure.setMemo(expenditure.getMemo());
        existExpenditure.setDatetime(expenditure.getDatetime());
        expenditureRepository.save(existExpenditure);

        return existExpenditure;
    }

    public void deleteExpenditure(Long userId, Long expenditureId) {
        UserExpenditure existUserExpenditure = userExpenditureRepository.findByUserIdAndExpenditureId(userId, expenditureId).get();
        if(existUserExpenditure == null)
            return;

        existUserExpenditure.setIsDeleted(true);
        userExpenditureRepository.save(existUserExpenditure);
    }

    public void recoverExpenditure(Long userId, Long expenditureId) {
        UserExpenditure existUserExpenditure = userExpenditureRepository.findByUserIdAndExpenditureId(userId,expenditureId).get();
        if(existUserExpenditure == null)
            return;

        existUserExpenditure.setIsDeleted(false);
        userExpenditureRepository.save(existUserExpenditure);
    }

    public List<Expenditure> getExpenditureListByUserId(Long userId, boolean isDeleted) {
        List<UserExpenditure> userExpenditureList = userExpenditureRepository.findAllByUserIdAndIsDeleted(userId, isDeleted);

        List<Expenditure> expenditureList = new ArrayList<>();

        userExpenditureList.stream().forEach(userExpenditure -> {
            Long expenditureId = userExpenditure.getExpenditureId();
            Expenditure expenditure = expenditureRepository.findById(expenditureId).get();
            if(expenditure != null) {
                expenditureList.add(expenditure);
            }
        });

        return expenditureList;
    }

}
