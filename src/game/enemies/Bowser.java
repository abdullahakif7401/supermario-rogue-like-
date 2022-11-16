package game.enemies;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import edu.engine.positions.Location;
import edu.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.Utils;
import game.magicalitems.Key;

import java.util.ArrayList;
import java.util.List;

import static game.Monologue.getBowserSentences;

public class Bowser extends Enemies implements Resettable {

    Location storeLocation;
    IntrinsicWeapon intrinsicWeapon;

    private int turnCounter = 0;
    List<String> bowserMonologue = new ArrayList<>(List.of(getBowserSentences()));

    // Bowser's Key
    public static Key bowserKey = new Key();

    /**
     *
     * @param currentLocation
     */
    public Bowser(Location currentLocation) {
        super("Bowser", 'B', 5);
        this.addCapability(Status.FIRE);
        this.storeLocation = currentLocation;
        registerInstance();

        // Bowser hod the Key to save Princess Peach in his Inventory
        this.addItemToInventory(bowserKey);
    }

    /**
     *
     * @return
     */
    public IntrinsicWeapon intrinsicWeapon(){
        intrinsicWeapon = new IntrinsicWeapon(80, "PUNCH");
        return intrinsicWeapon;
    }

    /**
     *
     * @param actions
     * @param lastAction
     * @param map
     * @param display
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (this.hasCapability(Status.BOWSER)) {
            if (storeLocation.containsAnActor() == false){
                this.removeCapability(Status.BOWSER);
            }
        }

        // Drop the Key onto map when Bowser dies - Danesh REQ2
        if (!this.isConscious()){
            this.removeItemFromInventory(bowserKey);
            map.locationOf(this).addItem(bowserKey);
        }

        // FOR SPEAKING - REQ5:
        // Increment Turn Counter
        turnCounter += 1;

        // Bowser speaks every Second (Alternating) turn
        if(turnCounter % 2 == 0){
            System.out.println("Bowser" + ": " + bowserMonologue.get(Utils.randomIndexFull()));
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String toString() {
        return "Bowser";
    }

    /**
     * <p>
     *     Method called when the player chooses the resetgame action
     * </p>
     *
     */
    @Override
    public void resetInstance() {
        Location originallocation=storeLocation.map().at(15,8);
        storeLocation.map().moveActor(this,originallocation);
        this.heal(99999);
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
