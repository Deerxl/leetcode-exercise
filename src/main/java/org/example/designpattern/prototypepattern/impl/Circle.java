package org.example.designpattern.prototypepattern.impl;

import org.example.designpattern.prototypepattern.Shape;

/**
 * @author xiaolu
 * @date 2024/10/4 16:26
 */
public class Circle extends Shape {

    public Circle() {
        type = "circle";
    }


    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
