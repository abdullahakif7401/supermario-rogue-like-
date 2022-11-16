package game;

import edu.engine.weapons.WeaponItem;

/**
 *  Wrench Class manages everything to do with the Key Item
 *  Wrench is an Item that can be bought from the Toad
 *  @author Danesh Mariapan
 */
public class Wrench extends WeaponItem {

    /**
     * Wrench Item Attributes
     */
    public static final String NAME = "Wrench";
    private static final Character DISPLAY_CHARACTER = 'w';
    private static final int DAMAGE = 50;
    private static final String VERB = "POW";
    private static final int HIT_RATE = 80;

    /**
     * Constructor for Wrench Weapon Item
     */
    public Wrench() {
        super(NAME, DISPLAY_CHARACTER, DAMAGE, VERB, HIT_RATE);
    }


}
