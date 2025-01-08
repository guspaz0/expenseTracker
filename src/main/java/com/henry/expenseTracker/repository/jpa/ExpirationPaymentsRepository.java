package com.henry.expenseTracker.repository.jpa;

import com.henry.expenseTracker.entity.jpa.ExpirationPayments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpirationPaymentsRepository extends JpaRepository<ExpirationPayments, Long> {

    List<ExpirationPayments> findByExpirationId(Long expiration_id);

    List<ExpirationPayments> findByPaymentId(Long payment_id);
}
