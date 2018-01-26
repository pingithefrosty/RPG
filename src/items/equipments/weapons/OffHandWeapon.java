package items.equipments.weapons;

import characters.Hero;

public abstract class OffHandWeapon extends Weapon {
	private int armor;

	public OffHandWeapon(String name, int base, int d, int l, int armor, boolean fullName) {
		super(name, base, d, l, fullName);
		this.armor=armor;
	}
	
	public OffHandWeapon(String name, int base, int d, int l, int armor) {
		super(name, base, d, l);
		this.armor=armor;
	}

	@Override
	public void equip(Hero h) {
		h.equipLeftHand(this);
	}
	
	@Override
	public void unEquip(Hero h) {
		h.unEquipLeftHand(this);
	}
	
	public int getArmor() {
		return armor;
	}	

}

final class NoWeapon extends OffHandWeapon{
	protected NoWeapon(){
		super ("none",0,0,0,0);
	}

	@Override
	public void equip(Hero h) {
		throw new UnsupportedOperationException();
	}
}
