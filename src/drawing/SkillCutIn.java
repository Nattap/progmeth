/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package drawing;

import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class SkillCutIn implements IRenderable {

	private Image cutin;
	// private boolean notify = false;
	private int opacity = 1;
	private boolean rising = true;
	private String name;
	private int overtime = 0;
	private boolean flip;

	public SkillCutIn(Image cutin, String name, boolean flip) {
		this.cutin = cutin;
		this.name = name;
		this.flip = flip;
		RenderableHolder.spell.play();
		RenderableHolder.getInstance().addlater(this);
	}

	@Override
	public int getZ() {
		return 4;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.ORANGE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		if (rising) {

			WritableImage cropped = new WritableImage(cutin.getPixelReader(), 500 - (opacity * 5), 0, opacity * 5, 500);
			if (flip)
				gc.scale(-1, 1);
			gc.setGlobalAlpha(opacity / 100.0);
			if (flip) {
				gc.drawImage(cropped, -1024, 76);
			} else {
				gc.drawImage(cropped, 0, 76);
			}
			gc.setGlobalAlpha(1);
			if (flip) {
				gc.scale(-1, 1);
				FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
				double font_width = fontLoader.computeStringWidth(name, gc.getFont());
				gc.fillText(name, 1024 - 50 - opacity - font_width, 476);
			} else {
				gc.fillText(name, 50 + opacity, 476);
			}
			opacity += 3;
			if (opacity >= 100) {
				opacity = 100;
				overtime += 1;
				if (overtime >= 30)
					rising = false;
			}
		} else {
			gc.setGlobalAlpha(opacity / 100.0);
			if (flip) {
				gc.scale(-1, 1);
				gc.drawImage(cutin, -1024, 76);
				gc.scale(-1, 1);
			} else {
				gc.drawImage(cutin, 0, 76);
			}
			FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
			double font_width = fontLoader.computeStringWidth(name, gc.getFont());
			if (flip) {
				gc.fillText(name, 1024 - 150 - font_width, 476);
			} else {
				gc.fillText(name, 150, 476);
			}
			opacity -= 3;
			if (opacity <= 0) {
				synchronized (this) {
					this.notify();
				}
				// notify = true;
				opacity = 0;
			}
		}
	}

	@Override
	public boolean isDestroyed() {
		return opacity <= 0;
	}

	// public boolean chk(){
	// System.out.println(this.notify);
	// return notify;
	// }

}
