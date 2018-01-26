package items.equipments.weapons;

public class Dagger extends OffHandWeapon {
	private static int D=4;
	private static int L=2;

	public Dagger(String name, int base, int parry) {
		super(name + " p" + parry, base, D, L, parry);
		//parry should be small
	}

}
