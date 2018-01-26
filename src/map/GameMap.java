package map;

import java.util.HashMap;
import java.util.Map;

import controls.GameLogger;
import utils.Utils;

public class GameMap {
	private static final GameLogger LOGGER = new GameLogger(GameMap.class);
	
	private Map<Coord, MapElement> map;
	private Coord origin;
	
	public static final int VISIBLESIZE = 9;
	private static final int VISIBLEDIST = 2;
	private MapElement[][] visibleMap = new MapElement[VISIBLESIZE][VISIBLESIZE];
	
	public GameMap(Map<Coord, MapElement> map) {
		this.map = new HashMap<Coord, MapElement>(map);
		setTile(0, 0,  MapObject.EMPTY);
		origin=new Coord(0,0);
		generateVisibleMap();
	}
	
	public GameMap() {
		this(new HashMap<Coord, MapElement>());
	}

	public void generateVisibleMap(){
		revealTiles(origin);
		int bx = origin.getX()-VISIBLESIZE/2;
		int by = origin.getY()+VISIBLESIZE/2;
		
		for(int i =0; i<VISIBLESIZE;++i){
			for(int j=0; j<VISIBLESIZE;++j){
				visibleMap[i][j]=getTile(bx+i,by-j);
			}
		}
	}
	
	private void revealTiles(Coord c){
		int ox = c.getX();
		int oy= c.getY();
		for(int i =-VISIBLEDIST; i<=VISIBLEDIST;++i){
			for(int j =-VISIBLEDIST; j<=VISIBLEDIST;++j){
				if(!hasTile(ox+i,oy+j)){
					setTile(ox+i,oy+j,Utils.genRandMapElement());
				}
			}
		}
	}
	
	public void center(Coord c){
		LOGGER.notice("Centering map on " + c);
		origin=c;
		generateVisibleMap();
	}
	
	public MapElement getTile(Coord c){
		return map.getOrDefault(c, MapObject.UNCHARTED);
	}
	
	public MapElement getTile(int x, int y){
		return getTile(new Coord(x,y));
	}
	
	private void setTile(Coord c, MapElement t){
		map.put(c,t);
	}
	
	private void setTile(int x, int y, MapElement t){
		setTile(new Coord(x,y), t);
	}
	
	public void forceSetTile(Coord c, MapElement t){
		setTile(c, t);
		generateVisibleMap(); //TODO: only update coord c from visibleMap
	}
	
	private boolean hasTile(Coord c){
		return map.containsKey(c);
	}
	
	private boolean hasTile(int x, int y){
		return hasTile(new Coord(x,y));
	}

	public MapElement[][] getVisibleMap() {
		return visibleMap;
	}
	
	//TODO Coord<->array element
}
//visibleMap should be iterated over like this
//foreach-type iteration transposes the matrix
//for(int j=0; j<7; j++){
//	for (int i =0; i<7; ++i){
//		System.out.print(m.getVisibleMap()[i][j]);
//	}
//	System.out.println("");
//}
