package org.example.designpattern.builderpattern;

import java.math.BigDecimal;

/**
 * @author xiaolu
 * @date 2024/9/29 12:05
 */
public interface Item {

    String getName();

    Packing getPackage();

    BigDecimal getPrice();
}
