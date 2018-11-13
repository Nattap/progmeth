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

public class Comboindicator implements IRenderable {

	public Comboindicator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public void draw(GraphicsContext gc) {
		if (Status.totalCombo() > 1) {
			gc.setFill(Color.WHITE);
			gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 70));
			gc.setLineWidth(3);
			gc.setStroke(Color.BLACK);
			if (Status.totalCombo() >= 5) {
				gc.setStroke(Color.YELLOW);
				if (Status.totalCombo() >= 10) {
					gc.setStroke(Color.ORANGE);
					if (Status.totalCombo() >= 20) {
						gc.setStroke(Color.AQUA);
					}
				}
			}
			gc.strokeText(Integer.toString(Status.totalCombo()) + " Combo  +"
					+ Integer.toString((Status.totalCombo() - 1) * 5) + "%", 15, 571);
			gc.fillText(Integer.toString(Status.totalCombo()) + " Combo  +"
					+ Integer.toString((Status.totalCombo() - 1) * 5) + "%", 15, 571);
		}
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
