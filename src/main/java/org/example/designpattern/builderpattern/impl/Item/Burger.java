package org.example.designpattern.builderpattern.impl.Item;

import org.example.designpattern.builderpattern.Item;
import org.example.designpattern.builderpattern.Packing;
import org.example.designpattern.builderpattern.impl.packing.WrapperPackingImpl;

import java.math.BigDecimal;

/**
 * @author xiaolu
 * @date 2024/9/29 12:08
 */
public abstract class Burger implements Item {

    @Override
    public Packing getPackage() {
        return new WrapperPackingImpl();
    }

    @Override
    public abstract BigDecimal getPrice();
}
