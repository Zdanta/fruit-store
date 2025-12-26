package com.fg.fruitstore.strategy;
import java.math.BigDecimal;

public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal applyDiscount(BigDecimal originalTotal) {
        return originalTotal;
    }
}
