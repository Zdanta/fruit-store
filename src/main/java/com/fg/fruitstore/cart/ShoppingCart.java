package com.fg.fruitstore.cart;

import com.fg.fruitstore.domain.Fruit;
import com.fg.fruitstore.strategy.DiscountStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.EnumMap;
import java.util.Map;

/**
 * 购物车类，负责商品结算
 */
public class ShoppingCart {

    private final Map<Fruit, BigDecimal> items = new EnumMap<>(Fruit.class);

    /**
     * 添加商品
     *
     * @param fruit 水果类型
     * @param weightKg 购买斤数（>=0）
     */
    public void addFruit(Fruit fruit, BigDecimal weightKg) {
        if (weightKg.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("重量必须大于等于0");
        }
        items.put(fruit, weightKg);
    }

    /**
     * 计算原始总价
     */
    public BigDecimal calculateOriginalTotal() {
        return items.entrySet().stream()
                .map(e -> e.getKey().getPricePerKg().multiply(e.getValue()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }


    /**
     * 应用促销策略
     */
    public BigDecimal checkout(DiscountStrategy discountStrategy) {
        BigDecimal originalTotal = calculateOriginalTotal();
        return discountStrategy.applyDiscount(originalTotal);
    }

    public BigDecimal getFruitWeight(Fruit fruit) {
        return items.getOrDefault(fruit, BigDecimal.ZERO);
    }
}
