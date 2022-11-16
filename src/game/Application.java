package game;

import java.util.Arrays;
import java.util.List;

import edu.engine.displays.Display;
import edu.engine.positions.FancyGroundFactory;
import edu.engine.positions.GameMap;
import edu.engine.positions.World;
import game.Ground.*;
import game.actors.Player;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.enemies.Bowser;
import game.fountains.HealthFountain;
import game.fountains.PowerFountain;
import game.magicalitems.Bottle;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

			World world = new World(new Display());

			FancyGroundFactory groundFactory = new FancyGroundFactory(new Lava(), new Dirt(), new Wall(), new Floor(), new Tree());

			List<String> map = Arrays.asList(
				"..........................................##..........+.........................",
				"............+............+..................#...................................",
				"............................................#...................................",
				".............................................##......................+..........",
				"...............................................#................................",
				"................................................#...............................",
				".................+................................#.............................",
				".................................................##.............................",
				"................................................##..............................",
				".........+..............................+#____####.................+............",
				".......................................+#_____###++.............................",
				".......................................+#______###..............................",
				"........................................+#_____###..............................",
				"........................+........................##.............+...............",
				"...................................................#............................",
				"....................................................#...........................",
				"...................+.................................#..........................",
				"......................................................#.........................",
				".......................................................##.......................");

			GameMap gameMap = new GameMap(groundFactory, map);
			world.addGameMap(gameMap);

			Player mario = new Player("Mario", 'm', 100);
			world.addPlayer(mario, gameMap.at(42, 10));

			// Part of REQ 5:
			// Toad can be added Manually ("Somewhere in the Floor + Walls area, near Mario when the game starts")
			gameMap.at(42, 11).addActor(new Toad());

			List<String> lavaZone = Arrays.asList(
				".............................................",
				"............+............+...................",
				".............................................",
				".............................................",
				".............................................",
				".............................................",
				".................+...........................",
				".............................................",
				".............................................",
				".........+...................................",
				".............................................",
				".............................................");

			GameMap newLavaMap = new GameMap(groundFactory, lavaZone);
			world.addGameMap(newLavaMap);

			Lava lava = new Lava();
			newLavaMap.at(2,3).setGround(lava);
			newLavaMap.at(8,6).setGround(lava);
			newLavaMap.at(9,10).setGround(lava);
			newLavaMap.at(22,9).setGround(lava);
			newLavaMap.at(42,5).setGround(lava);
			newLavaMap.at(32,6).setGround(lava);
			newLavaMap.at(12,2).setGround(lava);
			newLavaMap.at(20,1).setGround(lava);
			newLavaMap.at(15,8).setGround(lava);
			newLavaMap.at(18,7).setGround(lava);
			newLavaMap.at(10,10).setGround(lava);
			newLavaMap.at(16,11).setGround(lava);

			WarpPipe warpPipe = new WarpPipe("lavaZone");
			newLavaMap.at(0,0).setGround(warpPipe);
			gameMap.at(15,18).setGround(new WarpPipe(warpPipe));
			gameMap.at(20,5).setGround(new WarpPipe(warpPipe));
			gameMap.at(5,8).setGround(new WarpPipe(warpPipe));
			gameMap.at(18,13).setGround(new WarpPipe(warpPipe));
			gameMap.at(10,10).setGround(new WarpPipe(warpPipe));
			gameMap.at(19,10).setGround(new WarpPipe(warpPipe));

			// Add Bowser to Lava Map
			newLavaMap.at(17, 8).addActor(new Bowser(newLavaMap.at(15, 8)));
			// Add Princess Peach to Lava Map
			newLavaMap.at(16, 8).addActor(new PrincessPeach());


			Bottle bottle = new Bottle(mario);
			mario.addItemToInventory(bottle);
			HealthFountain healthFountain = new HealthFountain();
			gameMap.at(16, 5).setGround(healthFountain);
			PowerFountain powerFountain = new PowerFountain();
			gameMap.at(20, 13).setGround(powerFountain);


			world.run();
	}
}
