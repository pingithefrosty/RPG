package map;

import characters.Hittable;
import controls.visuals.Drawable;

public interface MapElement extends Drawable, Hittable{
	boolean isTraversable();
}
