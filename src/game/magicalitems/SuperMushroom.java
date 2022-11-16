package game.magicalitems;

import edu.engine.actors.Actor;
import edu.engine.items.Item;
import game.Resettable;
import game.Status;

import java.util.List;

public class SuperMushroom extends Item implements Resettable {
//    SuperMushroom superMushroom = new SuperMushroom("SuperMushroom", '^', false);
    MagicalItems magicalItems = new MagicalItems(this);
    Actor actor;

    public SuperMushroom() {
        super("SuperMushroom", '^', false);
        addAction(magicalItems);
        this.registerInstance();
    }

    public void addToActorInvertory(Actor actor){
        List<Item> i = actor.getInventory();
        for (int a = 0; a < i.size(); a++){
            if ((i.get(a) instanceof SuperMushroom) == false){
                actor.addItemToInventory(this);
            }
        }
    }

    public void actorUsesItem(Actor actor) {
        actor.hasCapability(Status.TALL);
        actor.increaseMaxHp(50);
        actor.removeItemFromInventory(this);
    }

    @Override
    public void resetInstance() {
        removeCapability(Status.TALL);
    }

    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
