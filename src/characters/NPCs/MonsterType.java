package characters.NPCs;

public enum MonsterType {
	goblin(20, 2, 2, "goblin.png", 1, 3), dragon(40, 5, 3, "dragon.png", 2, 4);
	
	private int baseHp;
	private int baseAtt;
	private int baseDef;
	private String imgPath;
	private int d;
	private int l;
	
	private MonsterType(int baseHp, int baseAtt, int baseDef, String imgPath, int d, int l) {
		this.baseHp = baseHp;
		this.baseAtt = baseAtt;
		this.baseDef = baseDef;
		this.imgPath = imgPath;
		this.d = d;
		this.l = l;
	}

	public int getBaseHp() {
		return baseHp;
	}

	public int getBaseAtt() {
		return baseAtt;
	}

	public int getBaseDef() {
		return baseDef;
	}

	public String getImgPath() {
		return imgPath;
	}

	public int getD() {
		return d;
	}

	public int getL() {
		return l;
	}
	
	
}
