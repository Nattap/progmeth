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

public class Moveindicator implements IRenderable {

	public Moveindicator() {

	}

	@Override
	public int getZ() {
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		if (Status.turnOwner == 0) {
			gc.fillText("Move left : " + Status.moveleft, 15, 57);
		} else {
			gc.fillText("Move left : " + Status.moveleft, 778, 57);
		}
	}

	@Override
	public boolean isDestroyed() {
		return false;
	}

}
