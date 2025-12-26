package com.fg.fruitstore.strategy;

import com.fg.fruitstore.constants.PriceConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FullReductionStrategy implements DiscountStrategy {

    @Override
    public BigDecimal applyDiscount(BigDecimal originalTotal) {
        if (originalTotal.compareTo(PriceConstants.FULL_REDUCTION_THRESHOLD) >= 0) {
            return originalTotal.subtract(PriceConstants.FULL_REDUCTION_AMOUNT)
                    .setScale(2, RoundingMode.HALF_UP);
        }
        return originalTotal.setScale(2, RoundingMode.HALF_UP);
    }
}