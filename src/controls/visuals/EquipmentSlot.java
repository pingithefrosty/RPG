package controls.visuals;

import characters.Hero;
import controls.GameLogger;
import items.equipments.Equipment;

public class EquipmentSlot extends InventorySlot {
	private static final GameLogger LOGGER = new GameLogger(EquipmentSlot.class);

	public EquipmentSlot(Hero hero, Equipment eq, String prefix) {
		super(eq, 1, prefix + ": " + (eq != null ? eq.getName() : null));
	    this.setOnMouseReleased(x->{	LOGGER.notice("EqPlace for " + eq + " pressed");
										if (eq != null){
											eq.unEquip(hero);
										}
	    							});
	}

}
