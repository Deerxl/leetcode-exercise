package org.example.designpattern.abstractfactorypattern;

import java.util.Arrays;

/**
 * @author xiaolu
 * @date 2024/9/29 11:08
 */
public enum ColorEnum {
    RED("red"),
    BLUE("blue"),
    GREEN("green"),
    ;

    String color;

    ColorEnum(String color) {
        this.color = color;
    }

    public static ColorEnum getColor(String color) {
        return Arrays.stream(ColorEnum.values())
                .filter(colorEnum -> colorEnum.color.equalsIgnoreCase(color))
                .findAny()
                .orElse(null);
    }
}
