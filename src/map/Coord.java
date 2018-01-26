package map;

public class Coord {
	int x;
	int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Coord add(Direction d){
		switch(d){
		case UP: return new Coord(x,y+1);
		case DOWN: return new Coord(x,y-1);
		case LEFT: return new Coord(x-1,y);
		case RIGHT: return new Coord(x+1,y);
		default: return this; //should not happen
		}
	}
	
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	
	
	
}
