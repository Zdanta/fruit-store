package com.fg.fruitstore.domain;

import com.fg.fruitstore.constants.PriceConstants;

import java.math.BigDecimal;

public enum Fruit {

    APPLE(PriceConstants.APPLE_PRICE),
    STRAWBERRY(PriceConstants.STRAWBERRY_PRICE),
    MANGO(PriceConstants.MANGO_PRICE);

    private final BigDecimal pricePerKg;

    Fruit(BigDecimal pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public BigDecimal getPricePerKg() {
        return pricePerKg;
    }
}
