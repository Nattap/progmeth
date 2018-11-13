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
import javafx.scene.text.FontPosture;
import status.Status;

public class WildSelect implements IRenderable {

	public WildSelect() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getZ() {
		return 1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		gc.fillText("Wild Card", 260, 57);
		gc.drawImage(RenderableHolder.orbatk1, 430 + 10, 22, 30, 30);
		gc.drawImage(RenderableHolder.orbatk2, 470 + 10, 22, 30, 30);
		gc.drawImage(RenderableHolder.orbatk3, 510 + 10, 22, 30, 30);
		gc.drawImage(RenderableHolder.orbrec1, 550 + 10, 22, 30, 30);
		gc.drawImage(RenderableHolder.orbrec2, 590 + 10, 22, 30, 30);
		gc.drawImage(RenderableHolder.orbrec3, 630 + 10, 22, 30, 30);
		gc.drawImage(RenderableHolder.orbmag, 670 + 10, 22, 30, 30);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
