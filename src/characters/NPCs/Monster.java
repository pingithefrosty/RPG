package characters.NPCs;

import characters.Entity;
import characters.Hero;
import controls.GameLogger;
import map.MapElement;
import utils.MonsterDiedException;
import utils.Utils;

public class Monster extends Entity implements MapElement {
	private static final GameLogger LOGGER = new GameLogger(Monster.class);
	
	private int id;
	private int lvl;
	private int d;
	private int l;
	
	private static int COUNTER=0;
	
	public Monster(String name, int lvl, int hp, int att, int def, String imgPath,int d,int l) {
		super(name, hp, att, def, imgPath);
		this.lvl = lvl;
		this.d=d;
		this.l=l;
		this.id=COUNTER++;
	}
	
	@Override
	public boolean isTraversable() {
		return false;
	}

	@Override
	public String getDmgRange() {
		return att + "-" + (att + d * (l-1));
	}

	@Override
	public int getDmg(){
		return att + Utils.rollDice(d,l);
	}

	@Override
	public void die() {
		LOGGER.gameLog(toString() + " died");
		throw new MonsterDiedException();
	}
	
	public void hit(Hero h){
		h.getHit(getDmg());
	}
	
	@Override
	public void getHit(Hero h, int dmg) {
		int lost = Math.min(0, def-dmg);
		try {
			adjustHp(lost);
			LOGGER.gameLog(toString() + " got hit for " + dmg + " losing " + lost + " health. Remaining: " + getHpState());
		} catch (MonsterDiedException e) {
			h.pickUp(Utils.getRandItem(lvl));
			throw e;
		}			
		LOGGER.info(toString() + " retaliating");
		hit(h);
	}

	@Override
	public String getToolTip() {
		return toString() + " hp:" + getHpState() +  " att:" + att + "+" + d + "d" + l + " dmg:" + getDmgRange() + " def:" + def;
	}
	
	@Override
	public String toString() {
		return name + "(" + lvl + ") [" + id + "]";
	}
	
	public int getLvl() {
		return lvl;
	}

	public static Monster getMonsterFromType(MonsterType mt) {
		int lvl = (int) Math.floor(Math.log10(COUNTER+1));
		int d = mt.getD();
		int l = mt.getL();
		int bh = mt.getBaseHp();
		int ba = mt.getBaseAtt();
		int bd = mt.getBaseDef();
		
		return new Monster(mt.toString(), d*(lvl+1), lvl*bh + Utils.rollDice(bh, l), lvl*ba + Utils.rollDice(ba, l), lvl*bd + Utils.rollDice(bd, l-1), mt.getImgPath(), d, l);
	}	


}
