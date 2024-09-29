package org.example.designpattern.abstractfactorypattern;

import org.example.designpattern.abstractfactorypattern.factory.AbstractProductFactory;
import org.example.designpattern.abstractfactorypattern.factory.ColorFactory;
import org.example.designpattern.abstractfactorypattern.factory.ShapeFactory;

/**
 * @author xiaolu
 * @date 2024/9/29 11:29
 */
public class ProductFactoryProducer {

    public static AbstractProductFactory getFactory(String product) {
        if (product.equalsIgnoreCase("shape")) {
            return new ShapeFactory();
        } else if (product.equalsIgnoreCase("color")) {
            return new ColorFactory();
        }
        return null;
    }
}
