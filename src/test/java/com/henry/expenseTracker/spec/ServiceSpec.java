package com.henry.expenseTracker.spec;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.TestPropertySources;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestPropertySources({
        @TestPropertySource(value = "configs/api_countries.properties"),
        @TestPropertySource(value = "configs/api_currency.properties"),
        @TestPropertySource(value = "configs/client_security.properties"),
        @TestPropertySource(value = "configs/redis.properties")
})
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration
public abstract class ServiceSpec {
//    static {
//        System.setProperty("POSTGRES_DB", "${POSTGRES_DB}");
//        System.setProperty("POSTGRES_USER", "${POSTGRES_USER}");
//        System.setProperty("POSTGRES_PASSWORD", "${POSTGRES_PASSWORD}");
//    }
}
