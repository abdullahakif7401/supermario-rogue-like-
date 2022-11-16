package edu.demo.mars.actors;

import java.util.*;

import edu.demo.mars.actions.KickAction;
import edu.engine.actors.Actor;
import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.displays.Display;
import edu.engine.weapons.IntrinsicWeapon;
import edu.engine.positions.GameMap;
import game.behaviours.Behaviour;


public class Bug extends Actor {

	private final Random rand = new Random();
	public List<Behaviour> behaviours = new ArrayList<>();

	public Bug() {
		super("Feature", 'x', 1);
	}
	
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		for (Behaviour behaviour : behaviours) {
			Action action = behaviour.getAction(this, map);
			if(action != null)
				return action;
		}
		
		return actions.get(rand.nextInt(actions.size()));
	}

	@Override
	public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
		ActionList list = super.allowableActions(otherActor, direction, map);
		list.add(new KickAction(this));
		return list;
	}

	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(1, "stings");
	}
}
