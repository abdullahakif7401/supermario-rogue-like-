package edu.demo.mars.grounds;

import edu.demo.mars.DemoCapabilities;
import edu.engine.positions.Location;
import edu.engine.actors.Actor;
import edu.engine.positions.Ground;

public class Crater extends Ground {
	private int age;

	public Crater() {
		super('o');
		age = 0;
	}

	@Override
	public void tick(Location location){
		age++;
		if(age == 10){
			setDisplayChar('#');
		}else if (age == 20){
			setDisplayChar('&');
		}
	}
	
	@Override
	public boolean canActorEnter(Actor a) {
		return a.hasCapability(DemoCapabilities.SPACETRAVELLER);
	}
}