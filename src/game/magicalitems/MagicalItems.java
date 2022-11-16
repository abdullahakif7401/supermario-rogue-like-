package game.magicalitems;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;

public class MagicalItems extends Action {
    SuperMushroom superMushroom;
    PowerStar powerStar;
    int status = 0;
    public MagicalItems(SuperMushroom superMushroom) {
        this.superMushroom = superMushroom;
        status = 1;
    }

    public MagicalItems(PowerStar powerStar) {
        this.powerStar = powerStar;
        status = 2;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "";
        if (status == 1){
            superMushroom.actorUsesItem(actor);
            output = actor + " uses SuperMushroom";
        }
        else if (status == 2){
            powerStar.actorUsesItem(actor);
            output = actor + " uses PowerStar";
        }
        return output;
    }

    @Override
    public String menuDescription(Actor actor) {

        return execute(actor, null);
    }
}
