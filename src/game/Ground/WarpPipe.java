package game.Ground;

import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.positions.Ground;
import edu.engine.positions.Location;
import game.Resettable;
import game.enemies.PiranhaPlant;

public class WarpPipe extends Ground implements Resettable {

    String map;
    Location location;
    WarpPipe destination;
    WarpPipe departure = this;
    PiranhaPlant piranhaPlant;

    /**
     *
     * @param map
     */
    public WarpPipe(String map) {
        super('C');
        this.map = map;
        this.registerInstance();
    }

    /**
     *
     * @param destination
     */
    public WarpPipe(WarpPipe destination) {
        super('C');
        this.destination = destination;
        this.registerInstance();
    }

    /**
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (location.containsAnActor() == true) {
            actions.add(new Teleportation(destination, departure));
        }
        return actions;
    }

    /**
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        this.location = location;
        if (piranhaPlant == null){
            if (location.containsAnActor() == false && this.map != "lavaZone"){
                piranhaPlant = new PiranhaPlant();
                location.addActor(piranhaPlant);
            }
        }
        super.tick(this.location);
    }

    public String getMap() {
        return map;
    }

    /**
     *
     * @param map
     */
    public void setMap(String map) {
        this.map = map;
    }

    public Location getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    public WarpPipe getDestination() {
        return destination;
    }

    /**
     *
     * @param destination
     */
    public void setDestination(WarpPipe destination) {
        this.destination = destination;
    }

    public WarpPipe getDeparture() {
        return departure;
    }

    /**
     *
     * @param departure
     */
    public void setDeparture(WarpPipe departure) {
        this.departure = departure;
    }

    @Override
    public String toString() {
        return "WarpPipe is on " + map;
    }

    /**
     * <p>
     *     Method called when the player chooses the resetgame action
     * </p>
     *
     */
    @Override
    public void resetInstance() {
        if (this.piranhaPlant!=null) {
            if (this.piranhaPlant.isConscious()) {
                this.piranhaPlant.increaseMaxHp(50);
            } else {
                this.piranhaPlant = null;
            }
        }
    }

    /**
     * <p>
     *     Method called in the constructor to add this class as a resettable class
     * </p>
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
