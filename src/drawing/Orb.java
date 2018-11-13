/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import status.Status;

public class Orb implements IRenderable {

	private Image sprite;
	private int x, y, opacity;
	private int identity;

	public Orb(Image sprite, int x, int y, int orb) {
		this.sprite = sprite;
		this.x = x;
		this.y = y;
		this.opacity = 100;
		this.identity = orb;
	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setGlobalAlpha(opacity / 100.0);
		if (this.y < 17) {
			return;
		}
		if (this.y < 72) {
			WritableImage cropped = new WritableImage(this.sprite.getPixelReader(), 0, 72 - this.y, 56,
					56 - 72 + this.y);
			if (Status.phase == 2 || Status.phase == 1) {
				WritableImage theother = new WritableImage(this.sprite.getPixelReader(), 0, 0, 56, 72 - this.y);
				gc.drawImage(theother, x, 576 - (72 - this.y));
			}
			gc.drawImage(cropped, x, 72);
			return;
		}
		if (this.y > 520) {
			WritableImage cropped = new WritableImage(this.sprite.getPixelReader(), 0, 0, 56, 576 - this.y);
			WritableImage theother = new WritableImage(this.sprite.getPixelReader(), 0, 576 - this.y, 56,
					56 - (576 - this.y));
			gc.drawImage(theother, x, 73);
			gc.drawImage(cropped, x, y);
			return;
		}
		if (this.x < 260) {
			WritableImage cropped = new WritableImage(this.sprite.getPixelReader(), 260 - this.x, 0, 56 - 260 + this.x,
					56);
			WritableImage theother = new WritableImage(this.sprite.getPixelReader(), 0, 0, 260 - this.x, 56);
			gc.drawImage(theother, 764 - 260 + this.x, y);
			gc.drawImage(cropped, 260, y);
			return;
		}
		if (this.x > 708) {
			WritableImage cropped = new WritableImage(this.sprite.getPixelReader(), 0, 0, 764 - this.x, 56);
			WritableImage theother = new WritableImage(this.sprite.getPixelReader(), 764 - this.x, 0,
					56 - (764 - this.x), 56);
			gc.drawImage(theother, 260, y);
			gc.drawImage(cropped, x, y);
			return;
		}
		gc.drawImage(sprite, x, y);
	}

	public int getidentity() {
		return this.identity;
	}

	@Override
	public boolean isDestroyed() {
		return this.opacity < 1;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getOpacity() {
		return this.opacity;
	}

	public void dying() {
		this.opacity -= 5;
	}

	public void instakill() {
		this.opacity = 0;
	}
}
