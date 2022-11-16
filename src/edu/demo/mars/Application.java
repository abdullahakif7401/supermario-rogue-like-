package edu.demo.mars;

import edu.engine.positions.FancyGroundFactory;
import edu.engine.positions.World;
import edu.demo.mars.actors.Bug;
import edu.demo.mars.actors.Player;
import edu.demo.mars.behaviours.SpitBehaviour;
import edu.demo.mars.grounds.Crater;
import edu.demo.mars.grounds.Floor;
import edu.demo.mars.grounds.LockedDoor;
import edu.demo.mars.grounds.Wall;
import edu.demo.mars.items.MartianItem;
import edu.demo.mars.items.Stick;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.items.Item;
import edu.engine.positions.GameMap;
import edu.engine.actions.MoveActorAction;
import game.behaviours.FollowBehaviour;

import java.util.Arrays;
import java.util.List;


public class Application {

    public static void main(String[] args) {
        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Floor(), new Wall(), new LockedDoor(),
                new Crater());
        GameMap gameMap;

        List<String> map = Arrays.asList(
                ".............",
                "......######.",
                "......+....+.",
                "......######.",
                ".............");
        gameMap = new GameMap(groundFactory, map);
        world.addGameMap(gameMap);

        List<String> marsMap = Arrays.asList(
                "ooooooooooooo",
                "oooooooo...oo",
                "oooooo....ooo",
                "oooooooo..ooo",
                "ooo..oooooooo",
                "ooooooooooooo");
        GameMap mars = new GameMap(groundFactory, marsMap);
        world.addGameMap(mars);

        MartianItem rocket = new MartianItem("Rocket", '^', false);
        rocket.addSampleAction(new MoveActorAction(mars.at(7, 2), "to Mars!"));
        gameMap.at(1, 1).addItem(rocket);

        Item spaceSuit = new MartianItem("space suit", '[', true);
        spaceSuit.addCapability(DemoCapabilities.SPACETRAVELLER);
        gameMap.at(0, 1).addItem(spaceSuit);

        Item stick = new Stick();
        gameMap.at(8, 2).addItem(stick);

        Actor player = new Player("The Player", '@', 100);
        world.addPlayer(player, gameMap.at(2, 3));

        Bug bug = new Bug();
        bug.addItemToInventory(new MartianItem("rock", '*', true));
        bug.behaviours.add(new SpitBehaviour(player));
        bug.behaviours.add(new FollowBehaviour(player));
        gameMap.at(0, 2).addActor(bug);

        world.run();
    }
}
