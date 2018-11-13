/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Damage implements IRenderable {

	private int x, y;
	private double opacity;
	private String amount;
	private boolean damage;

	public Damage(int location, int amount, boolean damage) {
		this.x = 100 + 764 * (location / 3);
		this.y = 112 + 80 * (location % 3);
		this.damage = damage;
		this.amount = Integer.toString(amount);
		this.opacity = 1;
		RenderableHolder.getInstance().addlater(this);
	}

	@Override
	public int getZ() {
		return 1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setGlobalAlpha(opacity);
		gc.setFill(Color.WHITE);
		if (damage) {
			gc.setStroke(Color.RED);
		} else {
			gc.setStroke(Color.GREEN);
		}
		gc.setLineWidth(2);
		gc.setFont(Font.font("Eras ITC", 50));
		gc.fillText(amount, x, y);
		gc.strokeText(amount, x, y);
		this.y -= 1;
		this.opacity -= 0.015;
	}

	@Override
	public boolean isDestroyed() {
		if (opacity <= 0)
			return true;
		return false;
	}

}
