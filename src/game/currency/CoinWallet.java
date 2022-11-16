package game.currency;
import java.util.HashMap;

import edu.engine.items.Item;

/**
 *  Coin Wallet manages everything to do with a Coin values and Player Balance
 *  @author Danesh Mariapan
 */
public class CoinWallet extends Item{

    /**
     * A HashMap, containing pair values of an Integer (noCoins) and the Coin Object
     * that was picked up by the Player
     */
    private HashMap<Integer, Coin> hashMap;
    private int noCoins;

    private static final String NAME = "Player Coin Wallet";
    private static final Character DISPLAY_CHARACTER = 'Q';
    private static final boolean PORTABLE = false;


    /**
     * CoinWallet zero-parameter Constructor
     */
    public CoinWallet(){
        super(NAME, DISPLAY_CHARACTER, PORTABLE);
        this.hashMap = new HashMap<Integer, Coin>();
        this.noCoins = 0;

        // For Testing Trading
        this.addCoin(new Coin(100));
        this.addCoin(new Coin(100));
        this.addCoin(new Coin(100));
        this.addCoin(new Coin(100));
        this.addCoin(new Coin(100));
    }

    /**
     * addCoin Method - Adds Coin object to HashMap
     * @param newCoin Coin Object
     */
    public void addCoin(Coin newCoin){
        // Increase Coin count
        noCoins += 1;
        // Put the Coin into the HashMap
        hashMap.put(noCoins, newCoin);
    }

    /**
     * Displays the current Player's Wallet Balance with number of Coins stored shown
     * @return Player's Balance Integer
     */
    public int displayWalletBalance(){
        int totalWalletBalance = 0;
        int numberCoins = 0;

        if(!hashMap.isEmpty()){
            for(int i = 1; i < hashMap.size() + 1; i++) {
                if ((getHashMap().get(i)) != null){
                    int ownedCoinValue = (getHashMap().get(i).getValue());
                    totalWalletBalance += ownedCoinValue;
                    numberCoins += 1;
                }
            }
        }

        else{
            System.out.println("Wallet is Empty");
        }

        System.out.println("Player's Current Total Wallet Balance is: " + "$" + totalWalletBalance + " \n"
                + "With a total of: " + numberCoins + " Coins, in Player's Wallet");
        return totalWalletBalance;
    }


    // Setters and Getters

    /**
     * HashMap getter
     * @return hashMap
     */
    public HashMap<Integer, Coin> getHashMap() {
        return hashMap;
    }

    /**
     * HashMap setter
     * @param hashMap of <Integer,Coin></Integer,Coin> type
     */
    public void setHashMap(HashMap<Integer, Coin> hashMap) {
        this.hashMap = hashMap;
    }

    /**
     * Getter for Coin ID (order of coins picked up)
     * @return Coin Number Integer
     */
    public int getNoCoins() {
        return noCoins;
    }

    /**
     * Setter for Coin ID (order of coins picked up)
     * @param noCoins Coin Number Integer
     */
    public void setNoCoins(int noCoins) {
        this.noCoins = noCoins;
    }


}
