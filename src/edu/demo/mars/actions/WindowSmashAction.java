package edu.demo.mars.actions;

import edu.engine.positions.Location;
import edu.demo.mars.grounds.Floor;
import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;

import java.util.Random;


public class WindowSmashAction extends Action {

	private final String direction;
	private final Location windowLocation;
	private final Random rand = new Random();
	
	public WindowSmashAction(String direction, Location windowLocation) {
		this.direction = direction;
		this.windowLocation = windowLocation;
	}
	
	@Override
	public String execute(Actor actor, GameMap map) {
		if(rand.nextBoolean()) {
			return actor + " hurts their foot.";
		}
		else {
			windowLocation.setGround(new Floor());
			return "The window is smashed";
		}
	}

	@Override
	public String menuDescription(Actor actor) {
		return actor + " smashes the window to the " + direction;
	}
}
