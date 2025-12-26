package com.fg.fruitstore.cart;

import com.fg.fruitstore.domain.Fruit;
import com.fg.fruitstore.strategy.DiscountStrategy;

import java.util.EnumMap;
import java.util.Map;

/**
 * 购物车类，负责商品结算
 */
public class ShoppingCart {

    private final Map<Fruit, Integer> items = new EnumMap<>(Fruit.class);

    /**
     * 添加商品
     *
     * @param fruit 水果类型
     * @param weightKg 购买斤数（>=0）
     */
    public void addFruit(Fruit fruit, int weightKg) {
        if (weightKg < 0) {
            throw new IllegalArgumentException("克重必须大于等于0");
        }
        items.put(fruit, weightKg);
    }

    /**
     * 计算原始总价
     */
    public double calculateOriginalTotal() {
        return items.entrySet().stream()
                .mapToDouble(e -> e.getKey().getPricePerKg() * e.getValue())
                .sum();
    }

    /**
     * 应用促销策略
     */
    public double checkout(DiscountStrategy discountStrategy) {
        double originalTotal = calculateOriginalTotal();
        return discountStrategy.applyDiscount(originalTotal);
    }

    public int getFruitWeight(Fruit fruit) {
        return items.getOrDefault(fruit, 0);
    }
}
