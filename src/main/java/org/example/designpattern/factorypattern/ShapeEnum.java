package org.example.designpattern.factorypattern;

import java.util.Arrays;

/**
 * @author xiaolu
 * @date 2024/9/29 10:40
 */
public enum ShapeEnum {
    CIRCLE("Circle"),
    RECTANGLE("Rectangle"),
    SQUARE("Square");
    ;

    public String code;

    ShapeEnum(String code) {
        this.code = code;
    }

    public static ShapeEnum getShapeEnum(String code) {
        return Arrays.stream(ShapeEnum.values())
                .filter(shapeEnum -> shapeEnum.code.equalsIgnoreCase(code))
                .findAny()
                .orElse(null);
    }
}
