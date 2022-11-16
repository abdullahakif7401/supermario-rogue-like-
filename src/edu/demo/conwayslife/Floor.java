package edu.demo.conwayslife;

import edu.engine.positions.Ground;

public class Floor extends Ground {

	public Floor() {
		super('.');
		addCapability(Status.DEAD);
	}
}
