package utils;

import controls.Main;
import controls.visuals.Drawable;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import map.GameMap;
import map.MapObject;

public class VisualUtils {
	public static ImageView getImageView(Drawable d){
		ImageView img = new ImageView(new Image(d.getImgPath()));
		img.setFitWidth(Main.MHEIGHT/GameMap.VISIBLESIZE);
		img.setFitHeight(Main.MHEIGHT/GameMap.VISIBLESIZE);
		if(!d.equals(MapObject.EMPTY)){
		    Tooltip tooltip = new Tooltip(d.getToolTip());
		    Tooltip.install(img, tooltip);
		}
		return img;
	}

}
