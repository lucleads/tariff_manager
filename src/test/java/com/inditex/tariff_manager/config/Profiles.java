package com.inditex.tariff_manager.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Profiles {

    public static final String ACCEPTANCE = "acceptance";
    public static final String NO_ACCEPTANCE = "!" + ACCEPTANCE;
    public static final String TEST = "test";

}
