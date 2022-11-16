package edu.demo.mars.actors;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import edu.engine.displays.Menu;

/**
 * Class representing the Player.
 */
public class Player extends Actor {

	private final Menu menu = new Menu();
	
	/**
	 * Constructor.
	 *
	 * @param name Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// Check if it has previous action or not. If so, execute that last action.
		// Useful when you want to implement chain-actions like sleeping implementation.
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		return menu.showMenu(this, actions, display);
	}
}
