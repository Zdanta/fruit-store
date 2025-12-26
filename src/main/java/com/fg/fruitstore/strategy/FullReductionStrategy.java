package com.fg.fruitstore.strategy;

import com.fg.fruitstore.constants.Constants;

public class FullReductionStrategy implements DiscountStrategy {

    @Override
    public double applyDiscount(double originalTotal) {
        if (originalTotal >= Constants.FULL_REDUCTION_THRESHOLD) {
            return originalTotal - Constants.FULL_REDUCTION_AMOUNT;
        }
        return originalTotal;
    }
}
