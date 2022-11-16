package game.enemies;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import edu.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Status;
import game.Utils;

import java.util.ArrayList;
import java.util.List;

import static game.Monologue.getKoopaSentences;

public class Koopa extends Enemies implements Resettable {

    IntrinsicWeapon intrinsicWeapon;

    private int turnCounter = 0;
    List<String> koopaMonologue = new ArrayList<>(List.of(getKoopaSentences()));

    /**
     * Constructor.
     *
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.registerInstance();
    }

    public IntrinsicWeapon intrinsicWeapon(){
        intrinsicWeapon = new IntrinsicWeapon(30, "PUNCH");
        return intrinsicWeapon;
    }

    @Override
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
//        ADD CODE FOR LAST POINT IN IMPLEMENTATION EXPECTATIONS (NOT CLEAR)
        return actions;
    }

    @Override
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

        // Koopa speaks every Second (Alternating) turn
        if(turnCounter % 2 == 0){
            System.out.println("Koopa" + ": " + koopaMonologue.get(Utils.randomIndexTwo()));
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public void resetInstance() {
        this.hurt(101);
    }

    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
