package com.fg.fruitstore.strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {

    /**
     * 根据原始总价计算最终价格
     *
     * @param originalTotal 原始总价
     * @return 折扣后的价格
     */
    BigDecimal applyDiscount(BigDecimal originalTotal);
}
