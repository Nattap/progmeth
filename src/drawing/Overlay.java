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
import status.InputAssist;
import status.Status;

public class Overlay implements IRenderable {

	public Overlay() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getZ() {
		// TODO Auto-generated method stub
		return 2;
	}

	// (768, 10, 170, 57)(5, 10, 170, 57)
	@Override
	public void draw(GraphicsContext gc) {
		if (InputAssist.mousex < 938 && InputAssist.mousex > 768 && InputAssist.mousey < 67 && InputAssist.mousey > 10
				&& Status.turnOwner == 0 && (Status.phase == 2 || Status.phase == 1) && !InputAssist.turnending) {
			gc.setGlobalAlpha(0.7);
			gc.setFill(Color.WHITE);
			gc.fillRect(768, 10, 170, 57);
		}
		if (InputAssist.mousex < 175 && InputAssist.mousex > 5 && InputAssist.mousey < 67 && InputAssist.mousey > 10
				&& Status.turnOwner == 1 && (Status.phase == 2 || Status.phase == 1) && !InputAssist.turnending) {
			gc.setGlobalAlpha(0.7);
			gc.setFill(Color.WHITE);
			gc.fillRect(5, 10, 170, 57);
		}
		if (InputAssist.inrange(0, 260, 72, 312) || InputAssist.inrange(764, 1024, 72, 312)) {
			gc.setFill(Color.LAWNGREEN);
			gc.setGlobalAlpha(0.7);
			gc.fillRect((InputAssist.mousex / 764) * 764, ((InputAssist.mousey - 72) / 80) * 80 + 72, 260, 80);
			gc.setFill(Color.WHITE);
			gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
			int chara = ((InputAssist.mousex / 764) * 3) + ((InputAssist.mousey - 72) / 80);
			String display = Integer.toString(Status.character[chara].getCurhp()) + " / "
					+ Integer.toString(Status.character[chara].getHp());
			gc.fillText(display, (InputAssist.mousex / 764) * 764, ((InputAssist.mousey - 72) / 80) * 80 + 132);
		}
		gc.setGlobalAlpha(1);
		gc.setLineWidth(2);
		gc.setStroke(Color.CORNFLOWERBLUE);
		if (InputAssist.indicateto != -1) {
			gc.strokeRoundRect(435 + (InputAssist.indicateto * 40), 17, 40, 40, 5, 5);
		}
		gc.setStroke(Color.LAWNGREEN);
		gc.strokeRoundRect(435 + (Status.wildIndicate * 40), 17, 40, 40, 5, 5);
		// gc.drawImage(RenderableHolder.orbatk1, 430+10, 22, 30, 30);
		// gc.drawImage(RenderableHolder.orbatk2, 470+10, 22, 30, 30);
		// gc.drawImage(RenderableHolder.orbatk3, 510+10, 22, 30, 30);
		// gc.drawImage(RenderableHolder.orbrec1, 550+10, 22, 30, 30);
		// gc.drawImage(RenderableHolder.orbrec2, 590+10, 22, 30, 30);
		// gc.drawImage(RenderableHolder.orbrec3, 630+10, 22, 30, 30);
		// gc.drawImage(RenderableHolder.orbmag, 670+10, 22, 30, 30);
		// if (InputAssist.mousex < 475 && InputAssist.mousex > 445 &&
		// InputAssist.mousey < 57 && InputAssist.mousey > 17
		// && Status.phase == 1)
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

}
