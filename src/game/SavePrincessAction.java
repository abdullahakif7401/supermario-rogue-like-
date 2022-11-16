package game;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;

import static game.enemies.Bowser.bowserKey;

/**
 * SavePrincessAction class manages the Action used to save Princess Peach to Win the Game
 * This Action is used to interact with Princess Peach if the Player has obtained the Key from Bowser
 * @author Danesh Mariapan
 */
public class SavePrincessAction extends Action {

    private final Actor target;

    /**
     * Constructor for the Action
     * @param target the Actor that we want to interact with (Princess Peach)
     */
    public SavePrincessAction(Actor target) {
        this.target = target;
    }

    /**
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Strings - For the End Game messages. (Either Victory or a hint to Win the Game)
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        // Check if Player/Mario has the Key to save Princess Peach
        if (actor.getInventory().contains(bowserKey)){
            return ("""
                    *******************************************
                    ***       CONGRATULATIONS PLAYER!       ***
                    *******************************************
                    ***     You saved Princess Peach :D     ***
                    *******************************************""");
        }
        else{
            return (target) + ": " + "Mario you need to Kill Bowser and take his Key to save me! ;( ";
        }

    }

    /**
     * @param actor The actor performing the action.
     * @return String - The possible chosen Action displayed on the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Save Princess Peach";
    }

}
