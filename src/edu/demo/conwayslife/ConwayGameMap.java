package edu.demo.conwayslife;

import java.util.List;

import edu.engine.positions.GroundFactory;
import edu.engine.positions.Location;
import edu.engine.positions.GameMap;

public class ConwayGameMap extends GameMap {

	public ConwayGameMap(GroundFactory groundFactory, List<String> lines) {
		super(groundFactory, lines);
	}
	
	@Override
	protected Location makeNewLocation(int x, int y) {
		return new ConwayLocation(this, x, y);
	}
}
