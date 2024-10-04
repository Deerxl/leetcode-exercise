package org.example.designpattern.prototypepattern;

import org.example.designpattern.prototypepattern.impl.Circle;
import org.example.designpattern.prototypepattern.impl.Rectangle;
import org.example.designpattern.prototypepattern.impl.Square;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaolu
 * @date 2024/10/4 16:30
 */
public class ShapeCache {

    private static Map<String, Shape> shapeMap = new HashMap<>();

    public static Shape getShape(String id) {
        Shape shape = shapeMap.get(id);
        return shape.clone();
    }

    public static void loadShapeCache() {
        Circle circle = new Circle();
        circle.setId("circle1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rectangle = new Rectangle();
        rectangle.setId("rectangle1");
        shapeMap.put(rectangle.getId(), rectangle);

        Square square = new Square();
        square.setId("square1");
        shapeMap.put(square.getId(), square);
    }
}
