package game.currency;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.items.DropItemAction;
import edu.engine.items.Item;
import edu.engine.items.PickUpItemAction;
import edu.engine.positions.Location;
import game.Resettable;

/**
 *  Coin class manages everything to do with a Coin Object
 *  @author Danesh Mariapan
 */
public class Coin extends Item implements Resettable {

    /**
     * Attributes / Variables of the Coin Class
     */
    private static final String NAME = "Coin";
    private static final Character DISPLAY_CHARACTER = '$';
    private static final boolean PORTABLE = true;
    private int value;
    Location location;
    Boolean resetchecker;

    /**
     * Coin Constructor
     *
     * @param value the currency value that the Coin has
     */
    public Coin(int value) {
        super(NAME, DISPLAY_CHARACTER, PORTABLE);
        this.value = value;
        this.registerInstance();
        resetchecker=false;

        Action coinPickUpItemAction = new CoinPickUpItemAction(this);
        addAction(coinPickUpItemAction);
    }

    /**
     * Getter for Coin Value
     * @return value
     */
    // Getter for Coin Value
    public int getValue() {
        return value;
    }

    /**
     * Setter for Coin Value
     * @param value Integer value of Coin
     */
    // Setter for Coin Value
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Tick, to experience passage of time
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        this.location = currentLocation;
        if (resetchecker){
            this.location.removeItem(this);
        }
    }

    /**
     * toString method
     * @return "Coin ($value)"
     */
    @Override
    public String toString() {
        return super.toString() + " " + "(" + this.getDisplayChar() + this.getValue() + ")";
    }

    /**
     * getCoinType method
     * @return the toString method
     */
    public String getCoinType() {
        return this.toString();
    }

    /**
     * Overriding Resettable Class methods
     * To remove Coins from the Map, when Map gets reset
     */
    @Override
    public void resetInstance() {
        resetchecker=true;
    }

    /**
     * register Instance
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }

    /**
     * Here we want to cancel the Item's Class Functionality implementation
     * @param actor that picks up
     * @return null
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }

    /**
     * Here we want to cancel the Item's Class Functionality implementation
     * @param actor that picks up
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

}


