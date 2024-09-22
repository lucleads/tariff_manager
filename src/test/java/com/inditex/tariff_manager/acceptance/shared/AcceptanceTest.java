package com.inditex.tariff_manager.acceptance.shared;

import com.inditex.tariff_manager.config.Profiles;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({Profiles.ACCEPTANCE})
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Import({DatasourceBeans.class})
public abstract class AcceptanceTest {

}