/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package menu;

import drawing.IRenderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import status.InputAssist;

public class MenuOverlay implements IRenderable {

	private int x, y;
	private boolean clicked = false;

	public MenuOverlay() {
		// TODO Auto-generated constructor stub
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int getZ() {
		return 0;
	}

	public void clicked() {
		clicked = true;
	}

	public void released() {
		clicked = false;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setGlobalAlpha(0.7);
		if (inrange(50, 250, 426, 526) && !clicked) {
			gc.setFill(Color.WHITE);
			gc.fillRect(50, 426, 200, 100);
		}
		if (inrange(1024 - 250, 1024 - 50, 426, 526) && !clicked) {
			gc.setFill(Color.WHITE);
			gc.fillRect(1024 - 250, 426, 200, 100);
		}
		gc.setGlobalAlpha(1);
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

	public boolean inrange(int minx, int maxx, int miny, int maxy) {
		return x < maxx && x > minx && y < maxy && y > miny;
	}

}
