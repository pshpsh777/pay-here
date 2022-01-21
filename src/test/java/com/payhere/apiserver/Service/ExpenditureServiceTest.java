package com.payhere.apiserver.Service;

import com.payhere.apiserver.domain.Expenditure;
import com.payhere.apiserver.repository.ExpenditureRepository;
import com.payhere.apiserver.repository.MemoryExpenditureRepository;
import com.payhere.apiserver.repository.SpringDataJpaExpenditureRepository;
import com.payhere.apiserver.service.ExpenditureService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ExpenditureServiceTest {
    MemoryExpenditureRepository expenditureRepository;
    ExpenditureService expenditureService;

    @BeforeEach
    void beforeEach() {
        expenditureRepository = new MemoryExpenditureRepository();
        expenditureService = new ExpenditureService(expenditureRepository);
    }

    @AfterEach
    void afterEach() {
        expenditureRepository.clear();
    }

    @Test
    void addExpenditureTest() {
        // given
        Expenditure expenditure = new Expenditure();
        expenditure.setAmounts(1000);
        expenditure.setMemo("dinner");
        expenditure.setDatetime(new Date());

        // when
        Expenditure newExpenditure = expenditureService.save(expenditure);

        //then
        Expenditure getExpenditure = expenditureService.findById(newExpenditure.getId()).get();
        Assertions.assertThat(expenditure).isEqualTo(getExpenditure);
    }

    @Test
    void updateExpenditureTest() {
        // given
        Expenditure expenditure = new Expenditure();
        expenditure.setAmounts(10000);
        expenditure.setMemo("dinner");
        expenditure.setDatetime(new Date());

        Expenditure expenditure2 = new Expenditure();
        expenditure2.setAmounts(9000);
        expenditure2.setMemo("dinner");
        expenditure2.setDatetime(new Date());

        // when
        Expenditure newExpenditure = expenditureService.save(expenditure);
        expenditureService.update(newExpenditure.getId(), expenditure2);

        //then
        Expenditure getExpenditure = expenditureService.findById(newExpenditure.getId()).get();
        Assertions.assertThat(getExpenditure.getAmounts()).isEqualTo(expenditure2.getAmounts());
    }

    @Test
    void getExpenditureListTest() {
        // given
        Expenditure expenditure = new Expenditure();
        expenditure.setAmounts(10000);
        expenditure.setMemo("dinner");
        expenditure.setDatetime(new Date());

        Expenditure expenditure2 = new Expenditure();
        expenditure2.setAmounts(9000);
        expenditure2.setMemo("dinner");
        expenditure2.setDatetime(new Date());

        // when
        Expenditure getExpenditure = expenditureService.save(expenditure);
        Expenditure getExpenditure2 = expenditureService.save(expenditure2);

        //then
        List<Expenditure> expenditureList = expenditureService.findAll();
        Assertions.assertThat(expenditureList.size()).isEqualTo(2);
        Assertions.assertThat(expenditureList.get(0)).isEqualTo(getExpenditure);
        Assertions.assertThat(expenditureList.get(1)).isEqualTo(getExpenditure2);
    }
}
