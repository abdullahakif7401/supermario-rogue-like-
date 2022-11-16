package game.enemies;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import game.Status;
import game.Utils;
import game.magicalitems.SuperMushroom;

import java.util.ArrayList;
import java.util.List;

import static game.Monologue.getFlyingKoopaSentences;

public class FlyingKoopa extends Enemies{

    private int turnCounter = 0;
    List<String> flyingKoopaMonologue = new ArrayList<>(List.of(getFlyingKoopaSentences()));

    /**
     * Constructor
     */
    public FlyingKoopa() {
        super("Flying Koopa", 'F', 150);
        this.addCapability(Status.FLYINGKOOPA);
    }

    /**
     *
     * @param actions
     * @param lastAction
     * @param map
     * @param display
     * @return
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (isConscious() == false) {
            if (hasCapability(Status.DORMANT) == false){
                addCapability(Status.DORMANT);
                setDisplayChar('D');
            }
        }

        // FOR SPEAKING - REQ5:
        // Increment Turn Counter
        turnCounter += 1;

        // Flying Koopa speaks every Second (Alternating) turn
        if(turnCounter % 2 == 0){
            System.out.println("Flying Koopa" + ": " + flyingKoopaMonologue.get(Utils.randomIndexThree()));
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     *
     * @param otherActor
     * @param direction
     * @param map
     * @return
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions;
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
//        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//            actions.add(new AttackAction(this,direction));
//        }
        if (hasCapability(Status.DORMANT) == false) {
            actions = super.allowableActions(otherActor, direction, map);
        }
        else {
            actions = new ActionList();
        }
        if (this.hasCapability(Status.DORMANT)){
            if (otherActor.hasCapability(Status.HASWRENCH)) {
                location.addItem(new SuperMushroom());
            }
        }
        return actions;
    }
}
