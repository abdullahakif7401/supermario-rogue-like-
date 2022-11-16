package edu.demo.mars.items;

import edu.engine.actions.Action;
import edu.engine.items.Item;

public class MartianItem extends Item{

	public MartianItem(String name, char displayChar, boolean portable) {
		super(name, displayChar, portable);
	}

	public void addSampleAction(Action newAction){
		this.addAction(newAction);
	}

}
