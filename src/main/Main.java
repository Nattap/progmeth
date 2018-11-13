/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package main;

import draft.DraftScreen;
import drawing.Gamescreen;
import drawing.RenderableHolder;
import instruction.Instructionscreen;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.MainLogic;
import menu.Menuscreen;

public class Main extends Application {

	// public static Main instance;
	private static Stage primaryStage;
	private static Scene gameScene, menuScene, draftScene, instructScene;
	private static Menuscreen menu;
	private static DraftScreen draft;
	private static MainLogic logic;
	private static Gamescreen gamescreen;
	private static Instructionscreen instruct;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) {
		// instance = this;
		primaryStage = stage;
		primaryStage.setTitle("Tower of Gensokyo");
		// this.primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
		// Main.tomain();
		Main.tomain();
		primaryStage.show();
		// StackPane root = new StackPane();
		// Scene scene = new Scene(root);
		// Gamescreen gameScreen = new Gamescreen();
		// MainLogic mainLogic = new MainLogic();
		// stage.setScene(scene);
		// root.getChildren().add(gameScreen);
		// gameScreen.requestFocus();
		// stage.show();
		// Status.character[0] = new Suwako(0);
		// RenderableHolder.getInstance().add(Status.character[0]);
		// Status.character[1] = new Iku(1);
		// RenderableHolder.getInstance().add(Status.character[1]);
		// Status.character[2] = new Suika(2);
		// RenderableHolder.getInstance().add(Status.character[2]);
		// Status.character[3] = new Sanae(3);
		// RenderableHolder.getInstance().add(Status.character[3]);
		// Status.character[4] = new Yukari(4);
		// RenderableHolder.getInstance().add(Status.character[4]);
		// Status.character[5] = new Cirno(5);
		// RenderableHolder.getInstance().add(Status.character[5]);
		//
		// AnimationTimer animation = new AnimationTimer() {
		// Long start = 0l;
		//
		// @Override
		// public void handle(long now) {
		// // TODO Auto-generated method stub
		// if (start == 0l)
		// start = now;
		// long diff = now - start;
		// if (diff >= 10000000l) { // 100000000l = 100ms.
		// mainLogic.update();
		// gameScreen.paintComponents();
		// start = now;
		// /*
		// * if(Status.moveleft == 0){ Status.orbbreak();
		// * Status.moveleft--; }
		// */
		// // System.out.println(Status.phase);
		// // System.out.println(Status.hp[3]);
		// // Status.killOrb();
		// }
		// }
		// };
		// animation.start();
	}

	public static void toinstruction() {
		Animtimer.resetstart();
		instruct = new Instructionscreen();
		StackPane instructpane = new StackPane();
		instructpane.getChildren().add(instruct);
		instructScene = new Scene(instructpane);
		instruct.requestFocus();
		Animtimer.runInstruction(instruct);
		primaryStage.setScene(instructScene);
	}

	public static void todraft() {
		Animtimer.resetstart();
		draft = new DraftScreen();
		StackPane draftpane = new StackPane();
		draftpane.getChildren().add(draft);
		draftScene = new Scene(draftpane);
		draft.requestFocus();
		Animtimer.runDraft(draft);
		primaryStage.setScene(draftScene);
		RenderableHolder.draft.play();
		RenderableHolder.game.stop();
		RenderableHolder.menu.stop();
	}

	public static void togame() {
		Animtimer.resetstart();
		gamescreen = new Gamescreen();
		logic = new MainLogic();
		StackPane gamepane = new StackPane();
		gamepane.getChildren().add(gamescreen);
		gameScene = new Scene(gamepane);
		gamescreen.requestFocus();
		Animtimer.runGame(gamescreen, logic);
		Platform.runLater(() -> {
			primaryStage.setScene(gameScene);
		});
		RenderableHolder.draft.stop();
		RenderableHolder.game.play();
		RenderableHolder.menu.stop();
	}

	public static void tomain() {
		Animtimer.resetstart();
		menu = new Menuscreen();
		StackPane menupane = new StackPane();
		menupane.getChildren().add(menu);
		menuScene = new Scene(menupane);
		menu.requestFocus();
		Animtimer.runMenu(menu);
		primaryStage.setScene(menuScene);
		RenderableHolder.menu.stop();
		RenderableHolder.draft.stop();
		RenderableHolder.game.stop();
		RenderableHolder.menu.play();
	}
}
