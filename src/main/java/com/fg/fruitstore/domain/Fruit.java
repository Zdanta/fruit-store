package com.fg.fruitstore.domain;

import com.fg.fruitstore.constants.Constants;

public enum Fruit {

    APPLE(Constants.APPLE_PRICE),
    STRAWBERRY(Constants.STRAWBERRY_PRICE),
    MANGO(Constants.MANGO_PRICE);

    private final double pricePerKg;

    Fruit(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }
}
