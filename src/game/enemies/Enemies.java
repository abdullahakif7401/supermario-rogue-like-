package game.enemies;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actions.DoNothingAction;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import edu.engine.positions.Location;
import game.*;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;

public abstract class Enemies extends Actor {
    Location location;
    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemies(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(1, new AttackBehaviour());
        if (displayChar!='B' && displayChar!='Y') {
            this.behaviours.put(10, new WanderBehaviour());
        }
    }
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            this.addCapability(Status.FOLLOWBEHAVIOUR);
            if (getDisplayChar() != 'Y') {
                behaviours.put(2, new FollowBehaviour(otherActor));
            }
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        FloorOnFire(this, map);

        if (isConscious() == false) {
            //drop key upon death for bowser
//            if (this.getDisplayChar()=='B'){
//                map.locationOf(this).addItem(new Key());
//            }

            map.removeActor(this);
        }
        else {
            for (Behaviour Behaviour : behaviours.values()) {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }

        return new DoNothingAction();
    }
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    public void FloorOnFire(Actor actor, GameMap map){
        if (map.locationOf(actor).getGround().getDisplayChar()=='v'){
            actor.hurt(20);
            System.out.println(actor.toString()+ " is standing on fire!");
        }
    }
}
