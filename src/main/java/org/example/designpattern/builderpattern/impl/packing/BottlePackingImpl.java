package org.example.designpattern.builderpattern.impl.packing;

import org.example.designpattern.builderpattern.Packing;

/**
 * @author xiaolu
 * @date 2024/9/29 12:07
 */
public class BottlePackingImpl implements Packing {

    @Override
    public String getPack() {
        return "Bottle";
    }
}
