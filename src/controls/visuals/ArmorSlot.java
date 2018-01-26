package controls.visuals;

import characters.Hero;
import controls.GameLogger;
import items.equipments.armor.Armor;

public class ArmorSlot extends EquipmentSlot {
	private static final GameLogger LOGGER = new GameLogger(ArmorSlot.class);
	Armor.Type type;
	public ArmorSlot(Hero hero, Armor armor, Armor.Type type) {
		super(hero, armor, type.toString());
		this.type=type;
	    this.setOnMouseReleased(x->{	LOGGER.notice("EqPlace for " + armor + " pressed");
										hero.unEquipArmor(type);
									});
	}

}
