package com.henry.expenseTracker.repository.mongo;

import com.henry.expenseTracker.entity.documents.AppUserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends MongoRepository<AppUserDocument, String> {

    Optional<AppUserDocument> findByUsername(String username);
}
