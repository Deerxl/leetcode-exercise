package org.example.designpattern.prototypepattern;

/**
 * @author xiaolu
 * @date 2024/10/4 16:34
 */
public class PrototypePatternDemo {

    public static void main(String[] args) {
        ShapeCache.loadShapeCache();


        Shape circle1 = ShapeCache.getShape("circle1");
        System.out.println("shape: " + circle1.getType());

        Shape rectangle1 = ShapeCache.getShape("rectangle1");
        System.out.println("shape: " + rectangle1.getType());

        Shape square1 = ShapeCache.getShape("square1");
        System.out.println("shape: " + square1.getType());
    }
}
