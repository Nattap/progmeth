/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package draft;

import drawing.IRenderable;
import drawing.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class HUD implements IRenderable {

	private int x, y;
	private boolean clicked = false;
	private int opacity = 100;
	private boolean rising = false;

	public void clicked() {
		clicked = true;
	}

	public void released() {
		clicked = false;
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

	public HUD() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setGlobalAlpha(1);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		gc.fillText("Character selection", 360, 57);
		gc.setFill(Color.BLACK);
		gc.fillRect(447, 445, 130, 40);
		gc.drawImage(DraftScreen.draftframe, 447, 445, 130, 40);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 30));
		gc.fillText("?", 508, 476);
		gc.drawImage(RenderableHolder.frame1, 0, 72);
		gc.drawImage(RenderableHolder.frame1, 764, 72);
		gc.drawImage(DraftScreen.draftframe, 0, 72 + 80);
		gc.drawImage(DraftScreen.draftframe, 764, 72 + 80);
		gc.drawImage(RenderableHolder.frame2, 0, 72 + 80 + 80);
		gc.drawImage(RenderableHolder.frame2, 764, 72 + 80 + 80);
		gc.drawImage(DraftScreen.draftframe, 0, 72 + 80 + 80 + 80);
		gc.drawImage(DraftScreen.draftframe, 764, 72 + 80 + 80 + 80);
		gc.drawImage(RenderableHolder.frame3, 0, 72 + 80 + 80 + 80 + 80);
		gc.drawImage(RenderableHolder.frame3, 764, 72 + 80 + 80 + 80 + 80);
		gc.setFill(Color.ORANGE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		int order = -1;
		for (int i = 0; i < 10; i++) {
			if (DraftScreen.pickorder[i] == -1) {
				order = i;
				break;
			}
		}
		if (rising) {
			opacity += 2;
			if (opacity >= 100) {
				opacity = 100;
				rising = false;
			}
		} else {
			opacity -= 2;
			if (opacity <= 0) {
				opacity = 0;
				rising = true;
			}
		}
		gc.setGlobalAlpha(opacity / 100.0);
		switch (order) {
		case (0):
			gc.fillText("Picking", 120, 132);
			break;
		case (1):
			gc.fillText("Picking", 784, 132);
			break;
		case (2):
			gc.fillText("Banning", 784, 222 - 10);
			break;
		case (3):
			gc.fillText("Banning", 115, 222 - 10);
			break;
		case (4):
			gc.fillText("Picking", 120, 302 - 10);
			break;
		case (5):
			gc.fillText("Picking", 784, 302 - 10);
			break;
		case (6):
			gc.fillText("Banning", 784, 382 - 10);
			break;
		case (7):
			gc.fillText("Banning", 115, 382 - 10);
			break;
		case (8):
			gc.fillText("Picking", 120, 462 - 10);
			break;
		case (9):
			gc.fillText("Picking", 784, 462 - 10);
			break;
		}
		gc.setFill(Color.WHITE);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean inrange(int minx, int maxx, int miny, int maxy) {
		return x < maxx && x > minx && y < maxy && y > miny;
	}

}
