package org.example.designpattern.strategypattern.impl;

import org.example.designpattern.strategypattern.CalStrategy;

/**
 * @author xiaolu
 * @date 2024/9/29 13:31
 */
public class MulOperation implements CalStrategy {

    @Override
    public int cal(int a, int b) {
        return a * b;
    }
}
