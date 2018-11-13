/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package menu;

import java.util.ArrayList;
import java.util.List;

import drawing.IRenderable;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import main.Main;

public class Menuscreen extends Canvas {

	private Image background;
	private MenuOverlay overlay = new MenuOverlay();
	private boolean mouseonscreen = true;
	private boolean selectingA = false;
	private boolean selectingB = false;
	private boolean active = true;

	public Menuscreen() {
		super(1024, 576);
		loadres();
		addlistener();
	}

	public void loadres() {
		background = new Image(ClassLoader.getSystemResource("menubg.png").toString());
	}

	public void addlistener() {
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouseonscreen = true;
			}

		});

		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				mouseonscreen = false;
			}

		});

		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (mouseonscreen) {
					overlay.setX((int) (event.getX()));
					overlay.setY((int) (event.getY()));
				}
			}
		});

		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (mouseonscreen) {
					overlay.setX((int) (event.getX()));
					overlay.setY((int) (event.getY()));
				}
			}
		});

		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				overlay.setX((int) (event.getX()));
				overlay.setY((int) (event.getY()));
				overlay.clicked();
				if (overlay.inrange(50, 250, 426, 526)) {
					selectingA = true;
				}
				if (overlay.inrange(1024 - 250, 1024 - 50, 426, 526)) {
					selectingB = true;
				}
			}
		});

		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				overlay.setX((int) (event.getX()));
				overlay.setY((int) (event.getY()));
				overlay.released();
				if (overlay.inrange(50, 250, 426, 526) && selectingA) {
					Main.toinstruction();
					active = false;
				}
				if (overlay.inrange(1024 - 250, 1024 - 50, 426, 526) && selectingB) {
					Main.todraft();
					active = false;
				}
				selectingA = false;
				selectingB = false;
			}
		});
	}

	public boolean paintcomponents() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setGlobalAlpha(1);
		gc.drawImage(background, 0, 0);
		overlay.draw(gc);
		return active;
	}
}
