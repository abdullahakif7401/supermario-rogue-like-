package game.magicalitems;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;
import game.actors.Player;
import game.Status;

/**
 * Fireflower consume covers the action available to the actor to consume a fire flower on the ground
 *
 * @author Seow Zheng Hao
 */
public class ConsumeFireFlower extends Action {
    FireFlower fireflower;
    public ConsumeFireFlower(FireFlower fireflower) {
        this.fireflower=fireflower;
    }

    /**
     * runs when the actor chooses to consume a fire flower on the ground
     *
     * @param actor the actor doing the action
     * @param map Gamemap class of the current map
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player.pickedup=true;
        Player.counter=20;
        actor.addCapability(Status.FIRE);
        map.locationOf(actor).removeItem(fireflower);
        return "Your "+actor+" consumed a Fire Flower! Somewhat more attack power now.";
    }

    /**
     * <p>
     *     Method that is called by the application to show a description of the option
     * </p>
     *
     * @param actor The actor performing the action.
     * @return String containing a description of the consume action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Consume fire flower [gains fire attack for 20 turns] (attacks so hot the ground literally catches on fire!)";
    }
}

