package org.example.designpattern.builderpattern.impl.Item;

import java.math.BigDecimal;

/**
 * @author xiaolu
 * @date 2024/9/29 12:15
 */
public class Coke extends ColdDrink {
    @Override
    public String getName() {
        return "Coke";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("3.00");
    }
}
