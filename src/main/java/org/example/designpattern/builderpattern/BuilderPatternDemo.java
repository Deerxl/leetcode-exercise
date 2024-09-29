package org.example.designpattern.builderpattern;

/**
 * @author xiaolu
 * @date 2024/9/29 13:14
 */
public class BuilderPatternDemo {

    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        System.out.println("vegMeal: ");
        System.out.println("vegMeal total cost: " + vegMeal.getCost());
        System.out.println("vegMeal details: ");
        vegMeal.showItems();

        Meal chickenMeal = mealBuilder.prepareChickenMeal();
        System.out.println("chickenMeal: ");
        System.out.println("chickenMeal total cost: " + chickenMeal.getCost());
        System.out.println("chickenMeal details: ");
        chickenMeal.showItems();
    }
}
