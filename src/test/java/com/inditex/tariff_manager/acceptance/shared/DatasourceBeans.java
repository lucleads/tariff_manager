package com.inditex.tariff_manager.acceptance.shared;

import com.inditex.tariff_manager.shared.persistence.h2.repositories.PriceEntityRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public abstract class DatasourceBeans {

    @MockBean
    private PriceEntityRepository priceEntityRepository;

}