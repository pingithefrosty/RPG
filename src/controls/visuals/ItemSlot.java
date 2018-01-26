package controls.visuals;

import characters.Hero;
import controls.GameLogger;
import items.Item;
import javafx.scene.input.MouseButton;

public class ItemSlot extends InventorySlot {
	private static final GameLogger LOGGER = new GameLogger(ItemSlot.class);

	public ItemSlot(Hero hero, Item item, int quantity) {
		super(item, quantity, quantity + "x " + item.getName());
	    this.setOnMouseReleased(x->{	if (x.getClickCount()!=2) return;
	    								if (x.getButton().equals(MouseButton.PRIMARY)){
		    								LOGGER.notice("Place for " + item + " left clicked");
											hero.use(item);
	    								} else if (x.getButton().equals(MouseButton.SECONDARY)){
	    									LOGGER.notice("Place for " + item + " right clicked");
	    									if (x.isShiftDown()){
	    										hero.dropAll(item);
	    									} else {
	    										hero.drop(item);
	    									}
	    								}
								});
	}

}
