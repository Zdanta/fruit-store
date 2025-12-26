package com.fg.fruitstore;

import com.fg.fruitstore.cart.ShoppingCart;
import com.fg.fruitstore.domain.Fruit;
import com.fg.fruitstore.strategy.FullReductionStrategy;
import com.fg.fruitstore.strategy.NoDiscountStrategy;
import com.fg.fruitstore.strategy.StrawberryDiscountStrategy;

public class ShoppingCartTest {

    public static void main(String[] args) {

        // 顾客 A：苹果 + 草莓
        ShoppingCart cartA = new ShoppingCart();
        cartA.addFruit(Fruit.APPLE, 2);
        cartA.addFruit(Fruit.STRAWBERRY, 1);
        assertEqual(cartA.checkout(new NoDiscountStrategy()), 29.0, "Customer A");

        // 顾客 B：苹果 + 草莓 + 芒果
        ShoppingCart cartB = new ShoppingCart();
        cartB.addFruit(Fruit.APPLE, 2);
        cartB.addFruit(Fruit.STRAWBERRY, 1);
        cartB.addFruit(Fruit.MANGO, 1);
        assertEqual(cartB.checkout(new NoDiscountStrategy()), 49.0, "Customer B");

        // 顾客 C：草莓 8 折
        ShoppingCart cartC = new ShoppingCart();
        cartC.addFruit(Fruit.APPLE, 2);
        cartC.addFruit(Fruit.STRAWBERRY, 1);
        double resultC = cartC.checkout(
                new StrawberryDiscountStrategy(cartC.getFruitWeight(Fruit.STRAWBERRY)));
        assertEqual(resultC, 26.4, "Customer C");

        // 顾客 D：满 100 减 10（边界值）
        ShoppingCart cartD1 = new ShoppingCart();
        cartD1.addFruit(Fruit.MANGO, 5); // 100
        assertEqual(cartD1.checkout(new FullReductionStrategy()), 90.0, "Exactly 100");

        ShoppingCart cartD2 = new ShoppingCart();
        cartD2.addFruit(Fruit.MANGO, 4); // 80
        assertEqual(cartD2.checkout(new FullReductionStrategy()), 80.0, "Below 100");

        ShoppingCart cartD3 = new ShoppingCart();
        cartD3.addFruit(Fruit.MANGO, 6); // 120
        assertEqual(cartD3.checkout(new FullReductionStrategy()), 110.0, "Above 100");

        System.out.println("success");
    }

    private static void assertEqual(double actual, double expected, String message) {
        if (Math.abs(actual - expected) > 0.0001) {
            throw new AssertionError(message + " failed: expected "
                    + expected + ", actual " + actual);
        }
    }
}
