package game.Ground;

import edu.engine.actions.ActionList;
import edu.engine.actors.Actor;
import edu.engine.positions.Ground;
import edu.engine.positions.Location;
import game.Resettable;
import game.currency.Coin;
import game.enemies.FlyingKoopa;
import game.enemies.Goomba;
import game.enemies.Koopa;
import game.magicalitems.FireFlower;

import java.util.ArrayList;
import java.util.Random;

/**
 * Tree class manages everything there is for a tree in the game
 *
 * @author Seow Zheng Hao
 */
public class Tree extends Ground implements Resettable {
    TreeTypes treeState;
    int tickcount;
    Location location;
    Boolean resetchecker;
    /**
     * Constructor.
     *
     */
    public Tree() {
        super('+');
        treeState=TreeTypes.SPROUT;
        tickcount=0;
        this.registerInstance();
        resetchecker=false;
    }

    /**
     *<p>
     *     Returns a boolean to show whether an actor can enter
     *</p>
     *
     * @param actor the Actor to check
     * @return true if actor can enter,false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.getDisplayChar()=='F'){
            return true;
        } else {
            return false;
        }
    }

    /**
     * <p>
     *     Returns all the available actions when this class is in an exit
     * </p>
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return ActionList containing list of possible actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (actor.getDisplayChar()=='m') {//check if actor is mario player
            if (location.containsAnActor()==false){//check that location doesnt have another acotr
                actions.add(new JumpAction(location, direction));
            }
        }
        return actions;
    }

    /**
     * <p>
     *     Returns the number of turns this entity has existed
     * </p>
     *
     * @return an integer
     */
    public int getTick() {
        return tickcount;
    }

    /**
     * <p>
     *     Adds one to this entity's existance counter (lets the class feel the flow of time/turns)
     * </p>
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
//        for (int i=0;i<location.getExits().size();i++){
//            System.out.println(location.getExits().get(i).getName());
//        }
//        System.out.println(location.getExits().size());

        this.location=location;
        if (treeState==TreeTypes.DEAD){
            return;
        }
        if (resetchecker==true){
            location.setGround(new Dirt());
        }

        //create random double for chance
        Random random=new Random();
        double probability=random.nextDouble();
        this.tickcount+=1;
        //System.out.println(this.getTick());
        //System.out.println(this.location);

        if (tickcount<=10){ //sprout
            sprout(probability,location);

            if (tickcount==1 || tickcount==10){ //growing stage (sprout)(sprout->sapling)
                fireflower(probability,location);
            }
        } else if (tickcount<=20){ //sapling
            sapling(probability,location);
            treeState=TreeTypes.SAPLING;
            super.setDisplayChar('t');

            if (tickcount==20){ //growing stage (sapling->mature)
                fireflower(probability,location);
            }
        } else { //mature
            mature(probability,location);
            treeState=TreeTypes.MATURE;
            super.setDisplayChar('T');
        }
    }

    /**
     * <p>
     *     Method called every turn the tree is in the sprout life stage
     * </p>
     *
     * @param probability chance of success
     * @param location location of the tree on the map
     */
    public void sprout(double probability, Location location){
        if (probability<=0.1 & !location.containsAnActor()){ //10% chance to spawn goomba
            location.addActor(new Goomba());
        }
    }

    /**
     * <p>
     *  Method called every turn the tree is in the sapling life stage
     * </p>
     *
     * @param probability chance of success
     * @param location location of the tree on the map
     */
    public void sapling(double probability, Location location){
        if (tickcount>10 & tickcount<=20){ //sapling stage
            treeState=TreeTypes.SAPLING;
            if (probability<=0.1){ //10% chance to spawn $20 coin
                location.addItem(new Coin(20));
            }
        }
    }

    /**
     * <p>
     *     Method called every turn the tree is in the mature life stage
     * </p>
     *
     * @param probability chance of success
     * @param location location of the tree on the map
     */
    public void mature(double probability, Location location){
        if (this.tickcount>=20){ //mature stage
            treeState=TreeTypes.MATURE;
            if (probability<=0.15 & !location.containsAnActor()){ //15% chance to spawn koopa
                Random random=new Random();
                double probability2=random.nextDouble();
                if (probability2<=0.5){
                    location.addActor(new Koopa());
                } else{
                    location.addActor(new FlyingKoopa());
                }
            }

            if (this.tickcount%5==0){ //turn tiles around it
                grownew(location);
            }
            //System.out.println(probability);
            if (probability<=0.2){ //chance to wither
                treeState=TreeTypes.DEAD;
                super.setDisplayChar('.');
                location.setGround(new Dirt());
                //System.out.println("dead: "+location.x()+" "+location.y());
            }
        }
    }

    public void fireflower(Double probability, Location location){
        if (probability<=0.5){
            location.addItem(new FireFlower("Fire Flower",'f',false));
        }
    }

    /**
     * <p>
     *     Method called when a mature tree can grow new trees in one of the fertile ground around it
     * </p>
     *
     * @param location location of the tree on the map
     */
    public void grownew(Location location){
        ArrayList<Location> fertileLocations=new ArrayList<>();

        for (int i=0;i<location.getExits().size();i++){ //adds all possible exits with fertile ground into arraylist
            if (location.getExits().get(i).getDestination().getDisplayChar()=='.'){
                fertileLocations.add(location.getExits().get(i).getDestination()); //adds location class of the location to arraylist
            }
        }
        if (fertileLocations.size()==0){ //if no fertile ground around, cant grow
            return;
        }
        Random random=new Random();
        int index=random.nextInt(fertileLocations.size());
        fertileLocations.get(index).setGround(new Tree());
        //System.out.println("new tree at:"+fertileLocations.get(index).x()+" "+fertileLocations.get(index).y());
    }

    /**
     * <p>
     *     Method called when the player chooses the resetgame action
     * </p>
     *
     */
    @Override
    public void resetInstance() {
        Random random=new Random();
        double probability=random.nextDouble();
        if (probability>0.5) { //chance to wither
            treeState = TreeTypes.DEAD;
            super.setDisplayChar('.');
            resetchecker=true;
        }
    }

    /**
     * <p>
     *     Method called in the constructor to add this class as a resettable class
     * </p>
     */
    @Override
    public void registerInstance() {
        Resettable.super.registerInstance();
    }
}
