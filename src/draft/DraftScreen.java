/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package draft;

import java.util.Arrays;
import java.util.Random;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import main.Animtimer;
import main.Main;
import status.Status;
import template.Character;
import character.*;

public class DraftScreen extends Canvas {

	private static Character[] roster;
	public static int[] pickorder;
	public static Image draftframe;
	public HUD hud;
	private int selecttime = 30;
	private Thread timer;
	private boolean mouseonscreen = true;
	public static int hovering;
	public static int selecting;
	public static boolean israndoming = false;

	public DraftScreen() {
		super(1024, 576);
		timer = new Thread(() -> {
			try {
				while (true) {
					Thread.sleep(1000);
					timerdown();
				}
			} catch (InterruptedException e) {

			}
		});
		roster = new Character[20];
		pickorder = new int[10];
		hud = new HUD();
		Arrays.fill(pickorder, -1);
		// pickorder[0] = 5;
		// pickorder[1] = 7;
		// pickorder[2] = 2;
		// pickorder[3] = 12;
		// pickorder[4] = 0;
		// pickorder[5] = 19;
		// pickorder[6] = 15;
		// pickorder[7] = 4;
		// pickorder[8] = 9;
		roster[0] = new Alice(-1);
		roster[1] = new Aya(-1);
		roster[2] = new Cirno(-1);
		roster[3] = new Iku(-1);
		roster[4] = new Komachi(-1);
		roster[5] = new Marisa(-1);
		roster[6] = new Meiling(-1);
		roster[7] = new Patchouli(-1);
		roster[8] = new Reimu(-1);
		roster[9] = new Reisen(-1);
		roster[10] = new Remilia(-1);
		roster[11] = new Sakuya(-1);
		roster[12] = new Sanae(-1);
		roster[13] = new Suika(-1);
		roster[14] = new Suwako(-1);
		roster[15] = new Tenshi(-1);
		roster[16] = new Utsuho(-1);
		roster[17] = new Youmu(-1);
		roster[18] = new Yukari(-1);
		roster[19] = new Yuyuko(-1);
		addlistener();
		timer.start();
	}

