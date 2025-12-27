package com.fg.fruitstore;

import com.fg.fruitstore.cart.ShoppingCart;
import com.fg.fruitstore.domain.Fruit;
import com.fg.fruitstore.strategy.FullReductionStrategy;
import com.fg.fruitstore.strategy.NoDiscountStrategy;
import com.fg.fruitstore.strategy.StrawberryDiscountStrategy;

import java.math.BigDecimal;

public class ShoppingCartTest {

    public static void main(String[] args) {

        // 场景一：顾客购买苹果 + 草莓（无促销）
        ShoppingCart cartA = new ShoppingCart();
        cartA.addFruit(Fruit.APPLE, new BigDecimal("2"));
        cartA.addFruit(Fruit.STRAWBERRY, new BigDecimal("1"));
        assertEqual(
                cartA.checkout(new NoDiscountStrategy()),
                new BigDecimal("29.00"),
                "场景一：无促销计算"
        );

        // 场景二：顾客购买苹果 + 草莓 + 芒果（无促销）
        ShoppingCart cartB = new ShoppingCart();
        cartB.addFruit(Fruit.APPLE, new BigDecimal("2"));
        cartB.addFruit(Fruit.STRAWBERRY, new BigDecimal("1"));
        cartB.addFruit(Fruit.MANGO, new BigDecimal("1"));
        assertEqual(
                cartB.checkout(new NoDiscountStrategy()),
                new BigDecimal("49.00"),
                "场景二：三种水果无促销计算"
        );

        // 场景三：草莓打 8 折
        ShoppingCart cartC = new ShoppingCart();
        cartC.addFruit(Fruit.APPLE, new BigDecimal("2"));
        cartC.addFruit(Fruit.STRAWBERRY, new BigDecimal("1"));
        BigDecimal resultC = cartC.checkout(
                new StrawberryDiscountStrategy(cartC.getFruitWeight(Fruit.STRAWBERRY))
        );
        assertEqual(
                resultC,
                new BigDecimal("26.40"),
                "场景三：草莓八折促销"
        );

        // 场景四：满 100 减 10（边界值测试）
        ShoppingCart cartD1 = new ShoppingCart();
        cartD1.addFruit(Fruit.MANGO, new BigDecimal("5")); // 总价 100
        assertEqual(
                cartD1.checkout(new FullReductionStrategy()),
                new BigDecimal("90.00"),
                "场景四-1：刚好满 100 是否减 10"
        );

        ShoppingCart cartD2 = new ShoppingCart();
        cartD2.addFruit(Fruit.MANGO, new BigDecimal("4")); // 总价 80
        assertEqual(
                cartD2.checkout(new FullReductionStrategy()),
                new BigDecimal("80.00"),
                "场景四-2：未满 100 不减"
        );

        ShoppingCart cartD3 = new ShoppingCart();
        cartD3.addFruit(Fruit.MANGO, new BigDecimal("6")); // 总价 120
        assertEqual(
                cartD3.checkout(new FullReductionStrategy()),
                new BigDecimal("110.00"),
                "场景四-3：超过 100 减 10"
        );

        System.out.println("所有测试用例通过");
    }

    /**
     * 断言金额是否相等
     *
     * @param actual   实际计算结果
     * @param expected 期望结果
     * @param message  测试场景说明
     */
    private static void assertEqual(BigDecimal actual, BigDecimal expected, String message) {
        if (actual.compareTo(expected) != 0) {
            throw new AssertionError(
                    message + " 失败，期望值：" + expected + "，实际值：" + actual
            );
        }
    }
}
