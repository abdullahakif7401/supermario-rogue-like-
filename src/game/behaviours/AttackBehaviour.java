package game.behaviours;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.Exit;
import edu.engine.positions.GameMap;
import edu.engine.positions.Location;
import game.AttackAction;
import game.Status;

import java.util.Random;

public class AttackBehaviour implements Behaviour {
    private final Random random = new Random();
    // TODO: develop and use it to attack the player automatically.
    @Override
    public Action getAction(Actor actor, GameMap map) {

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor() && destination.getActor().hasCapability(Status.HOSTILE_TO_ENEMY)) {
                return new AttackAction(actor, exit.getName());
            }
        }

        return null;
    }
}
