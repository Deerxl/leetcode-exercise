package org.example.designpattern.factorypattern.impl;

import org.example.designpattern.factorypattern.Shape;

/**
 * @author xiaolu
 * @date 2024/9/29 10:36
 */
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
