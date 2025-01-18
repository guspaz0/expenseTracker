package com.henry.expenseTracker.entity.jpa;

import com.henry.expenseTracker.util.constants.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String country;
    private String currency;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name="user_role")
    private UserRole userRole;

    @Basic(fetch = FetchType.LAZY)
    private String password;

}
