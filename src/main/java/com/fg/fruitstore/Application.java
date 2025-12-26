package com.fg.fruitstore;

import com.fg.fruitstore.cart.ShoppingCart;
import com.fg.fruitstore.domain.Fruit;
import com.fg.fruitstore.strategy.NoDiscountStrategy;

public class Application {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        cart.addFruit(Fruit.APPLE, 2);
        cart.addFruit(Fruit.STRAWBERRY, 3);

        double total = cart.checkout(new NoDiscountStrategy());
        System.out.println("总价格:" + total);
    }
}
