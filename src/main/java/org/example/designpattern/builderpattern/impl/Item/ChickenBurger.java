package org.example.designpattern.builderpattern.impl.Item;

import java.math.BigDecimal;

/**
 * @author xiaolu
 * @date 2024/9/29 12:14
 */
public class ChickenBurger extends Burger {
    @Override
    public String getName() {
        return "Chicken Burger";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("25.00");
    }
}
