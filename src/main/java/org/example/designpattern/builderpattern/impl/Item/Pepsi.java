package org.example.designpattern.builderpattern.impl.Item;

import java.math.BigDecimal;

/**
 * @author xiaolu
 * @date 2024/9/29 12:16
 */
public class Pepsi extends ColdDrink {
    @Override
    public String getName() {
        return "Pepsi";
    }

    @Override
    public BigDecimal getPrice() {
        return new BigDecimal("2.5");
    }
}
