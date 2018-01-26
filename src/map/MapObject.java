package map;

import characters.Hero;

public class MapObject implements MapElement {
	private String name;
	private String imgPath;
	private boolean traversable;
	
	public static final MapObject TREE = new MapObject("tree", "tree.png", false);
	public static final MapObject ROCK = new MapObject("rock", "rock.png", false);
	public static final MapObject WALL = new MapObject("wall", "wall.png", false);
	public static final MapObject UNCHARTED = new MapObject("uncharted", "uncharted.png", false);
	public static final MapObject EMPTY = new MapObject("empty", "plains.png", true);

	private MapObject(String name,String imgPath, boolean traversable) {
		this.name = name;
		this.imgPath = imgPath;
		this.traversable=traversable;
	}

	@Override
	public String toString() {
		return "|"+ name + "|" ;
	}
	
	@Override
	public String getImgPath() {
		return imgPath;
	}

	@Override
	public boolean isTraversable() {
		return traversable;
	}

	@Override
	public String getToolTip() {
		return name;
	}

	@Override
	public void getHit(Hero h, int dmg) {
		// Nothing happens
	}
	
	
	
		
}
