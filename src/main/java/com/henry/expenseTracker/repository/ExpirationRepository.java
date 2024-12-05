package com.henry.expenseTracker.repository;

import com.henry.expenseTracker.entity.Expiration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpirationRepository extends JpaRepository<Expiration, Long> {

}
