package org.example.designpattern.strategypattern;

/**
 * @author xiaolu
 * @date 2024/9/29 13:31
 */
public class CalContext {

    private CalStrategy calStrategy;

    public CalContext(CalStrategy calStrategy) {
        this.calStrategy = calStrategy;
    }

    public int executeStrategy(int a, int b) {
        return calStrategy.cal(a, b);
    }
}
