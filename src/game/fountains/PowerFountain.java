package game.fountains;

import game.magicalitems.DrinkWater;

public class PowerFountain extends Fountain{

    public PowerFountain() {
        super('A');
    }

    /**
     *
     * @param drinkWater
     * @return
     */
    public String fountainsEffect(DrinkWater drinkWater) {
        drinkWater.increaseIntrinsicAttackDamage(25);
        return drinkWater + " gained intrinsic attack damage by 15 ";
    }

    @Override
    public String typeOfWater() {
        return "IncreaseIntrinsicAttack water";
    }
}
