package game.fountains;

import game.magicalitems.Bottle;
import game.magicalitems.DrinkWater;

public class HealthFountain extends Fountain{

    public HealthFountain() {
        super('H');
    }

    /**
     *
     * @param drinkWater
     * @return
     */
    public String fountainsEffect(DrinkWater drinkWater) {
        drinkWater.healing(50);
        return drinkWater + " gained 50 HP ";
    }

    @Override
    public String typeOfWater() {
        return "Healing water";
    }
}
