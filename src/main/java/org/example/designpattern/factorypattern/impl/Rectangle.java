package org.example.designpattern.factorypattern.impl;

import org.example.designpattern.factorypattern.Shape;

/**
 * @author xiaolu
 * @date 2024/9/29 10:37
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
