package game.actors;

import edu.engine.actions.Action;
import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.displays.Display;
import edu.engine.positions.GameMap;
import edu.engine.displays.Menu;
import game.ResetAction;
import game.Status;
import game.currency.CoinWallet;
import game.magicalitems.DrinkWater;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements DrinkWater {

	private final Menu menu = new Menu();
	// Reset checker
	public static boolean resetchecker;

	// Player Starts with a Coin Wallet
	public static CoinWallet playerCoinWallet = new CoinWallet();

	// Fireflower
	public static Boolean pickedup=false;
	public static int counter;
	public static Boolean optionshown;

	/**
	 * Constructor.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		resetchecker=true;

		this.addItemToInventory(playerCoinWallet);

		// Testing SpeakAction
//		this.addItemToInventory(new Wrench());
//		this.addCapability(Status.INVINCIBLE);


	}

	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		/**
		 * reset ability
		 * @author Seow Zheng Hao
		 */
		if (resetchecker){
			actions.add(new ResetAction());
		}
//		System.out.println(map.locationOf(this).getItems());

		//check if floor on fire
		FloorOnFire(this,map);

		//fireflower current duration
		if (this.hasCapability(Status.FIRE)){
			System.out.println("Fire attack turns left: "+counter);
//			System.out.println("has status fire!");
			if (counter>0){
				counter-=1;
			} else {
				this.removeCapability(Status.FIRE);
			}
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}

	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	public CoinWallet getPlayerCoinWallet() {
		return playerCoinWallet;
	}

	public void setPlayerCoinWallet(CoinWallet playerCoinWallet) {
		this.playerCoinWallet = playerCoinWallet;
	}

	@Override
	public void healing(int healingHitPoints) {

	}

	@Override
	public void increaseIntrinsicAttackDamage(int points) {

	}

	public void FloorOnFire(Actor actor, GameMap map){
		if (map.locationOf(actor).getGround().getDisplayChar()=='v'){
			actor.hurt(20);
			System.out.println("standing on fire");
		}
	}
}
