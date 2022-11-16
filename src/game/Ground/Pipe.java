package game.Ground;

import edu.engine.positions.Ground;
import game.Resettable;

public class Pipe extends Ground implements Resettable {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Pipe(char displayChar) {
        super(displayChar);
    }

    @Override
    public void resetInstance() {

    }

    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
