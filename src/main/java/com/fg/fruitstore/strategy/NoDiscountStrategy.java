package com.fg.fruitstore.strategy;

public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(double originalTotal) {
        return originalTotal;
    }
}
