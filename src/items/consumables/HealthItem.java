package items.consumables;

import characters.Hero;

public class HealthItem extends Consumable {
	
	private int hp;
	
	public static HealthItem SMALLPOTION = new HealthItem("smallHpPot", 5);
	public static HealthItem MEDPOTION = new HealthItem("medHpPot", 10);
	public static HealthItem LARGEPOTION = new HealthItem("largeHpPot", 20);
	public static HealthItem POISON = new HealthItem("poison", -5);
	

	public HealthItem(String name, int hp) {
		super(name);
		this.hp=hp;
	}

	@Override
	public void consume(Hero h) {
		if(h.adjustHp(hp)){
			h.drop(this);
		}		
	}

}
