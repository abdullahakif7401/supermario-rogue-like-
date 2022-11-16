package game;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;

import java.util.ArrayList;
import java.util.List;

import static game.Monologue.*;

/**
 * SpeakAction class manages the Action used to Interact and Speak with Toad
 * Toad's possible speaking sentences differ depending on what items the Player (Mario) has
 * @author Danesh Mariapan
 */
public class SpeakAction extends Action {

    private final Actor target;

    /**
     * Constructor for the Action
     * @param target the Actor that we want to speak to
     */
    public SpeakAction(Actor target) {
        this.target = target;
    }

    /**
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A random Monologue String that the Toad says (Differs based on what Mario has)
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int randomIndex = Utils.randomIndexFull();
        boolean flagOneRemoved = true;

        List<String> toadMonologue = new ArrayList<>(List.of(getToadSentences()));

        // Remove 1st Sentence if Player has Wrench
        for(int i = 0; i < actor.getInventory().size(); i ++){
            if (actor.getInventory().get(i).toString().equals("Wrench")){
                toadMonologue.remove(T1);
                randomIndex = Utils.randomIndexThree();
                flagOneRemoved = false;
            }
        }

        // Remove 2nd Sentence if Player has Power Star Effect
        if (actor.hasCapability(Status.INVINCIBLE) && !flagOneRemoved){
            toadMonologue.remove(T2);
            randomIndex = Utils.randomIndexTwo();
        }
        else if(actor.hasCapability(Status.INVINCIBLE)){
            toadMonologue.remove(T2);
            randomIndex = Utils.randomIndexThree();
        }

        return (target) + ": " + toadMonologue.get(randomIndex);
    }

    /**
     * @param actor The actor performing the action.
     * @return String - The possible chosen Action displayed on the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " speaks to " + target);
    }


}



