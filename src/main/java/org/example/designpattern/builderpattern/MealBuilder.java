package org.example.designpattern.builderpattern;

import org.example.designpattern.builderpattern.impl.Item.ChickenBurger;
import org.example.designpattern.builderpattern.impl.Item.Coke;
import org.example.designpattern.builderpattern.impl.Item.Pepsi;
import org.example.designpattern.builderpattern.impl.Item.VegBurger;

/**
 * @author xiaolu
 * @date 2024/9/29 13:09
 */
public class MealBuilder {

    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        Item coke = new Coke();
        Item vegBurger = new VegBurger();
        meal.addItems(coke, vegBurger);
        return meal;
    }

    public Meal prepareChickenMeal() {
        Meal meal = new Meal();
        Item pepsi = new Pepsi();
        Item chickenBurger = new ChickenBurger();
        meal.addItems(pepsi, chickenBurger);
        return meal;
    }
}
