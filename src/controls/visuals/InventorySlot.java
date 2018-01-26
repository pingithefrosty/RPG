package controls.visuals;

import characters.Hero;
import items.Item;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class InventorySlot extends VBox {
	private Item item;
	private int quantity;
	
	public InventorySlot(Hero hero, Item item, int quantity, String displText){
		//https://docs.oracle.com/javase/8/javafx/api/javafx/scene/image/ImageView.html
		this.item = item;
		this.quantity = quantity;
		if(item!=null){
		    Tooltip tooltip = new Tooltip(item.getName());
		    Tooltip.install(this, tooltip);
		}
	    this.getChildren().add(new Text(displText));
	}

}
