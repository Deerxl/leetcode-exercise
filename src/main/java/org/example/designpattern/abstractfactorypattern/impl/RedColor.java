package org.example.designpattern.abstractfactorypattern.impl;

import org.example.designpattern.abstractfactorypattern.Color;

/**
 * @author xiaolu
 * @date 2024/9/29 11:05
 */
public class RedColor implements Color {

    @Override
    public void fill() {
        System.out.println("inside RedColor::fill() method.");
    }
}
