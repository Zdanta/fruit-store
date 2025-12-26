package com.fg.fruitstore.constants;

import java.math.BigDecimal;

public final class PriceConstants {

    private PriceConstants() {}

    public static final BigDecimal APPLE_PRICE = new BigDecimal("8.00");
    public static final BigDecimal STRAWBERRY_PRICE = new BigDecimal("13.00");
    public static final BigDecimal MANGO_PRICE = new BigDecimal("20.00");

    public static final BigDecimal STRAWBERRY_DISCOUNT = new BigDecimal("0.8");

    public static final BigDecimal FULL_REDUCTION_THRESHOLD = new BigDecimal("100.00");
    public static final BigDecimal FULL_REDUCTION_AMOUNT = new BigDecimal("10.00");
}
