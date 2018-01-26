package items.equipments.weapons;

import items.equipments.Equipment;
import utils.Utils;

public abstract class Weapon extends Equipment {
	private int base;
	private int d;
	private int l;
	
	public static final OffHandWeapon none= new NoWeapon();
	
	public Weapon(String name, int base, int d, int l, boolean fullName) { //meeeh
		super(name);
		this.base=base;
		this.d=d;
		this.l=l;
	}
	
	public Weapon(String name, int base, int d, int l) {
		this(name + " " + base + "+" + d + "d" + l, base, d, l, true);
	}
	
	public String getAttackInfo(){
		return base + "+" + d + "d" + l;
	}
	
	public int getMinAttack(){
		return base;
	}
	
	public int getMaxAttack(){
		return base + d * (l-1);
	}
	
	public int getDmg(){
		return base + Utils.rollDice(d,l);
	}
}