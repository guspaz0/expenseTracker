package com.henry.expenseTracker.entity.documents;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection="app_users")
@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class AppUserDocument implements Serializable {

    @Id
    private String id;
    private String email;
    private boolean enabled;
    private String username;
    private String password;
    private Role role;
}
