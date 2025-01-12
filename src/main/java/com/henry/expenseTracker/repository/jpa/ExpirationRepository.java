package com.henry.expenseTracker.repository.jpa;

import com.henry.expenseTracker.entity.jpa.Expiration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpirationRepository extends JpaRepository<Expiration, Long> {

    //@Query
    List<Expiration> findByExpenseId(Long id);
}
