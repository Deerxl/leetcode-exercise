package org.example.designpattern.prototypepattern.impl;

import org.example.designpattern.prototypepattern.Shape;

/**
 * @author xiaolu
 * @date 2024/10/4 16:28
 */
public class Square extends Shape {

    public Square() {
        type = "square";
    }

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
