package game.magicalitems;

import edu.engine.items.Item;

/**
 * Key class manages everything to do with the Key Item
 * The Key is held by Bowser and can only be obtained by Killing Bowser - It is used to save Princess Peach
 * @author Danesh Mariapan
 */
public class Key extends Item {

    /**
     * Key Item Attributes
     */
    private static final String NAME = "Key";
    private static final Character DISPLAY_CHARACTER = 'k';
    private static final boolean PORTABLE = true;

    /***
     * Key Item Constructor
     */
    public Key() {
        super(NAME, DISPLAY_CHARACTER, PORTABLE);
    }

}
