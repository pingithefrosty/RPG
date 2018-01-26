package map;

public class Tile {
	private MapElement content;
	
	public Tile(MapElement content) {
		this.content = content;
	}

	public static final Tile UNCHARTED=new Tile(MapObject.UNCHARTED);
	
	@Override
	public String toString() {
		return "|"+ content + "|" ;
	}

	
}