	static {
		draftframe = new Image(ClassLoader.getSystemResource("Frame.png").toString());
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
					hud.setX((int) (event.getX()));
					hud.setY((int) (event.getY()));
					int locx = -1, locy = -1;
					int target = -1;
					if (hud.inrange(288, 736, 91, 485)) {
						if (event.getX() < 418.5 && event.getX() > 288.5) {
							locx = 0;
						}
						if (event.getX() < 577 && event.getX() > 447) {
							locx = 1;
						}
						if (event.getX() < 735.5 && event.getX() > 605.5) {
							locx = 2;
						}
						if (event.getY() < 131 && event.getY() > 91) {
							locy = 0;
						}
						if (event.getY() < 190 && event.getY() > 150) {
							locy = 1;
						}
						if (event.getY() < 249 && event.getY() > 209) {
							locy = 2;
						}
						if (event.getY() < 308 && event.getY() > 268) {
							locy = 3;
						}
						if (event.getY() < 367 && event.getY() > 327) {
							locy = 4;
						}
						if (event.getY() < 426 && event.getY() > 386) {
							locy = 5;
						}
						if (locx != -1 && locy != -1) {
							target = 3 * locy + locx;
						}
						if (event.getY() < 485 && event.getY() > 445) {
							locy = 6;
							if (locx != -1 && locx != 1) {
								target = 3 * locy + locx / 2;
							}
						}
						if (locx == 1 && locy == 6) {
							israndoming = true;
						} else {
							israndoming = false;
						}
						if (locx == -1 || locy == -1) {
							hovering = -1;
						}
					} else {
						israndoming = false;
						hovering = -1;
					}
					if (!isselected(target)) {
						hovering = target;
					}
				}
			}
		});

		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (mouseonscreen) {
					hud.setX((int) (event.getX()));
					hud.setY((int) (event.getY()));
					int locx = -1, locy = -1;
					int target = -1;
					if (hud.inrange(288, 736, 91, 485)) {
						if (event.getX() < 418.5 && event.getX() > 288.5) {
							locx = 0;
						}
						if (event.getX() < 577 && event.getX() > 447) {
							locx = 1;
						}
						if (event.getX() < 735.5 && event.getX() > 605.5) {
							locx = 2;
						}
						if (event.getY() < 131 && event.getY() > 91) {
							locy = 0;
						}
						if (event.getY() < 190 && event.getY() > 150) {
							locy = 1;
						}
						if (event.getY() < 249 && event.getY() > 209) {
							locy = 2;
						}
						if (event.getY() < 308 && event.getY() > 268) {
							locy = 3;
						}
						if (event.getY() < 367 && event.getY() > 327) {
							locy = 4;
						}
						if (event.getY() < 426 && event.getY() > 386) {
							locy = 5;
						}
						if (locx != -1 && locy != -1) {
							target = 3 * locy + locx;
						}
						if (event.getY() < 485 && event.getY() > 445) {
							locy = 6;
							if (locx != -1 && locx != 1) {
								target = 3 * locy + locx / 2;
							}
						}
						if (locx == 1 && locy == 6) {
							israndoming = true;
						} else {
							israndoming = false;
						}
						if (locx == -1 || locy == -1) {
							hovering = -1;
						}
					} else {
						israndoming = false;
						hovering = -1;
					}
					if (!isselected(target)) {
						hovering = target;
					}
				}
			}
		});

		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				hud.setX((int) (event.getX()));
				hud.setY((int) (event.getY()));
				hud.clicked();
				if (!isselected(hovering)) {
					selecting = hovering;
				} else {
					selecting = -1;
					hovering = -1;
				}
				if (israndoming) {
					selecting = -2;
				}
			}
		});

		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (selecting == hovering && selecting != -1) {
					selecting(selecting);
				}
				if (selecting == -2) {
					selecting(hovering);
				}
				// overlay.setX((int) (event.getX()));
				// overlay.setY((int) (event.getY()));
				// overlay.released();
				// if(overlay.inrange(50, 250, 426, 526)&&selectingA){
				// Main.toinstruction();
				// active = false;
				// }
				// if(overlay.inrange(1024-250, 1024-50, 426, 526)&&selectingB){
				// Main.todraft();
				// active = false;
				// }
			}
		});
	}

	public void paintcomponents() {
		if (israndoming) {
			Random rand = new Random();
			int ra = rand.nextInt(20);
			while (isselected(ra)) {
				ra = rand.nextInt(20);
			}
			hovering = ra;
		}
		int order = 0;
		boolean p1 = true;
		for (int i = 0; i < 10; i++) {
			if (pickorder[i] == -1) {
				order = i;
				break;
			}
		}
		switch (order) {
		case (0):
		case (3):
		case (4):
		case (7):
		case (8):
			p1 = true;
			break;
		case (1):
		case (2):
		case (5):
		case (6):
		case (9):
			p1 = false;
			break;
		}
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setGlobalAlpha(1);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, 1024, 576);
		for (int i = 0; i < 20; i++) {
			roster[i].drawdraft(gc, i);
		}
		hud.draw(gc);
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		gc.setGlobalAlpha(1);
		if (p1) {
			gc.fillText("Time left : " + Integer.toString(selecttime), 15, 57);
		} else {
			gc.fillText("Time left : " + Integer.toString(selecttime), 778, 57);
		}
	}

	public void timerdown() {
		selecttime--;
		if (selecttime == 0) {
			timeout();
		}
	}

	public void timeout() {
		Random rand = new Random();
		int c = rand.nextInt(20);
		for (int i = 0; i < 10; i++) {
			if (pickorder[i] == c) {
				c = rand.nextInt(20);
				i = -1;
			}
		}
		selecting(c);
	}

	public void selecting(int select) {
		for (int i = 0; i < 10; i++) {
			if (pickorder[i] == -1) {
				pickorder[i] = select;
				selecttime = 30;
				timer.interrupt();
				if (i == 0) {
					Status.character[0] = roster[select];
					roster[select].setLocation(0);
				}
				if (i == 1) {
					Status.character[3] = roster[select];
					roster[select].setLocation(3);
				}
				if (i == 4) {
					Status.character[1] = roster[select];
					roster[select].setLocation(1);
				}
				if (i == 5) {
					Status.character[4] = roster[select];
					roster[select].setLocation(4);
				}
				if (i == 8) {
					Status.character[2] = roster[select];
					roster[select].setLocation(2);
				}
				if (i == 9) {
					Status.character[5] = roster[select];
					roster[select].setLocation(5);
					Main.togame();
					Animtimer.drafta.stop();
				}
				timer = new Thread(() -> {
					try {
						while (true) {
							Thread.sleep(1000);
							timerdown();
						}
					} catch (InterruptedException e) {

					}
				});
				timer.start();
				return;
			}
		}
	}

	public boolean isselected(int chk) {
		for (int i = 0; i < 10; i++) {
			if (pickorder[i] == chk) {
				return true;
			}
		}
		return false;
	}
}
