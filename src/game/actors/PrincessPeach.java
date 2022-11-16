package game.actors;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actions.DoNothingAction;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import game.SavePrincessAction;
import game.Utils;

import java.util.ArrayList;
import java.util.List;

import static game.Monologue.getPrincessPeachSentences;

/**
 * PrincessPeach class manages everything to do with the Character Princess Peach
 * @author Danesh Mariapan
 */
public class PrincessPeach extends Actor {

    /**
     * Princess Peach Attributes
     */
    private static final String NAME = "Princess Peach";
    private static final Character DISPLAY_CHARACTER = 'P';
    private static final int HIT_POINTS = 1;

    // Counter to Count the number of turns since Princess Peach has been Instantiated
    private int turnCounter = 0;
    // String List which holds Princess Peach's Monologue
    List<String> princessPeachMonologue = new ArrayList<>(List.of(getPrincessPeachSentences()));

    /**
     * Constructor for Princess Peach
     */
    public PrincessPeach() {
        super(NAME, DISPLAY_CHARACTER, HIT_POINTS);
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return DoNothingAction() - Princess Peach is not able to Move or Attack
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        // FOR SPEAKING - REQ5:
        // Increment Turn Counter
        turnCounter += 1;

        // Princess Peach speaks every Second (Alternating) turn
        if(turnCounter % 2 == 0){
            System.out.println(NAME + ": " + princessPeachMonologue.get(Utils.randomIndexThree()));
        }

        return new DoNothingAction();
    }

    /**
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList - Contains the new added action (SavePrincessAction)
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        // Add SavePrincessAction for End Game
        return new ActionList(new SavePrincessAction(this));
    }


}
