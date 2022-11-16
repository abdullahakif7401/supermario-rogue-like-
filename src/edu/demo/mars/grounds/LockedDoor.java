package edu.demo.mars.grounds;

import edu.demo.mars.actions.WindowSmashAction;
import edu.engine.positions.Location;
import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.positions.Ground;


public class LockedDoor extends Ground {

	public LockedDoor() {
		super('+');
	}
	
	@Override
	public boolean canActorEnter(Actor actor) {
		return false;
	}
	
	@Override
	public ActionList allowableActions(Actor actor, Location location, String direction){
		return new ActionList(new WindowSmashAction(direction, location));
	}
	
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}
}
