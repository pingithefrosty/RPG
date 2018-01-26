package items;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controls.GameLogger;

public class Inventory {
	private static final GameLogger LOGGER = new GameLogger(Inventory.class);
	
	private Map<Item,Integer> inv;
	private int size;
	
	public Inventory(int size){
		this.size=size;
		this.inv=new HashMap<Item, Integer>();
	}
	
	private void adjust (Item i, int q){
		inv.put(i, inv.get(i)+q);
	}
	
	public boolean canAdd(Item i){
		boolean can = inv.containsKey(i) || inv.size()<size;
		if (!can) {
			LOGGER.gameLog("INVENTORY FULL");
		}
		return can;
	}
	
	public boolean add(Item i, int q){
		if (!canAdd(i)){
			return false;
		}
		if (inv.containsKey(i)){
			adjust(i,q);
		} else {
			inv.put(i, q);
			if (inv.size()>=size){
				LOGGER.gameLog("INVENTORY GOT FULL");
			}
		}
		return true;
	}
	
	public boolean add(Item i){
		return add(i,1);
	}
	
	public void drop(Item i, int q){
		if(!inv.containsKey(i)){
			return;
		}
		int cur = inv.get(i);
		if(cur<=q){
			inv.remove(i);
		} else {
			adjust(i,-q);
		}
	}
	
	public void drop(Item i){
		drop(i,1);
	}
	
	public void dropAll(Item i){
		drop(i,inv.getOrDefault(i, 0));
	}
	
	public List<Item> getItemList(){		
		List<Item> l = new ArrayList<Item>(inv.keySet());
		Collections.sort(l);
		return l;
	}
	
	public int getQuantity(Item i){		
		return inv.getOrDefault(i,0);
	}

	@Override
	public String toString() {
		return "Inventory [inv=" + inv + ", size=" + inv.size() +"/" + size + "]";
	}
}
