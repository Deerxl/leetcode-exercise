package org.example.designpattern.strategypattern;

import org.example.designpattern.strategypattern.impl.AddOperation;

/**
 * @author xiaolu
 * @date 2024/9/29 13:33
 */
public class StrategyPatternDemo {

    public static void main(String[] args) {
        int a = 10, b = 5;
        CalContext addCalContext = new CalContext(new AddOperation());
        System.out.println("a + b = " + addCalContext.executeStrategy(a, b));
    }
}
