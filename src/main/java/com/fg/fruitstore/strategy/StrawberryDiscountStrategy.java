package com.fg.fruitstore.strategy;

import com.fg.fruitstore.constants.PriceConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StrawberryDiscountStrategy implements DiscountStrategy {

    private final BigDecimal strawberryAmount; // 斤数

    public StrawberryDiscountStrategy(BigDecimal strawberryAmount) {
        if (strawberryAmount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("克重必须大于等于0");
        }
        this.strawberryAmount = strawberryAmount;
    }

    @Override
    public BigDecimal applyDiscount(BigDecimal originalTotal) {
        BigDecimal strawberryDiscount = strawberryAmount
                .multiply(PriceConstants.STRAWBERRY_PRICE)
                .multiply(BigDecimal.ONE.subtract(PriceConstants.STRAWBERRY_DISCOUNT));
        return originalTotal.subtract(strawberryDiscount)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
