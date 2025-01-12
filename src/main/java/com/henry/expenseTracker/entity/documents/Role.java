package com.henry.expenseTracker.entity.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class Role {

    @Field(name="granted_authorities")
    private Set<String> grantedAuthorities;
}
