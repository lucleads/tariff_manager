package com.inditex.tariff_manager.integration;

import com.inditex.tariff_manager.config.Profiles;
import lombok.NoArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles(Profiles.TEST)
@Transactional // Enable rollback after each scenario execution, cleaning the database
@NoArgsConstructor(force = true)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class IntegrationTest {

}
