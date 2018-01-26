package items.equipments.armor;

import characters.Hero;
import items.equipments.Equipment;

public class Armor extends Equipment {
	
	public enum Type {CHEST, LEG, HELM, GLOVES, BOOTS};
	
	private int armor;
	private Armor.Type type;

	public Armor(String name, Armor.Type type, int armor) {
		super(name + " " + armor);
		this.type=type;
		this.armor=armor;
	}

	@Override
	public void equip(Hero h) {
		h.equipArmor(this);
	}
	
	@Override
	public void unEquip(Hero h) {
		h.unEquipArmor(this);
	}

	public int getArmor() {
		return armor;
	}

	public Armor.Type getType() {
		return type;
	}
	
	@Override
	public String toString() {
		return type + "-" + super.toString();
	}

}
