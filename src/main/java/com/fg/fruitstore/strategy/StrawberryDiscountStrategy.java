package com.fg.fruitstore.strategy;

import com.fg.fruitstore.constants.Constants;

public class StrawberryDiscountStrategy implements DiscountStrategy {

    private final double strawberryAmount;

    public StrawberryDiscountStrategy(double strawberryAmount) {
        this.strawberryAmount = strawberryAmount;
    }

    @Override
    public double applyDiscount(double originalTotal) {
        double strawberryDiscount =
                strawberryAmount * Constants.STRAWBERRY_PRICE * (1 - Constants.STRAWBERRY_DISCOUNT);
        return originalTotal - strawberryDiscount;
    }
}
