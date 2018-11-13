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

public class TurnEndButton implements IRenderable {

	public TurnEndButton() {

	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.BLACK);
		gc.setStroke(Color.YELLOW);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		gc.setLineWidth(3);
		if (Status.turnOwner == 1) {
			gc.fillRect(5, 10, 170, 57);
			gc.strokeRect(5, 10, 170, 57);
			gc.setFill(Color.WHITE);
			gc.fillText("End Turn", 15, 57);
		} else {
			gc.fillRect(768, 10, 170, 57);
			gc.strokeRect(768, 10, 170, 57);
			gc.setFill(Color.WHITE);
			gc.fillText("End Turn", 778, 57);
		}
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

}
