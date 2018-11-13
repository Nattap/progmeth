/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package main;

import draft.DraftScreen;
import drawing.Gamescreen;
import instruction.Instructionscreen;
import javafx.animation.AnimationTimer;
import logic.MainLogic;
import menu.Menuscreen;

public class Animtimer {

	public static long start = 0l;
	public static AnimationTimer menua, drafta, gamea, instructiona;

	public static void resetstart() {
		start = 0l;
	}

	public static void runMenu(Menuscreen menu) {
		menua = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 10000000l) { // 100000000l = 100ms.
					if (!menu.paintcomponents()) {
						Animtimer.menua.stop();
					}

					// mainLogic.update();
					// gameScreen.paintComponents();
					// start = now;
					/*
					 * if(Status.moveleft == 0){ Status.orbbreak();
					 * Status.moveleft--; }
					 */
					// System.out.println(Status.phase);
					// System.out.println(Status.hp[3]);
					// Status.killOrb();
					start = now;
				}
			}
		};
		menua.start();
	}

	public static void runDraft(DraftScreen draft) {
		drafta = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 10000000l) { // 100000000l = 100ms.
					draft.paintcomponents();
				}
			}

		};
		drafta.start();
	}

	public static void runGame(Gamescreen g, MainLogic l) {
		gamea = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 10000000l) { // 100000000l = 100ms.
					g.paintComponents();
					l.update();
				}
			}

		};
		gamea.start();
	}

	public static void runInstruction(Instructionscreen instruct) {
		instructiona = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (start == 0l)
					start = now;
				long diff = now - start;
				if (diff >= 10000000l) { // 100000000l = 100ms.
					instruct.paintcomponents();
				}
			}

		};
		instructiona.start();
	}
}
