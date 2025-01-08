package com.henry.expenseTracker.repository.jpa;

import com.henry.expenseTracker.entity.jpa.Expiration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpirationRepository extends JpaRepository<Expiration, Long> {

    //@Query
    List<Expiration> findByExpenseId(Long id);
}
