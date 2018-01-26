package items.equipments;

import characters.Hero;
import items.Item;

public abstract class Equipment extends Item implements Equippable {

	public Equipment(String name) {
		super(name);
	}

	@Override
	public void use(Hero h) {
		equip(h);
		
	}

	@Override
	abstract public void equip(Hero h);

}
