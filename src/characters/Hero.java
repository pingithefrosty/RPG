package characters;

import java.util.HashMap;
import java.util.Map;

import controls.GameLogger;
import items.Inventory;
import items.Item;
import items.equipments.armor.Armor;
import items.equipments.weapons.OffHandWeapon;
import items.equipments.weapons.OneHandedWeapon;
import items.equipments.weapons.Weapon;
import map.Coord;
import map.Direction;
import map.GameMap;
import utils.GameOverException;

public class Hero extends Entity{
	private static final GameLogger LOGGER = new GameLogger(Hero.class);
	private Weapon rightHand;
	private OffHandWeapon leftHand;
	private Map<Armor.Type,Armor> armors;
	private Inventory inv;
	private Coord location;
	private Direction facing;

	
	public Hero(int hp, int att, int def) {
		super("you",hp,att,def,"heroDOWN.png");
		this.rightHand=Weapon.none;
		this.leftHand=Weapon.none;
		this.armors=new HashMap<Armor.Type, Armor>();
		this.inv= new Inventory(30);
		this.inv.add(new OneHandedWeapon("stick", 1));
		location= new Coord(0,0);
		facing=Direction.DOWN;
	}
	
	public boolean unEquipRightHand(){
		if(rightHand==Weapon.none){
			return true;
		}
		Weapon w = rightHand;
		if (!inv.add(w)){
			return false;
		}
		rightHand = Weapon.none;
		LOGGER.gameLog("Unequipped " + w);
		return true;
	}
	
	public boolean unEquipRightHand(Weapon w){
		if(rightHand.equals(w)){
			return unEquipRightHand();
		}
		return false;
	}
	
	public void equipRightHand(Weapon w){
		inv.drop(w);
		if(unEquipRightHand()){
			rightHand=w;
			LOGGER.gameLog("Equipped " + w);
		} else {
			inv.add(w);
		}		
	}
	
	public boolean unEquipLeftHand(){
		if(leftHand==Weapon.none){
			return true;
		}
		OffHandWeapon w = leftHand;
		if (!inv.add(w)){
			return false;
		}
		def-=w.getArmor();
		leftHand = Weapon.none;
		LOGGER.gameLog("Unequipped " + w);
		return true;
	}
	
	public boolean unEquipLeftHand(OffHandWeapon w){
		if(leftHand==w){
			return unEquipLeftHand();
		}
		return false;
	}
	
	public void equipLeftHand(OffHandWeapon w){
		inv.drop(w);
		if (unEquipLeftHand()){
			leftHand=w;
			def+=leftHand.getArmor();
			LOGGER.gameLog("Equipped " + w);
		} else {
			inv.add(w);
		}
	}
	
	public boolean unEquipArmor(Armor.Type type){
		if (armors.containsKey(type)){
			Armor a = armors.get(type);
			if (!inv.add(a)){
				return false;
			}
			def-=a.getArmor();
			armors.remove(type);
			LOGGER.gameLog("Unequipped " + a);
		}
		return true;		
	}
	
	public boolean unEquipArmor(Armor a){
		Armor.Type type=a.getType();
		if (a != null && a.equals(armors.get(type))){
			return unEquipArmor(type);
		}
		return false;		
	}
	
	public void equipArmor(Armor a){
		inv.drop(a);
		Armor.Type type = a.getType();
		if(unEquipArmor(type)){
			armors.put(type, a);
			def+=a.getArmor();
			LOGGER.gameLog("Equipped " + a);
		} else {
			inv.add(a);
		}
	}
	
	public void turn(Direction d){
		facing=d;
		imgPath="hero" + d + ".png";
	}
	
	//TODO abstract to interface, rethink
	public void move(GameMap m, Direction d){
		turn(d);
		Coord to = location.add(d);
		if (m.getTile(to).isTraversable()){
			location=to;
			LOGGER.notice(location.toString());
			m.center(location);
		}
	}
	
	public void use(Item i){
		i.use(this);
	}
	
	public void pickUp(Item i){
		LOGGER.gameLog("Got " + i);
		inv.add(i);
	}
	
	public void pickUp(Item i, int q){
		LOGGER.gameLog("Got " + q + " " + i);
		inv.add(i,q);
	}
	
	public void drop(Item i){
		LOGGER.gameLog("Dropped " + i);
		inv.drop(i);
	}
	
	public void drop(Item i, int q){
		inv.drop(i,q);
	}
	
	public void dropAll(Item i){
		LOGGER.gameLog("Dropped all " + i);
		inv.dropAll(i);
	}
	
	@Override
	public String getDmgRange() {
		int min = att * (rightHand.getMinAttack()+leftHand.getMinAttack());
		int max = att * (rightHand.getMaxAttack()+leftHand.getMaxAttack());
		return min + " - " + max;
	}
	
	@Override
	public int getDmg(){
		return att * (rightHand.getDmg()+leftHand.getDmg());
	}

	@Override
	public void die(){
		LOGGER.gameLog("GAME OVER"); //TODO:not showing due to exception
		throw new GameOverException();
		//https://stackoverflow.com/questions/26361559/general-exception-handling-in-javafx-8
	}
	
	public Weapon getRightHand(){
		return rightHand;
	}
	
	public Weapon getLeftHand() {
		return leftHand;
	}

	public Map<Armor.Type, Armor> getArmors() {
		return armors;
	}

	public Inventory getInv() {
		return inv;
	}
	
	public void setInv(Inventory inv) {
		this.inv = inv;
	}

	public Coord getLocation() {
		return location;
	}

	public Direction getFacing() {
		return facing;
	}

	@Override
	public String getToolTip() {
		return name;
	}
	
	public void getHit(int dmg) {
		int lost = Math.min(0, def-dmg);
		adjustHp(lost);
		LOGGER.gameLog("You got hit for " + dmg + " losing " + lost);
	}
	
	public void hit(Hittable h){
		h.getHit(this, getDmg());
	}
	
	
}
