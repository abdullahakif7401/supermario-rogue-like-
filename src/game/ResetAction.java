package game;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;
import game.actors.Player;

/**
 * Class that handles the resetgame option available to the player after turn 1
 *
 * @author Seow Zheng Hao
 */
public class ResetAction extends Action {
    /**
     * <p>
     *     Method that is called when the player chooses the resetgame option
     * </p>did
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String containing description of reset success
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        Player.resetchecker=false;
        return "The game has been reset to baby mode.";
    }

    /**
     * <p>
     *     Method that is called to display a description of the resetgame option
     * </p>
     *
     * @param actor The actor performing the action.
     * @return String containing description of reset action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game. (removes enemies, player status, reset player health, some trees and all coins on the ground) [can only be done once!] p.s for pussies";
    }

    /**
     * <p>
     *     Method that overrides the default hotkey for resetgame option
     * </p>
     *
     * @return String containing desired hotkey for resetgame option
     */
    @Override
    public String hotkey(){
        return "r";
    }
}
