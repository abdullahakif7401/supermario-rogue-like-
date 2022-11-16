package game.Ground;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;

public class Teleportation extends Action {

    WarpPipe teledestination;
    WarpPipe teledeparture;

    /**
     *
     * @param destination
     * @param departure
     */
    public Teleportation(WarpPipe destination, WarpPipe departure) {
        this.teledestination = destination;
        this.teledeparture = departure;
    }

    public WarpPipe getTeledestination() {
        return teledestination;
    }

    /**
     *
     * @param teledestination
     */
    public void setTeledestination(WarpPipe teledestination) {
        this.teledestination = teledestination;
    }

    public WarpPipe getTeledeparture() {
        return teledeparture;
    }

    /**
     *
     * @param teledeparture
     */
    public void setTeledeparture(WarpPipe teledeparture) {
        this.teledeparture = teledeparture;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.moveActor(actor, this.teledestination.getLocation());
        this.teledestination.destination = this.teledeparture; // to allow travel back to the last/previous pipe that teleported you before
        return actor + " teleports to " + this.teledestination;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " teleports to " + this.teledestination;
    }
}
