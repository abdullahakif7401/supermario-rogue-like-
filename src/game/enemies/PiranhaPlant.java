package game.enemies;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import edu.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.Utils;

import java.util.ArrayList;
import java.util.List;

import static game.Monologue.getPiranhaPlantsSentences;

public class PiranhaPlant extends Enemies{

    IntrinsicWeapon intrinsicWeapon;

    private int turnCounter = 0;
    List<String> piranhaPlantMonologue = new ArrayList<>(List.of(getPiranhaPlantsSentences()));


    public PiranhaPlant() {
        super("PiranhaPlant", 'Y', 5);
        this.addCapability(Status.NONMOVEABLE);
    }

    public IntrinsicWeapon intrinsicWeapon(){
        intrinsicWeapon = new IntrinsicWeapon(100, "CHOMP");
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

        // FOR SPEAKING - REQ5:
        // Increment Turn Counter
        turnCounter += 1;

        // Piranha Plant speaks every Second (Alternating) turn
        if(turnCounter % 2 == 0){
            System.out.println("Piranha Plant" + ": " + piranhaPlantMonologue.get(Utils.randomIndexTwo()));
        }

        return super.playTurn(actions, lastAction, map, display);
    }
}
