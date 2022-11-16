package game;

/**
 *  Monologue holds all the Allies' & Enemies' Sentences
 *  @author Danesh Mariapan
 */
public class Monologue {

    /**
     * Sentences that the Toad can choose to say
     */
    public static final String T1 = "You might need a wrench to smash Koopa's hard shells";
    public static final String T2 = "You better get back to finding the Power Stars";
    public static final String T3 = "The Princess is depending on you! You are our only hope";
    public static final String T4 = "Being imprisoned in these walls can drive a fungus crazy :(";

    /**
     * Sentence that Princess Peach can choose to say
     */
    public static final String PP1 = "Dear Mario, I'll be waiting for you...";
    public static final String PP2 = "Never gonna give you up!";
    public static final String PP3 = "Release me, or I will kick you!";

    /**
     * Sentences that Bowser can choose to say
     */
    public static final String B1 = "What was that sound? Oh, just a fire.";
    public static final String B2 = "Princess Peach! You are formally invited... to the creation of my new kingdom!";
    public static final String B3 = "Never gonna let you down!";
    public static final String B4 = "Wrrrrrrrrrrrrrrrryyyyyyyyyyyyyy!!!!";

    /**
     * Sentences that Goombas can choose to say
     */
    public static final String G1 = "Mugga mugga!";
    public static final String G2 = "Ugha ugha... (Never gonna run around and desert you...)";
    public static final String G3 = "Ooga-Chaka Ooga-Ooga!";

    /**
     * Sentences that Koopas can choose to say
     */
    public static final String K1 = "Never gonna make you cry!";
    public static final String K2 = "Koopi koopi koopii~!";

    /**
     * Sentences that Flying Koopas can choose to say
     */
    public static final String FK1 = "Never gonna make you cry!";
    public static final String FK2 = "Koopi koopi koopii~!";
    public static final String FK3 = "Pam pam pam!";

    /**
     * Sentences that Piranha Plants can choose to say
     */
    public static final String P1 = "Slsstssthshs~! (Never gonna say goodbye~)";
    public static final String P2 = "Ohmnom nom nom nom.";


    /**
     * String List to store all of Toad's Sentences
     */
    private static final String[] TOAD_SENTENCES = {
            T1,
            T2,
            T3,
            T4
    };

    /**
     * Getter for String List of all of Toad's Sentences
     * @return String List
     */
    public static String[] getToadSentences() {
        return TOAD_SENTENCES;
    }

    /**
     * String List to store all of Princess Peach's Sentences
     */
    private static final String[] PRINCESS_PEACH_SENTENCES = {
            PP1,
            PP2,
            PP3
    };

    /**
     * Getter for String List of all of Princess Peach's Sentences
     * @return String List
     */
    public static String[] getPrincessPeachSentences() {
        return PRINCESS_PEACH_SENTENCES;
    }

    /**
     * String List to store all of Bowser's Sentences
     */
    private static final String[] BOWSER_SENTENCES = {
            B1,
            B2,
            B3,
            B4
    };

    /**
     * Getter for String List of all of Bowser's Sentences
     * @return String List
     */
    public static String[] getBowserSentences() {
        return BOWSER_SENTENCES;
    }

    /**
     * String List to store all of Goomba's Sentences
     */
    private static final String[] GOOMBA_SENTENCES = {
            G1,
            G2,
            G3
    };

    /**
     * Getter for String List of all of Goomba's Sentences
     * @return String List
     */
    public static String[] getGoombaSentences() {
        return GOOMBA_SENTENCES;
    }

    /**
     * String List to store all of Koopa's Sentences
     */
    private static final String[] KOOPA_SENTENCES = {
            K1,
            K2
    };

    /**
     * Getter for String List of all of Koopa's Sentences
     * @return String List
     */
    public static String[] getKoopaSentences() {
        return KOOPA_SENTENCES;
    }

    /**
     * String List to store all of Flying Koopa's Sentences
     */
    private static final String[] FLYING_KOOPA_SENTENCES = {
            FK1,
            FK2,
            FK3
    };

    /**
     * Getter for String List of all of Flying Koopa's Sentences
     * @return String List
     */
    public static String[] getFlyingKoopaSentences() {
        return FLYING_KOOPA_SENTENCES;
    }

    /**
     * String List to store all of Piranha Plant's Sentences
     */
    private static final String[] PIRANHA_PLANTS_SENTENCES = {
            P1,
            P2
    };

    /**
     * Getter for String List of all of Piranha Plant's Sentences
     * @return String List
     */
    public static String[] getPiranhaPlantsSentences() {
        return PIRANHA_PLANTS_SENTENCES;
    }


}
