package game.magicalitems;

import edu.engine.items.Item;

import java.util.Stack;

public class Bottle extends Item {
    public static DrinkWater drinkWater;
    Stack<Water> drinkingWater = new Stack<Water>();

    public static Bottle bottle; // instance of Bottle to update the status of refillingBottle

    public Bottle(DrinkWater drinkWater) {
        super("Bottle", 'b', false);
        bottle = this;
        Bottle.drinkWater = drinkWater;
    }

    public boolean isdrinkingWaterEmpty() {
        return drinkingWater.empty();
    }

    public String actorUsesItem() {
        String output = "";
        if (isdrinkingWaterEmpty() == false){
            output = drinkingWater.pop().typeOfWater;
            bottle = this;
        }
        else {
            output = "Heyyyyyy!!!! THE BOTTLE IS EMPTY :-(";
        }
        return output;
    }

    public void refillingBottle (Water water) {
        bottle = this;
        drinkingWater.push(water);
    }

    @Override
    public String toString() {
        return "Bottle" + drinkingWater;
    }
}
