package controls.visuals;

import items.Item;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class InventorySlot extends VBox {
	private Item item; //might not needed to save these, we'll see
	private int quantity;
	
	public InventorySlot(Item item, int quantity, String displText){
		this.item = item;
		this.quantity = quantity;
		if(item!=null){
		    Tooltip tooltip = new Tooltip(item.getName());
		    Tooltip.install(this, tooltip);
		}
	    this.getChildren().add(new Text(displText));
	}

}
