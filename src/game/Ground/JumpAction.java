package game.Ground;

import edu.engine.actions.Action;
import edu.engine.actors.Actor;
import edu.engine.positions.GameMap;
import edu.engine.positions.Ground;
import edu.engine.positions.Location;
import game.Status;

import java.util.Random;

/**
 * Class that manages the choice for a player to jump to higher ground
 *
 * @author Seow Zheng Hao
 */
public class JumpAction extends Action {

    double probability;
    String directionintereseted;
    Location targetlocation;
    Boolean jumpoutcome;
    Ground targetground;
    int x;
    int y;
    int damage;

    /**
     * Constructor
     *
     * @param location location of the jump target
     * @param direction direction of the jump target from the actor location
     */
    public JumpAction(Location location,String direction) {
        this.targetlocation=location;
        this.directionintereseted=direction;
        this.jumpoutcome=false;

    }

    /**
     * <p>
     *     Method that runs when an acotr chooses the jump option
     * </p>
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String containing description of the jump's success or failure
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //sets the target ground type and x/y coordinates
        this.targetground=targetlocation.getGround();
        x=targetlocation.x();
        y=targetlocation.y();

        //generate random chance
        Random random=new Random();
        this.probability=random.nextDouble();

        //check if player ate super mushroom
        if (actor.hasCapability(Status.TALL)){
            successfuljump(actor,map);
            System.out.println("Your "+actor+" got superpowers");
        } else {
            //check jump target
            char groundchar = this.targetground.getDisplayChar();
            if (groundchar == '#') { //target is wall
                System.out.println("Wall("+x+","+y+")");
                this.wall(this.probability, actor, map);
            } else if (groundchar == '+' || groundchar == 't' || groundchar == 'T') { //target is tree
                System.out.println("Tree("+x+","+y+")");
                this.tree(probability, groundchar, actor, map);
            }
        }
        //////System.out.println("conscious? "+actor.isConscious());]

        //check for successful jump
        if (this.jumpoutcome){
            return "Your "+actor+" succeeded for the first time in their life and jumped to location ("+x+","+y+")";
        } else{
            return "Your "+actor+" is the living embodiment of failure and remains at location ("+x+","+y+") after taking "+damage+" damage.";
        }
    }

    /**
     * <p>
     *     Method that is called by the application to show a description of the option
     * </p>
     *
     * @param actor The actor performing the action.
     * @return String containing a description of the jump action
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Jump to climbable object "+this.directionintereseted;
    }

    /**
     * <p>
     *     Method that is called when the jump target is a wall
     * </p>
     *
     * @param probability probablity of success
     * @param actor the actor that is doing the jump action
     * @param map the map that the jump is being done on
     */
    public void wall(Double probability, Actor actor,GameMap map){
        //determine if jump action is successful
        if (probability<=0.8){ //successful jump
            successfuljump(actor,map);
        } else { //unsuccessful
            uncessfuljump(actor,20);
        }
    }

    /**
     * <p>
     *     Method that is called when the jump target is a tree
     * </p>
     *
     * @param probability probablity of success
     * @param groundchar character of the target ground
     * @param actor the actor that is doing the jump action
     * @param map the map that the jump is being done on
     */
    public void tree(Double probability,char groundchar, Actor actor,GameMap map){
        //directs to corresponding tree target
        if (groundchar=='+'){
            sprout(probability,actor,map);
        } else if (groundchar=='t'){
            sapling(probability,actor,map);
        } else if (groundchar=='T'){
            mature(probability,actor,map);
        }
    }

    /**
     * <p>
     *     Method that is called by the tree method when the jump target is a tree in sprout stage
     * </p>
     *
     * @param probability probablity of success
     * @param actor the actor that is doing the jump action
     * @param map the map that the jump is being done on
     */
    public void sprout(Double probability,Actor actor,GameMap map){
        if (probability<0.9){ //success
            successfuljump(actor,map);
        } else{ //actor takes damage
            uncessfuljump(actor,10);
        }
    }

    /**
     * <p>
     *     Method that is called by the tree method when the jump target is a tree in sapling stage
     * </p>
     *
     * @param probability probablity of success
     * @param actor the actor that is doing the jump action
     * @param map the map that the jump is being done on
     */
    public void sapling(Double probability,Actor actor,GameMap map){
        if (probability<0.8){ //success
            successfuljump(actor,map);
        } else{//actor takes damage
            uncessfuljump(actor,20);
        }
    }

    /**
     * <p>
     *     Method that is called by the tree method when the jump target is a tree in mature stage
     * </p>
     *
     * @param probability probablity of success
     * @param actor the actor that is doing the jump action
     * @param map the map that the jump is being done on
     */
    public void mature(Double probability,Actor actor,GameMap map){
        if (probability<0.7){ //success
            successfuljump(actor,map);
        } else{//actor takes damage
            uncessfuljump(actor,30);
        }
    }

    /**
     * <p>
     *     Method that is called when the jump is successful to move the actor
     * </p>
     *
     * @param actor the actor that is doing the jump action
     * @param map the map that the jump is being done on
     */
    public void successfuljump(Actor actor,GameMap map){
        //changes jumpoutcome to true and moves actor to target location
        this.jumpoutcome=true;
        map.moveActor(actor,this.targetlocation);
    }

    /**
     * <p>
     *     Method that is called when the jump is unsuccessful to deal damage to the actor
     * </p>
     *
     * @param actor the actor that is doing the jump action
     * @param value the integer value as damage done to actor
     */
    public void uncessfuljump(Actor actor,int value){
        //deals damage to actor for failing jump
        this.damage=value;
        actor.hurt(value);
    }
}
