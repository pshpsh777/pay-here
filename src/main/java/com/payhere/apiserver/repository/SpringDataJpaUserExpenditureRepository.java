package com.payhere.apiserver.repository;

import com.payhere.apiserver.domain.UserExpenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaUserExpenditureRepository extends JpaRepository<UserExpenditure, Long>, UserExpenditureRepository {

}
