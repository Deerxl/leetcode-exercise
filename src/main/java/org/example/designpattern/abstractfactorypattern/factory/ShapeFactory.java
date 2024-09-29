package org.example.designpattern.abstractfactorypattern.factory;

import org.example.designpattern.factorypattern.Shape;
import org.example.designpattern.factorypattern.ShapeEnum;
import org.example.designpattern.factorypattern.impl.Circle;
import org.example.designpattern.factorypattern.impl.Rectangle;
import org.example.designpattern.factorypattern.impl.Square;

/**
 * @author xiaolu
 * @date 2024/9/29 11:35
 */
public class ShapeFactory extends AbstractProductFactory {

    @Override
    public Shape getShape(String shapeType) {
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
