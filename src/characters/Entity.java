package characters;

import controls.visuals.Drawable;

public abstract class Entity implements Drawable{
	protected String name;
	protected int maxhp;
	protected int curhp;
	protected int att;
	protected int def;
	protected String imgPath;
	
	public Entity(String name, int hp, int att, int def, String imgPath) {
		this.name=name;
		this.maxhp=hp;
		this.curhp=hp;
		this.att=att;
		this.def=def;
		this.imgPath=imgPath;
	}
	
	public abstract String getDmgRange();
	
	public abstract int getDmg();
	
	public abstract void die();
	
	public boolean adjustHp(int adj){
		if (curhp>=maxhp && 0<adj){
			return false;
		}
		curhp=Math.min(curhp+adj, maxhp);
		if (curhp<=0){
			die();
		}
		return true;
	}
	
	public int getMaxhp() {
		return maxhp;
	}
	
	public int getCurhp() {
		return curhp;
	}

	public String getHpState(){
		return curhp + "/" + maxhp;
	}
	
	public int getAtt() {
		return att;
	}
	
	public int getDef() {
		return def;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String getImgPath() {
		return imgPath;
	}
	
	

}
