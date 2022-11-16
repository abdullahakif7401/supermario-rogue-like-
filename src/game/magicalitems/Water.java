package game.magicalitems;

import game.fountains.Fountain;

public class Water {

    Fountain fountain;
    public static String typeOfEffect;
    String typeOfWater;

    public Water() {
        fountain = Fountain.fountain;
        typeOfEffect = fountain.fountainsEffect(Bottle.drinkWater);
        typeOfWater = fountain.typeOfWater();
    }
}
