package com.inditex.tariff_manager;

import static org.assertj.core.api.Assertions.assertThat;

import com.inditex.tariff_manager.config.Profiles;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(Profiles.TEST)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TariffManagerApplicationTests {

    @Test
    void contextLoads(ApplicationContext context) {
        // Sanity check test that will fail if the application context cannot start
        assertThat(context).isNotNull();
    }

}
