package com.payhere.apiserver.Service;

import com.payhere.apiserver.domain.Expenditure;
import com.payhere.apiserver.domain.UserExpenditure;
import com.payhere.apiserver.repository.ExpenditureRepository;
import com.payhere.apiserver.repository.MemoryExpenditureRepository;
import com.payhere.apiserver.repository.MemoryUserExpenditureRepository;
import com.payhere.apiserver.repository.UserExpenditureRepository;
import com.payhere.apiserver.service.UserExpenditureService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UserExpenditureServiceTest {
    private MemoryUserExpenditureRepository userExpenditureRepository;
    private MemoryExpenditureRepository expenditureRepository;
    private UserExpenditureService service;
    private final Long userId = 1L;

    @BeforeEach
    void beforeEach() {
        userExpenditureRepository = new MemoryUserExpenditureRepository();
        expenditureRepository = new MemoryExpenditureRepository();
        service = new UserExpenditureService(userExpenditureRepository, expenditureRepository);
    }

    @AfterEach
    void afterEach() {
        expenditureRepository.clear();
        userExpenditureRepository.clear();
    }

    @Test
    void addUserExpenditureTest() {
        //given
        Expenditure expenditure = new Expenditure();
        expenditure.setAmounts(1000);
        expenditure.setMemo("dinner");

        //when
        UserExpenditure newUserExpenditure = service.saveExpenditure(userId, expenditure);

        //then
        Assertions.assertThat(service.getExpenditureListByUserId(userId, true).size()).isEqualTo(0);
        Assertions.assertThat(service.getExpenditureListByUserId(userId, false).size()).isEqualTo(1);
    }

    @Test
    void deleteUserExpenditureTest() {
        //given
        Expenditure expenditure = new Expenditure();
        expenditure.setAmounts(1000);
        expenditure.setMemo("dinner");

        // when
        UserExpenditure newUserExpenditure = service.saveExpenditure(userId, expenditure);
        service.deleteExpenditure(userId, expenditure.getId());

        // then
        Assertions.assertThat(service.getExpenditureListByUserId(userId, true).size()).isEqualTo(1);
        Assertions.assertThat(service.getExpenditureListByUserId(userId, false).size()).isEqualTo(0);
    }

    @Test
    void recoverUserExpenditureTest() {
        //given
        Expenditure expenditure = new Expenditure();
        expenditure.setAmounts(1000);
        expenditure.setMemo("dinner");

        // when
        UserExpenditure newUserExpenditure = service.saveExpenditure(userId, expenditure);
        service.deleteExpenditure(userId, expenditure.getId());
        service.recoverExpenditure(userId, expenditure.getId());

        // then
        Assertions.assertThat(service.getExpenditureListByUserId(userId, true).size()).isEqualTo(0);
        Assertions.assertThat(service.getExpenditureListByUserId(userId, false).size()).isEqualTo(1);
    }

    @Test
    void getUserExpenditureList() {
        //given
        Expenditure expenditure = new Expenditure();
        expenditure.setAmounts(1000);
        expenditure.setMemo("dinner");

        Expenditure expenditure2 = new Expenditure();
        expenditure2.setAmounts(8000);
        expenditure2.setMemo("lunch");

        Expenditure expenditure3 = new Expenditure();
        expenditure3.setAmounts(13000);
        expenditure3.setMemo("gift");

        UserExpenditure newUserExpenditure = service.saveExpenditure(userId, expenditure);
        UserExpenditure newUserExpenditure2 = service.saveExpenditure(userId, expenditure2);
        UserExpenditure newUserExpenditure3 = service.saveExpenditure(2L, expenditure3);

        // when
        List<Expenditure> expenditureList = service.getExpenditureListByUserId(userId, false);
        List<Expenditure> expenditureList2 = service.getExpenditureListByUserId(2L, false);

        Assertions.assertThat(expenditureList.size()).isEqualTo(2);
        Assertions.assertThat(expenditureList2.size()).isEqualTo(1);
    }

}
