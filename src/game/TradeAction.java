package game;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.items.Item;
import edu.engine.positions.GameMap;
import game.currency.CoinWallet;

/**
 * TradeAction class manages the Action used to Trade with Toad in his Shop of Magical Items
 * Also manages the currency / transactions made after purchases
 * @author Danesh Mariapan
 */
public class TradeAction extends Action {

    private final Item tradeItem;
    private final int price;

    /**
     * @param tradeItem The item available in Toad's shop
     * @param price The price of the Item
     */
    public TradeAction(Item tradeItem, int price) {
        this.tradeItem = tradeItem;
        this.price = price;
    }

    /**
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String - Displays on the Console what the Player has bought and for how much
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int balance = 0;
        int deduct = 0;
        int remainingBalance = 0;
        String result = "You don't have enough coins!";

        for(int i = 0; i < actor.getInventory().size(); i++){

            if(actor.getInventory().get(i) instanceof CoinWallet){
                balance = ((CoinWallet) actor.getInventory().get(i)).displayWalletBalance();
            }
        }

        if(balance >= price){
            actor.addItemToInventory(tradeItem);

            int k = ((CoinWallet) actor.getInventory().get(0)).getHashMap().size() + 1;
            while(deduct < price){
                if(actor.getInventory().get(0) instanceof CoinWallet){

                    if (((CoinWallet) actor.getInventory().get(0)).getHashMap().get(k)!= null){
                        deduct += ((CoinWallet) actor.getInventory().get(0)).getHashMap().get(k).getValue();
                        ((CoinWallet) actor.getInventory().get(0)).getHashMap().remove(k);
                    }
                }
                k--;
            }

            remainingBalance = balance - deduct;
            System.out.println("Player's remaining Wallet Balance is: " + "$" + remainingBalance);
            result = actor + " has purchased " + tradeItem + " for " + "$" + price;
        }

        return result;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return String - The possible chosen Action displayed on the console
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + tradeItem + " for " + "$" + price;
    }


}
