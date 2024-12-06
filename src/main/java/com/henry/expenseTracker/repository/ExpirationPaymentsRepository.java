package com.henry.expenseTracker.repository;

import com.henry.expenseTracker.entity.ExpirationPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface ExpirationPaymentsRepository extends JpaRepository<ExpirationPayments, Long> {

    List<ExpirationPayments> findByExpirationId(Long expiration_id);

    List<ExpirationPayments> findByPaymentId(Long payment_id);
}
