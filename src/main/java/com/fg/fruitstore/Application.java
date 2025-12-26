package com.fg.fruitstore;

import com.fg.fruitstore.cart.ShoppingCart;
import com.fg.fruitstore.domain.Fruit;
import com.fg.fruitstore.strategy.NoDiscountStrategy;
import com.fg.fruitstore.strategy.StrawberryDiscountStrategy;

import java.math.BigDecimal;

public class Application {

    public static void main(String[] args) {

        ShoppingCart cart = new ShoppingCart();
        cart.addFruit(Fruit.APPLE, new BigDecimal("2"));
        cart.addFruit(Fruit.STRAWBERRY, new BigDecimal("3"));

        BigDecimal totalNoDiscount = cart.checkout(new NoDiscountStrategy());
        System.out.println("无折扣: " + totalNoDiscount);

        BigDecimal totalWithStrawberryDiscount = cart.checkout(
                new StrawberryDiscountStrategy(cart.getFruitWeight(Fruit.STRAWBERRY))
        );
        System.out.println("草莓折扣: " + totalWithStrawberryDiscount);
    }
}
