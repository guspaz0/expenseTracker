package com.henry.expenseTracker.repository.jpa;

import com.henry.expenseTracker.entity.jpa.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //@Query
    Optional<User> findByEmail(String email);
}
