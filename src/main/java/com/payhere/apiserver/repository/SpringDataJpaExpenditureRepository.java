package com.payhere.apiserver.repository;

import com.payhere.apiserver.domain.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaExpenditureRepository extends JpaRepository<Expenditure, Long> , ExpenditureRepository {

}
