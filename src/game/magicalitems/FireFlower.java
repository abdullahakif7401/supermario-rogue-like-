package game.magicalitems;

import edu.engine.actions.Action;
import edu.engine.items.Item;
import game.actors.Player;

/**
 * Fire flower manages the fire flower item on the ground
 *
 * @author Seow Zheng Hao
 */
public class FireFlower extends Item{
    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public FireFlower(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        System.out.println(Player.optionshown);
        Action fireFlowerAction = new ConsumeFireFlower(this);
        addAction(fireFlowerAction);
    }
}
