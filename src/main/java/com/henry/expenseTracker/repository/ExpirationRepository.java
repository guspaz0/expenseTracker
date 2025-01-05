package com.henry.expenseTracker.repository;

import com.henry.expenseTracker.entity.Expiration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExpirationRepository extends JpaRepository<Expiration, Long> {

    //@Query
    List<Expiration> findByExpenseId(Long id);
}
