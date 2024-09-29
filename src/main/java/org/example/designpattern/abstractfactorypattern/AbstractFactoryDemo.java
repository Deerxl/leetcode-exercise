package org.example.designpattern.abstractfactorypattern;

import org.example.designpattern.abstractfactorypattern.factory.AbstractProductFactory;
import org.example.designpattern.factorypattern.Shape;
import org.example.designpattern.factorypattern.ShapeEnum;

/**
 * @author xiaolu
 * @date 2024/9/29 11:18
 */
public class AbstractFactoryDemo {

    public static void main(String[] args) {
        AbstractProductFactory shapeFactory = ProductFactoryProducer.getFactory("shape");
        Shape circleShape = shapeFactory.getShape(ShapeEnum.CIRCLE.code);
        circleShape.draw();


        AbstractProductFactory colorFactory = ProductFactoryProducer.getFactory("color");
        Color greenColor = colorFactory.getColor(ColorEnum.GREEN.color);
        greenColor.fill();
    }
}
