package game;

import java.util.Random;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;
import edu.engine.items.Item;
import edu.engine.positions.Location;
import edu.engine.weapons.Weapon;
import game.Ground.Fire;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	@Override
	public String execute(Actor actor, GameMap map) {

		Location targetlocation=map.locationOf(this.target);
		if (targetlocation.getGround().getDisplayChar()!='C') //check for pipe
//			System.out.println("not pipe");
			if (actor.hasCapability(Status.FIRE)) { //check for fire status
//				System.out.println("has status fire");
				targetlocation.setGround(new Fire()); //sets ground to fire
			}

		Weapon weapon = actor.getWeapon();

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			return actor + " misses " + target + ".";
		}

		int damage = weapon.damage();
		String result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
		target.hurt(damage);
		if (!target.isConscious()) {
			ActionList dropActions = new ActionList();
			// drop all items
			for (Item item : target.getInventory())
				dropActions.add(item.getDropAction(actor));
			for (Action drop : dropActions)
				drop.execute(target, map);
			// remove actor
			map.removeActor(target);
			result += System.lineSeparator() + target + " is killed.";
		}

		return result;
	}

	@Override
	public String menuDescription(Actor actor) {
		if (actor.hasCapability(Status.FIRE)){
			return actor + " attacks " + target + " at " + direction+" with fire";
		}
		return actor + " attacks " + target + " at " + direction;
	}
}
