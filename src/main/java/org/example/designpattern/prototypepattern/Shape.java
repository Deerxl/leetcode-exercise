package org.example.designpattern.prototypepattern;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaolu
 * @date 2024/9/30 11:41
 */
public abstract class Shape implements Cloneable {

    @Getter
    @Setter
    private String id;

    @Getter
    protected String type;

    public abstract void draw();

    @Override
    public Shape clone() {
        try {
            Shape clone = (Shape) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original

            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
