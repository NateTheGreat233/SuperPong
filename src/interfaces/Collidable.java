package interfaces;

import java.awt.Rectangle;

public interface Collidable {

	public void onCollision(Collidable other);
	public Rectangle getBounds();
	
}
