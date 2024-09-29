package org.example.designpattern.abstractfactorypattern.factory;

import org.example.designpattern.abstractfactorypattern.Color;
import org.example.designpattern.abstractfactorypattern.impl.BlueColor;
import org.example.designpattern.abstractfactorypattern.impl.GreenColor;
import org.example.designpattern.abstractfactorypattern.impl.RedColor;

/**
 * @author xiaolu
 * @date 2024/9/29 11:08
 */
public class ColorFactory extends AbstractProductFactory {

    @Override
    public Color getColor(String color) {
        Color c = null;
        switch (color) {
            case "red":
                c = new RedColor();
                break;
            case "blue":
                c = new BlueColor();
                break;
            case "green":
                c = new GreenColor();
                break;
            default:
                break;
        }
        return c;
    }
}
