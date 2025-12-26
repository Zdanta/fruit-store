package com.fg.fruitstore;

import com.fg.fruitstore.cart.ShoppingCart;
import com.fg.fruitstore.domain.Fruit;
import com.fg.fruitstore.strategy.FullReductionStrategy;
import com.fg.fruitstore.strategy.NoDiscountStrategy;
import com.fg.fruitstore.strategy.StrawberryDiscountStrategy;

import java.math.BigDecimal;

public class ShoppingCartTest {

    public static void main(String[] args) {

        // Customer A
        ShoppingCart cartA = new ShoppingCart();
        cartA.addFruit(Fruit.APPLE, new BigDecimal("2"));
        cartA.addFruit(Fruit.STRAWBERRY, new BigDecimal("1"));
        assertEqual(cartA.checkout(new NoDiscountStrategy()), new BigDecimal("29.00"), "Customer A");

        // Customer B
        ShoppingCart cartB = new ShoppingCart();
        cartB.addFruit(Fruit.APPLE, new BigDecimal("2"));
        cartB.addFruit(Fruit.STRAWBERRY, new BigDecimal("1"));
        cartB.addFruit(Fruit.MANGO, new BigDecimal("1"));
        assertEqual(cartB.checkout(new NoDiscountStrategy()), new BigDecimal("49.00"), "Customer B");

        // Customer C
        ShoppingCart cartC = new ShoppingCart();
        cartC.addFruit(Fruit.APPLE, new BigDecimal("2"));
        cartC.addFruit(Fruit.STRAWBERRY, new BigDecimal("1"));
        BigDecimal resultC = cartC.checkout(
                new StrawberryDiscountStrategy(cartC.getFruitWeight(Fruit.STRAWBERRY))
        );
        assertEqual(resultC, new BigDecimal("26.40"), "Customer C");

        // Customer D: full reduction
        ShoppingCart cartD1 = new ShoppingCart();
        cartD1.addFruit(Fruit.MANGO, new BigDecimal("5")); // 100
        assertEqual(cartD1.checkout(new FullReductionStrategy()), new BigDecimal("90.00"), "Exactly 100");

        ShoppingCart cartD2 = new ShoppingCart();
        cartD2.addFruit(Fruit.MANGO, new BigDecimal("4")); // 80
        assertEqual(cartD2.checkout(new FullReductionStrategy()), new BigDecimal("80.00"), "Below 100");

        ShoppingCart cartD3 = new ShoppingCart();
        cartD3.addFruit(Fruit.MANGO, new BigDecimal("6")); // 120
        assertEqual(cartD3.checkout(new FullReductionStrategy()), new BigDecimal("110.00"), "Above 100");

        System.out.println("All test cases passed!");
    }

    private static void assertEqual(BigDecimal actual, BigDecimal expected, String message) {
        if (actual.compareTo(expected) != 0) {
            throw new AssertionError(message + " failed: expected "
                    + expected + ", actual " + actual);
        }
    }
}
