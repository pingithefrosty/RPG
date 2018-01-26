package items.equipments.weapons;

public class Shield extends OffHandWeapon {

	public Shield(String name, int knockback, int armor) {
		super(name + " " + armor + " d" + knockback, knockback, 0, 0, armor, true);
		// no spread in dmg, armor should be big
	}

}
