package game.actors;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actions.DoNothingAction;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.items.Item;
import edu.engine.positions.GameMap;
import game.SpeakAction;
import game.TradeAction;
import game.Utils;
import game.Wrench;
import game.magicalitems.PowerStar;
import game.magicalitems.SuperMushroom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static game.Monologue.getToadSentences;

/**
 *  Toad class manages everything to do with the Character Toad and the Shop Items/Trading
 *  @author Danesh Mariapan
 */
public class Toad extends Actor {

    private int turnCounter = 0;
    List<String> toadMonologue = new ArrayList<>(List.of(getToadSentences()));

    /**
     * Toad Attributes
     */
    private static final String NAME = "Toad";
    private static final Character DISPLAY_CHARACTER = 'O';
    private static final int HIT_POINTS = 1;
    private final HashMap<Item, Integer> shopHashMap = new HashMap<Item, Integer>();

    private static final int POWER_STAR_COST = 600;
    private static final int SUPER_MUSHROOM_COST = 400;
    private static final int WRENCH_COST = 200;

    /**
     * Constructor for Toad
     */
    public Toad() {
        super(NAME, DISPLAY_CHARACTER, HIT_POINTS);
        this.shopHashMap.put(new Wrench(), WRENCH_COST);
        this.shopHashMap.put(new SuperMushroom(), SUPER_MUSHROOM_COST);
        this.shopHashMap.put(new PowerStar("PowerStar", '*', false), POWER_STAR_COST);
    }

    /**
     * Override playTurn method
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction()
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // FOR SPEAKING - REQ5:
        // Increment Turn Counter
        turnCounter += 1;

        // Toad speaks every Second (Alternating) turn
        if(turnCounter % 2 == 0){
            System.out.println(NAME + ": " + toadMonologue.get(Utils.randomIndexFull()));
        }

        return new DoNothingAction();
    }

    /**
     * Override ActionList method
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList newActions = new ActionList(new SpeakAction(this));
        for (Item shopItem: this.shopHashMap.keySet()) {
            int cost = this.shopHashMap.get(shopItem);
            newActions.add(new TradeAction(shopItem, cost));
        }

        return newActions;
    }


}
