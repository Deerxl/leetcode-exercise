package org.example.designpattern.builderpattern.impl.Item;

import java.math.BigDecimal;

/**
 * @author xiaolu
 * @date 2024/9/29 12:13
 */
public class VegBurger extends Burger {

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("15.00");
    }

    @Override
    public String getName() {
        return "Veg Burger";
    }
}
