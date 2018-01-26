package utils;

import java.util.Random;

import characters.NPCs.Monster;
import characters.NPCs.MonsterType;
import items.Item;
import items.consumables.HealthItem;
import items.equipments.armor.Armor;
import items.equipments.weapons.Dagger;
import items.equipments.weapons.OneHandedWeapon;
import items.equipments.weapons.Shield;
import map.MapElement;
import map.MapObject;

public class Utils {

	public static int rollDice(int n, int l){
		Random r = new Random();
		int o = 0;
		for(int i=0; i<n; ++i){
			o+=r.nextInt(l);
		}
		return o;
	}

	public static int rollDice(int l){
		return rollDice(1, l);
	}
	
	//TODO: enhance
	public static MapElement genRandMapElement(){
		switch (rollDice(30)){
		case 0: return MapObject.TREE;
		case 1: return MapObject.WALL;
		case 2: return MapObject.ROCK;
		case 3: return getRandMonster();
		default: return MapObject.EMPTY;
		}
	}
	
	public static Monster getRandMonster(){
		MonsterType mt;
		switch (rollDice(10)){
		case 0: mt = MonsterType.dragon; break;
		default: mt = MonsterType.goblin;
		}
		return Monster.getMonsterFromType(mt);
	}
	
	public static Item getRandItem(int lvl){
		switch (rollDice(14)){
		case 0: return getRandOneHand(lvl);
		case 1: return new Shield("shield", lvl, rollDice(lvl, 1+rollDice(3*lvl)));
		case 2: return new Dagger("dagger",rollDice(lvl, 1+rollDice(2*lvl)),lvl);
		case 3: return getRandArmor(Armor.Type.BOOTS,lvl);
		case 4: return getRandArmor(Armor.Type.LEG,lvl);
		case 5: return getRandArmor(Armor.Type.CHEST,lvl);
		case 6: return getRandArmor(Armor.Type.GLOVES,lvl);
		case 7: return getRandArmor(Armor.Type.HELM,lvl);
		default: return getRandPotion(lvl);
		}
	}
	
	private static Armor getRandArmor(Armor.Type t, int lvl){
		return new Armor(t.toString().toLowerCase(), t, rollDice(lvl, 1+rollDice(3*lvl)));
	}
	
	private static OneHandedWeapon getRandOneHand(int lvl){
		return new OneHandedWeapon(new String[]{"sword", "axe", "mace"}[rollDice(3)], rollDice(lvl, 1+rollDice(4*lvl)));
	}
	
	private static HealthItem getRandPotion(int lvl){
		int r = lvl*rollDice(10);
		if (r<10) return HealthItem.SMALLPOTION;
		if (r<30) return HealthItem.MEDPOTION;
		return HealthItem.LARGEPOTION;
	}
}
