package game.enemies;


import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import edu.engine.weapons.IntrinsicWeapon;
import game.Resettable;
import game.Utils;

import java.util.ArrayList;
import java.util.List;

import static game.Monologue.getGoombaSentences;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemies implements Resettable {

	IntrinsicWeapon intrinsicWeapon;

	private int turnCounter = 0;
	List<String> goombaMonologue = new ArrayList<>(List.of(getGoombaSentences()));

	/**
	 * Constructor.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.registerInstance();
	}

	public IntrinsicWeapon intrinsicWeapon(){
		intrinsicWeapon = new IntrinsicWeapon(10, "KICK");
		return intrinsicWeapon;
	}

	/**
	 * Figure out what to do next.
	 * @see Actor#playTurn(ActionList, Action, GameMap, Display)
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

		int random_Value = Utils.randomVal();
		if ((float)random_Value < (float)(10/100)) {
			this.hurt(21);
		}

		// FOR SPEAKING - REQ5:
		// Increment Turn Counter
		turnCounter += 1;

		// Goomba speaks every Second (Alternating) turn
		if(turnCounter % 2 == 0){
			System.out.println("Goomba" + ": " + goombaMonologue.get(Utils.randomIndexThree()));
		}

		return super.playTurn(actions,lastAction, map, display);
	}

	@Override
	public void resetInstance() {
		this.hurt(21);
	}

	@Override
	public void registerInstance() {
		Resettable.super.registerInstance();
	}
}
