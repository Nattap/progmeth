/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package instruction;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import main.Animtimer;
import main.Main;

public class Instructionscreen extends Canvas {

	private static Image pg1, pg2, pg3, pg4;
	private int order;
	private boolean clicked;

	public Instructionscreen() {
		super(1024, 576);
		order = 0;
		clicked = false;
		addlistener();
	}

	static {
		pg1 = new Image(ClassLoader.getSystemResource("instruction1.png").toString());
		pg2 = new Image(ClassLoader.getSystemResource("instruction2.png").toString());
		pg3 = new Image(ClassLoader.getSystemResource("instruction3.png").toString());
		pg4 = new Image(ClassLoader.getSystemResource("instruction4.png").toString());
	}

	public void addlistener() {
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				clicked = true;
			}
		});

		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (clicked) {
					order++;
				}
			}
		});
	}

	public void paintcomponents() {
		GraphicsContext gc = this.getGraphicsContext2D();
		switch (order) {
		case (0):
			gc.drawImage(pg1, 0, 0);
			break;
		case (1):
			gc.drawImage(pg2, 0, 0);
			break;
		case (2):
			gc.drawImage(pg3, 0, 0);
			break;
		case (3):
			gc.drawImage(pg4, 0, 0);
			break;
		case (4):
			Main.tomain();
			Animtimer.instructiona.stop();
			break;
		}
	}
}
