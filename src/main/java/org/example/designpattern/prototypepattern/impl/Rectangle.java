package org.example.designpattern.prototypepattern.impl;

import org.example.designpattern.prototypepattern.Shape;

/**
 * @author xiaolu
 * @date 2024/10/4 16:29
 */
public class Rectangle extends Shape {

    public Rectangle() {
        type = "rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
