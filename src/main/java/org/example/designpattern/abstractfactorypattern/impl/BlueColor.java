package org.example.designpattern.abstractfactorypattern.impl;

import org.example.designpattern.abstractfactorypattern.Color;

/**
 * @author xiaolu
 * @date 2024/9/29 11:06
 */
public class BlueColor implements Color {

    @Override
    public void fill() {
        System.out.println("Inside BlueColor::fill() method.");
    }
}
