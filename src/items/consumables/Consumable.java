package items.consumables;

import characters.Hero;
import items.Item;

public abstract class Consumable extends Item implements ConsumableI {

	public Consumable(String name) {
		super(name);
	}

	@Override
	abstract public void consume(Hero h);

	@Override
	public void use(Hero h) {
		consume(h);
	}

}
