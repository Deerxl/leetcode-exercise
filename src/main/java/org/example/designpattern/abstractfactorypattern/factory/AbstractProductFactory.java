package org.example.designpattern.abstractfactorypattern.factory;

import org.example.designpattern.abstractfactorypattern.Color;
import org.example.designpattern.factorypattern.Shape;

/**
 * @author xiaolu
 * @date 2024/9/29 10:52
 */
public abstract class AbstractProductFactory {

    public Shape getShape(String shape) {
        return null;
    }

    public Color getColor(String color) {
        return null;
    }
}
