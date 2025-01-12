package com.henry.expenseTracker.repository.jpa;

import com.henry.expenseTracker.entity.jpa.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByUserId(Long user_id);
}
