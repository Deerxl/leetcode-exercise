package org.example.designpattern.builderpattern;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolu
 * @date 2024/9/29 12:16
 */
public class Meal {

    List<Item> items;

    public void addItems(Item... items) {
        if (this.items == null) {
            this.items = new ArrayList<>();
        }
        this.items.addAll(List.of(items));
    }

    public BigDecimal getCost() {
        BigDecimal cost = BigDecimal.ZERO;
        for (Item item : items) {
            cost = cost.add(item.getPrice());
        }
        return cost;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.println("name: " + item.getName()
                    + ", package: " + item.getPackage().getPack()
                    + ", price: " + item.getPrice());
        }
    }
}
