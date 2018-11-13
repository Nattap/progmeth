/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package drawing;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import status.Status;

public class Skillbar implements IRenderable {

	private int p1display = 0, p2display = 0;

	public Skillbar() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public void draw(GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (p1display < Status.magic[0]) {
			p1display += 1;
			if (p1display % 100 == 0) {
				RenderableHolder.charge.play();
			}
			if (p1display > Status.magic[0]) {
				p1display = Status.magic[0];
			}
		}
		if (p1display > Status.magic[0]) {
			p1display -= 1;
			if (p1display < Status.magic[0]) {
				p1display = Status.magic[0];
			}
		}
		if (p2display < Status.magic[1]) {
			p2display += 1;
			if (p2display % 100 == 0) {
				RenderableHolder.charge.play();
			}
			if (p2display > Status.magic[1]) {
				p2display = Status.magic[1];
			}
		}
		if (p2display > Status.magic[1]) {
			p2display -= 1;
			if (p2display < Status.magic[1]) {
				p2display = Status.magic[1];
			}
		}
		gc.setFill(Color.AQUA);
		gc.fillOval(10, 326, 240, 240);
		gc.setFill(Color.WHITE);
		gc.fillArc(10, 326, 240, 240, 90, 360.0 - ((p1display % 100) * 360.0 / 100.0), ArcType.ROUND);
		gc.setFill(Color.BLACK);
		gc.fillOval(20, 336, 220, 220);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 200));
		gc.setLineWidth(5);
		switch (p1display / 100) {
		case (1):
			gc.setStroke(Color.BLUE);
			break;
		case (2):
			gc.setStroke(Color.GREENYELLOW);
			break;
		case (3):
			gc.setStroke(Color.YELLOW);
			break;
		case (4):
			gc.setStroke(Color.ORANGERED);
			break;
		case (5):
			gc.setStroke(Color.RED);
			break;
		default:
			break;
		}
		if (p1display >= 100) {
			gc.fillText(Integer.toString(p1display / 100), 80, 516);
			gc.strokeText(Integer.toString(p1display / 100), 80, 516);
			if (p1display == 500) {
				gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 30));
				gc.setLineWidth(1);
				gc.fillText("MAX", 170, 466);
				gc.strokeText("MAX", 170, 466);
				gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 200));
				gc.setLineWidth(5);
			}
		}
		gc.setFill(Color.AQUA);
		gc.fillOval(774, 326, 240, 240);
		gc.setFill(Color.WHITE);
		gc.fillArc(774, 326, 240, 240, 90, 360.0 - ((p2display % 100) * 360.0 / 100.0), ArcType.ROUND);
		gc.setFill(Color.BLACK);
		gc.fillOval(784, 336, 220, 220);
		gc.setFill(Color.WHITE);
		switch (p2display / 100) {
		case (1):
			gc.setStroke(Color.BLUE);
			break;
		case (2):
			gc.setStroke(Color.GREENYELLOW);
			break;
		case (3):
			gc.setStroke(Color.YELLOW);
			break;
		case (4):
			gc.setStroke(Color.ORANGERED);
			break;
		case (5):
			gc.setStroke(Color.RED);
			break;
		default:
			break;
		}
		if (p2display >= 100) {
			gc.fillText(Integer.toString(p2display / 100), 80 + 764, 516);
			gc.strokeText(Integer.toString(p2display / 100), 80 + 764, 516);
			if (p2display == 500) {
				gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 30));
				gc.setLineWidth(1);
				gc.fillText("MAX", 170 + 764, 466);
				gc.strokeText("MAX", 170 + 764, 466);
				gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 200));
				gc.setLineWidth(5);
			}
		}
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
