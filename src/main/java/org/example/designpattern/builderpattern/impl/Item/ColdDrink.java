package org.example.designpattern.builderpattern.impl.Item;

import org.example.designpattern.builderpattern.Item;
import org.example.designpattern.builderpattern.Packing;
import org.example.designpattern.builderpattern.impl.packing.BottlePackingImpl;

import java.math.BigDecimal;

/**
 * @author xiaolu
 * @date 2024/9/29 12:11
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing getPackage() {
        return new BottlePackingImpl();
    }

    public abstract BigDecimal getPrice();
}
