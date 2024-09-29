package org.example.designpattern.factorypattern;

import org.example.designpattern.factorypattern.impl.Circle;
import org.example.designpattern.factorypattern.impl.Rectangle;
import org.example.designpattern.factorypattern.impl.Square;

/**
 * @author xiaolu
 * @date 2024/9/29 10:38
 */
public class ShapeFactory {

    public static Shape getShape(String shapeType) {
        ShapeEnum shapeEnum = ShapeEnum.getShapeEnum(shapeType);
        Shape shape = null;
        switch (shapeEnum) {
            case CIRCLE:
                shape = new Circle();
                break;
            case RECTANGLE:
                shape = new Rectangle();
                break;
            case SQUARE:
                shape = new Square();
                break;
            default:
                break;
        }
        return shape;
    }
}
