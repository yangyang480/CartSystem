package com.example;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

public class CartSystem extends TheSystem {
    //to implement the logic related only to the cart.
    CartSystem() throws FileNotFoundException {
        super();
    }
    @Override
    public void display() {
        System.out.println("Cart:");
        System.out.printf("%-20s %-20s %-10s %-10s %-10s%n", "Name", "Description", "Price", "Quantity", "Sub Total");
        AtomicReference<Double> subTotal = new AtomicReference<>(0.0);
        getItemCollection().forEach((k, v) -> {
            Double itemTotal = v.getItemPrice() * v.getQuantity();
            System.out.format("%-20s %-20s %-10.2f %-10d %-10.2f%n", v.getItemName(), v.getItemDesc(), v.getItemPrice(), v.getQuantity(), itemTotal);
            subTotal.set(subTotal.get() + itemTotal);
        });

        System.out.format("%-20s %-20.2f%n", "Pre-tax Total", subTotal.get());
        System.out.format("%-20s %-20.2f%n", "Tax", subTotal.get() * 0.05);
        System.out.format("%-20s %-20.2f%n", "Total", subTotal.get() + subTotal.get() * 0.05);
    }
}
