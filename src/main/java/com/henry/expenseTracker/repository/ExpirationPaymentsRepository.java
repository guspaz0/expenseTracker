package com.henry.expenseTracker.repository;

import com.henry.expenseTracker.entity.ExpirationPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpirationPaymentsRepository extends JpaRepository<ExpirationPayments, Long> {
}
