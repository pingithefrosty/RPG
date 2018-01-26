package items.equipments.weapons;

import characters.Hero;

public class OneHandedWeapon extends Weapon {
	private static int D=3;
	private static int L=3;
	
	public OneHandedWeapon(String name, int base) {
		super(name, base, D, L);
	}

	@Override
	public void equip(Hero h) {
		h.equipRightHand(this);
	}
	
	@Override
	public void unEquip(Hero h) {
		h.unEquipRightHand(this);
	}
	
	

}
