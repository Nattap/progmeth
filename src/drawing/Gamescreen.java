/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package drawing;

import java.util.Optional;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import status.InputAssist;
import status.Status;
import template.SkillFailException;

public class Gamescreen extends Canvas {
	public Gamescreen() {
		super(1024, 576);
		addListener();
		RenderableHolder.getInstance().add(new Moveindicator());
		RenderableHolder.getInstance().add(new TurnEndButton());
		RenderableHolder.getInstance().add(new Overlay());
		RenderableHolder.getInstance().add(new Skillbar());
		RenderableHolder.getInstance().add(new WildSelect());
		RenderableHolder.getInstance().add(new Comboindicator());
		RenderableHolder.getInstance().add(new Background());
	}

	public void addListener() {
		this.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (InputAssist.mouseonscreen) {
					InputAssist.mousex = (int) event.getX();
					InputAssist.mousey = (int) event.getY();
					if (InputAssist.mousex < 685 + 30 && InputAssist.mousex > 435 && InputAssist.mousey < 57
							&& InputAssist.mousey > 17 && Status.phase == 1) {
						InputAssist.indicateto = (InputAssist.mousex - 435) / 40;
					} else {
						InputAssist.indicateto = -1;
					}
				}
			}
		});
		this.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				InputAssist.mouseonscreen = true;
			}
		});
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				InputAssist.mouseonscreen = false;
			}
		});
		this.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				InputAssist.mousedown = true;
				if (InputAssist.inrange(0, 260, 72, 312)) {
					int skilluser = (InputAssist.mousey - 72) / 80;
					Platform.runLater(() -> {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(Status.character[skilluser].getName());
						alert.setHeaderText(null);
						alert.setContentText("HP : " + Status.character[skilluser].getCurhp() + "/"
								+ Status.character[skilluser].getHp() + "\n" + "Attack : "
								+ Status.character[skilluser].getAtk() + "\n" + "Recovery : "
								+ Status.character[skilluser].getRec() + "\n" + "Speed : "
								+ Status.character[skilluser].getSpd() + "\n" + "Magic : "
								+ Status.character[skilluser].getMag());
						ButtonType useA = new ButtonType("Use SkillA");
						ButtonType useB = new ButtonType("Use SkillB");
						ButtonType stat = new ButtonType("See Status");
						ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
						if (Status.turnOwner == 0) {
							alert.getButtonTypes().setAll(useA, useB, stat, cancel);
						} else {
							alert.getButtonTypes().setAll(stat, cancel);
						}
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == useA) {
							Alert alert2 = new Alert(AlertType.CONFIRMATION);
							alert2.setTitle("Skill Confirmation");
							alert2.setHeaderText("Use \"" + Status.character[skilluser].getSkillNameA() + "\"?");
							alert2.setContentText(Status.character[skilluser].getSkillDescriptionA());
							Optional<ButtonType> result2 = alert2.showAndWait();
							if (result2.get() == ButtonType.OK) {
								try {
									Status.character[skilluser].skillA();
								} catch (SkillFailException e) {
									Alert alert3 = new Alert(AlertType.ERROR);
									alert3.setTitle("Skill use failed");
									alert3.setHeaderText(null);
									alert3.setContentText(e.getMessage());
									alert3.showAndWait();
								}
							}
						} else if (result.get() == useB) {
							Alert alert2 = new Alert(AlertType.CONFIRMATION);
							alert2.setTitle("Skill Confirmation");
							alert2.setHeaderText("Use \"" + Status.character[skilluser].getSkillNameB() + "\"?");
							alert2.setContentText(Status.character[skilluser].getSkillDescriptionB());
							Optional<ButtonType> result2 = alert2.showAndWait();
							if (result2.get() == ButtonType.OK) {
								try {
									Status.character[skilluser].skillB();
								} catch (SkillFailException e) {
									Alert alert3 = new Alert(AlertType.ERROR);
									alert3.setTitle("Skill use failed");
									alert3.setHeaderText(null);
									alert3.setContentText(e.getMessage());
									alert3.showAndWait();
								}
							}
						} else {
							if (result.get() == stat) {
								Alert alert3 = new Alert(AlertType.INFORMATION);
								alert3.setTitle("Active Battle Status");
								alert3.setHeaderText(null);
								alert3.setContentText(Status.character[skilluser].getStatus());
								alert3.showAndWait();
							}
						}
					});

				}
				if (InputAssist.inrange(0 + 764, 260 + 764, 72, 312)) {
					int skilluser = ((InputAssist.mousey - 72) / 80) + 3;
					Platform.runLater(() -> {
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle(Status.character[skilluser].getName());
						alert.setHeaderText(null);
						alert.setContentText("HP : " + Status.character[skilluser].getCurhp() + "/"
								+ Status.character[skilluser].getHp() + "\n" + "Attack : "
								+ Status.character[skilluser].getAtk() + "\n" + "Recovery : "
								+ Status.character[skilluser].getRec() + "\n" + "Speed : "
								+ Status.character[skilluser].getSpd() + "\n" + "Magic : "
								+ Status.character[skilluser].getMag());
						ButtonType useA = new ButtonType("Use SkillA");
						ButtonType useB = new ButtonType("Use SkillB");
						ButtonType stat = new ButtonType("See Status");
						ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
						if (Status.turnOwner == 1) {
							alert.getButtonTypes().setAll(useA, useB, stat, cancel);
						} else {
							alert.getButtonTypes().setAll(stat, cancel);
						}
						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == useA) {
							Alert alert2 = new Alert(AlertType.CONFIRMATION);
							alert2.setTitle("Skill Confirmation");
							alert2.setHeaderText("Use \"" + Status.character[skilluser].getSkillNameA() + "\"?");
							alert2.setContentText(Status.character[skilluser].getSkillDescriptionA());
							Optional<ButtonType> result2 = alert2.showAndWait();
							if (result2.get() == ButtonType.OK) {
								try {
									Status.character[skilluser].skillA();
								} catch (SkillFailException e) {
									Alert alert3 = new Alert(AlertType.ERROR);
									alert3.setTitle("Skill use failed");
									alert3.setHeaderText(null);
									alert3.setContentText(e.getMessage());
									alert3.showAndWait();
								}
							}
						} else if (result.get() == useB) {
							Alert alert2 = new Alert(AlertType.CONFIRMATION);
							alert2.setTitle("Skill Confirmation");
							alert2.setHeaderText("Use \"" + Status.character[skilluser].getSkillNameB() + "\"?");
							alert2.setContentText(Status.character[skilluser].getSkillDescriptionB());
							Optional<ButtonType> result2 = alert2.showAndWait();
							if (result2.get() == ButtonType.OK) {
								try {
									Status.character[skilluser].skillB();
								} catch (SkillFailException e) {
									Alert alert3 = new Alert(AlertType.ERROR);
									alert3.setTitle("Skill use failed");
									alert3.setHeaderText(null);
									alert3.setContentText(e.getMessage());
									alert3.showAndWait();
								}
							}
						} else {
							if (result.get() == stat) {
								Alert alert3 = new Alert(AlertType.INFORMATION);
								alert3.setTitle("Active Battle Status");
								alert3.setHeaderText(null);
								alert3.setContentText(Status.character[skilluser].getStatus());
								alert3.showAndWait();
							}
						}
					});
				}
				if (InputAssist.indicateto != -1) {
					InputAssist.wildselecting = true;
				}
				// (768, 10, 170, 57)(5, 10, 170, 57)
				if (arg0.getX() < 938 && arg0.getX() > 768 && arg0.getY() < 67 && arg0.getY() > 10
						&& Status.turnOwner == 0 && (Status.phase == 2 || Status.phase == 1)) {
					InputAssist.turnending = true;
				}
				if (arg0.getX() < 175 && arg0.getX() > 5 && arg0.getY() < 67 && arg0.getY() > 10
						&& Status.turnOwner == 1 && (Status.phase == 2 || Status.phase == 1)) {
					InputAssist.turnending = true;
				}
			}
		});
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (InputAssist.mouseonscreen) {
					InputAssist.mousex = (int) arg0.getX();
					InputAssist.mousey = (int) arg0.getY();
				}
				if (arg0.getX() < 764 && arg0.getX() > 260 && arg0.getY() < 576 && arg0.getY() > 72
						&& (Status.phase == 1 || Status.phase == 2)) {
					if (InputAssist.startx == -1) {
						InputAssist.startx = (int) arg0.getX();
						InputAssist.starty = (int) arg0.getY();
						InputAssist.currentx = (int) arg0.getX();
						InputAssist.currenty = (int) arg0.getY();
					} else {
						int movingorbx = (InputAssist.startx - 260) / 56;
						int movingorby = (InputAssist.starty - 72) / 56;
						InputAssist.currentx = (int) arg0.getX();
						InputAssist.currenty = (int) arg0.getY();
						Status.boardresnap();
						if (InputAssist.xismoving()) {
							for (int i = 0; i < 9; i++) {
								int newx = 260 + i * 56 + InputAssist.currentx - InputAssist.startx;
								if (newx <= 204) {
									newx = newx + 504;
								}
								if (newx >= 764) {
									newx = newx - 504;
								}
								Status.board[i][movingorby].setX(newx);
							}
						} else {
							for (int i = 0; i < 9; i++) {
								int newy = 72 + i * 56 + InputAssist.currenty - InputAssist.starty;
								if (newy <= 16) {
									newy = newy + 504;
								}
								if (newy >= 576) {
									newy = newy - 504;
								}
								Status.board[movingorbx][i].setY(newy);
							}
						}
					}
				} else {
					InputAssist.reinitialize();
					if (Status.phase == 1 || Status.phase == 2) {
						Status.boardresnap();
					}
				}
			}
		});
		this.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				InputAssist.mousedown = false;
				if (InputAssist.wildselecting) {
					if (InputAssist.indicateto == (InputAssist.mousex - 435) / 40) {
						Status.wildIndicate = InputAssist.indicateto;
					}
					InputAssist.wildselecting = false;
				}
				if (arg0.getX() < 764 && arg0.getX() > 260 && arg0.getY() <= 576 && arg0.getY() > 72
						&& (Status.phase == 1 || Status.phase == 2) && InputAssist.startx != -1) {
					InputAssist.currentx = (int) arg0.getX();
					InputAssist.currenty = (int) arg0.getY();
					InputAssist.endx = (int) arg0.getX();
					InputAssist.endy = (int) arg0.getY();
					int movingorbx = (InputAssist.startx - 260) / 56;
					int movingorby = (InputAssist.starty - 72) / 56;
					Status.boardresnap();
					int moved = 0;
					if (InputAssist.xismoving()) {
						for (int i = 0; i < 9; i++) {
							moved = InputAssist.currentx - InputAssist.startx;
							if (moved < 0) {
								moved = ((moved - 28) / 56) * 56;
							} else {
								moved = ((moved + 28) / 56) * 56;
							}
							int newx = 260 + i * 56 + moved;
							if (newx <= 204) {
								newx = newx + 504;
							}
							if (newx >= 764) {
								newx = newx - 504;
							}
							Status.board[i][movingorby].setX(newx);
						}
					} else {
						for (int i = 0; i < 9; i++) {
							moved = InputAssist.currenty - InputAssist.starty;
							if (moved < 0) {
								moved = ((moved - 28) / 56) * 56;
							} else {
								moved = ((moved + 28) / 56) * 56;
							}
							int newy = 72 + i * 56 + moved;
							if (newy <= 16) {
								newy = newy + 504;
							}
							if (newy >= 576) {
								newy = newy - 504;
							}
							Status.board[movingorbx][i].setY(newy);
						}
					}
					InputAssist.reinitialize();
					Status.boardRelocate();
					Status.boardresnap();
					if (moved != 0) {
						Status.moveleft--;
						if (Status.moveleft > 0) {
							Status.phase = 2;
						} else {
							Status.phase = 3;
						}
					}
				}
				if (arg0.getX() < 938 && arg0.getX() > 768 && arg0.getY() < 67 && arg0.getY() > 10
						&& Status.turnOwner == 0 && (Status.phase == 2 || Status.phase == 1) && InputAssist.turnending
						&& RenderableHolder.getInstance().noCutin()) {
					InputAssist.turnending = false;
					Status.phase = 3;
				} else {
					if (Status.turnOwner == 0) {
						InputAssist.turnending = false;
					}
				}
				if (arg0.getX() < 175 && arg0.getX() > 5 && arg0.getY() < 67 && arg0.getY() > 10
						&& Status.turnOwner == 1 && (Status.phase == 2 || Status.phase == 1) && InputAssist.turnending
						&& RenderableHolder.getInstance().noCutin()) {
					InputAssist.turnending = false;
					Status.phase = 3;
				} else {
					if (Status.turnOwner == 1) {
						InputAssist.turnending = false;
					}
				}
			}
		});
	}

	public void paintComponents() {
		GraphicsContext gc = this.getGraphicsContext2D();
		// gc.setFill(Color.BLACK);
		// gc.fillRect(0, 0, 1024, 576);
		// if (Status.phase == 3) {
		// Status.killOrb();
		// if (Status.allkilled()) {
		// Status.animationset = true;
		// }
		// }
		// if (Status.phase == 5) {
		// boolean moved = false;
		// for (int i = 0; i < 9; i++) {
		// for (int j = 8; j >= 0; j--) {
		// if (j == 8) {
		// if (Status.board[i][j].getY() < 520) {
		// Status.board[i][j].setY(Status.board[i][j].getY() + 5);
		// if (Status.board[i][j].getY() > 520) {
		// Status.board[i][j].setY(520);
		// }
		// moved = true;
		// }
		// // else{
		// // System.out.println(i+" "+j+"
		// // "+Status.board[i][j].getY());
		// // }
		// } else {
		// if (Status.board[i][j + 1].getY() - Status.board[i][j].getY() > 56) {
		// Status.board[i][j].setY(Status.board[i][j].getY() + 5);
		// if (Status.board[i][j + 1].getY() - Status.board[i][j].getY() < 56) {
		// Status.board[i][j].setY(Status.board[i][j + 1].getY() - 56);
		// }
		// moved = true;
		// }
		// }
		// }
		// }
		// }
		for (IRenderable e : RenderableHolder.getInstance().getEntities()) {
			e.draw(gc);
			gc.setGlobalAlpha(1);
		}
		RenderableHolder.getInstance().update();
		if (Status.phase == 7) {
			if (RenderableHolder.getInstance().noDamage()) {
				Status.phase = 8;
			}
		}
	}
}
