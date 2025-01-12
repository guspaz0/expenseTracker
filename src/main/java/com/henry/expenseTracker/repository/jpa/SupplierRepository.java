package com.henry.expenseTracker.repository.jpa;

import com.henry.expenseTracker.entity.jpa.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
