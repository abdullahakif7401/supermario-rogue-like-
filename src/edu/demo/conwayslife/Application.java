package edu.demo.conwayslife;

import java.util.Arrays;
import java.util.List;

import edu.engine.positions.FancyGroundFactory;
import edu.engine.positions.World;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;

public class Application {

	public static void main(String[] args) {
		World world = new World(new Display());

		FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Tree());
		List<String> map = Arrays.asList(
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"......................................++........................................",
		".....................................++.........................................",
		"......................................+.........................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		"................................................................................",
		".........................................................................++.....",
		"........................................................................++.++...",
		".........................................................................++++...",
		"..........................................................................++....",
		"................................................................................");
		GameMap gameMap = new ConwayGameMap(groundFactory, map );
		world.addGameMap(gameMap);
		
		Actor player = new Player("Player", '@', 100);
		world.addPlayer(player, gameMap.at(10, 10));
			
		world.run();
	}
}
