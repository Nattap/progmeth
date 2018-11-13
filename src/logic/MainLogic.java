/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package logic;

import java.util.ArrayList;
import java.util.Arrays;

import drawing.RenderableHolder;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import main.Animtimer;
import main.Main;
import status.InputAssist;
import status.Status;
import template.BattleStatus;
import template.SkillFailException;

public class MainLogic {

	public MainLogic() {
		for (int i = 0; i < 6; i++) {
			RenderableHolder.getInstance().add(Status.character[i]);
		}
	}

	public void update() {
		switch (Status.phase) {
		case (-1):
			while (true) {
				Status.refill();
				boolean broken = Status.orbbreak();
				while (!Status.allkilled()) {
					Status.killOrb();
					RenderableHolder.dissolve.stop();
				}
				if (!broken) {
					for (int i = 0; i < 6; i++) {
						Status.character[i].turnreset();
					}
					Arrays.fill(Status.combo, 0);
					break;
				}
			}
			Status.orbdrop();
			if (Status.alldrop()) {
				Status.phase = 0;
			}
			break;
		case (0):
			Platform.runLater(() -> {
				Alert alert2 = new Alert(AlertType.INFORMATION);
				alert2.setTitle("Next turn");
				alert2.setHeaderText(null);
				alert2.setContentText("Player" + (Status.turnOwner + 1) + "'s turn");
				alert2.showAndWait();
			});
			for (int i = 0; i < 6; i++) {
				Status.character[i].turnreset();
			}
			Arrays.fill(Status.combo, 0);
			Arrays.fill(Status.dissolved, 0);
			Status.moveleft += Status.character[Status.turnOwner * 3].getSpd();
			Status.moveleft += Status.character[Status.turnOwner * 3 + 1].getSpd();
			Status.moveleft += Status.character[Status.turnOwner * 3 + 2].getSpd();
			Status.statuschk();
			if (Status.chkWinner() != 0) {
				Status.phase = 8;
			}
			Status.phase = 1;
			break;
		case (1):
			/*
			 * if (InputAssist.move()[0] != -1) {
			 * Status.boardMove(InputAssist.move()[0], InputAssist.move()[1],
			 * InputAssist.move()[2], InputAssist.move()[3]); Status.moveleft--;
			 * Status.phase = 2; } if (Status.turnend = true) {
			 * Status.animationset = true; Status.phase = 3; }
			 */
			break;
		case (2):
			break;
		case (3):
			if (Status.orbbreak()) {
				Status.phase = 4;
			} else {
				Status.phase = 6;
			}
			break;
		case (4):
			Status.killOrb();
			if (Status.allkilled()) {
				// System.out.println("done");
				// System.out.println(RenderableHolder.getInstance().getEntities().size());
				Status.orbfall();
				// System.out.println(RenderableHolder.getInstance().getEntities().size());
				Status.refill();
				// System.out.println(RenderableHolder.getInstance().getEntities().size());
				Status.phase = 5;
			}
			break;
		case (5):
			Status.orbdrop();
			if (Status.alldrop()) {
				Status.phase = 3;
			}
			break;
		case (6):
			Status.statuschk();
			// Status.dissolved[Status.wildIndicate] += Status.dissolved[7];
			for (int i = 3 * Status.turnOwner; i < 3 + 3 * Status.turnOwner; i++) {
				Status.character[i].comboboost(0.05);
				Status.character[i]
						.recover((int) (Status.character[i].getrecpower() * Status.character[i].getRecmod()));
				Status.character[Status.character[i].getOpponent()].damaged((int) (Status.character[i].getpower()
						* Status.character[Status.character[i].getOpponent()].getDefmod()
						* Status.character[i].getAtkmod()));
			}
			// Status.damageDealt[Status.turnOwner
			// * 3] = (int) (Status.dissolved[0] *
			// Status.character[Status.turnOwner * 3].getAtk()
			// * (1 + Status.totalCombo() * 0.1) *
			// Status.atkmultiplier[Status.turnOwner * 3]);
			// Status.damageDealt[Status.turnOwner * 3
			// + 1] = (int) (Status.dissolved[1] *
			// Status.character[Status.turnOwner * 3 + 1].getAtk()
			// * (1 + Status.totalCombo() * 0.1) *
			// Status.atkmultiplier[Status.turnOwner * 3 + 1]);
			// Status.damageDealt[Status.turnOwner * 3
			// + 2] = (int) (Status.dissolved[2] *
			// Status.character[Status.turnOwner * 3 + 2].getAtk()
			// * (1 + Status.totalCombo() * 0.1) *
			// Status.atkmultiplier[Status.turnOwner * 3 + 2]);
			// Status.character[Status.turnOwner * 3]
			// .recover((int) (Status.dissolved[3] *
			// Status.character[Status.turnOwner * 3].getRec()
			// * (1 + Status.totalCombo() * 0.1) *
			// Status.recmultiplier[Status.turnOwner * 3]));
			// Status.character[Status.turnOwner * 3 + 1]
			// .recover((int) (Status.dissolved[4] *
			// Status.character[Status.turnOwner * 3 + 1].getRec()
			// * (1 + Status.totalCombo() * 0.1) *
			// Status.recmultiplier[Status.turnOwner * 3]));
			// Status.character[Status.turnOwner * 3 + 2]
			// .recover((int) (Status.dissolved[5] *
			// Status.character[Status.turnOwner * 3 + 2].getRec()
			// * (1 + Status.totalCombo() * 0.1) *
			// Status.recmultiplier[Status.turnOwner * 3]));
			// Status.character[Status.character[Status.turnOwner *
			// 3].getOpponent()]
			// .damaged((int) (Status.damageDealt[Status.turnOwner * 3]
			// * Status.defmultiplier[Status.character[Status.turnOwner *
			// 3].getOpponent()]));
			// Status.character[Status.character[Status.turnOwner * 3 +
			// 1].getOpponent()]
			// .damaged((int) (Status.damageDealt[Status.turnOwner * 3 + 1]
			// * Status.defmultiplier[Status.character[Status.turnOwner * 3 +
			// 1].getOpponent()]));
			// Status.character[Status.character[Status.turnOwner * 3 +
			// 2].getOpponent()]
			// .damaged((int) (Status.damageDealt[Status.turnOwner * 3 + 2]
			// * Status.defmultiplier[Status.character[Status.turnOwner * 3 +
			// 2].getOpponent()]));
			Status.magic[Status.turnOwner] += (int) ((Status.getTotalMag(Status.turnOwner)
					* (1 + (Status.totalCombo() - 1) * 0.05)) * Status.dissolved[6]);
			if (Status.magic[Status.turnOwner] > 500) {
				Status.magic[Status.turnOwner] = 500;
			}
			Status.phase = 7;
			break;
		case (7):
			break;
		case (8):
			Status.statuschk();
			if (Status.chkWinner() != 0) {
				Platform.runLater(() -> {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Game Over");
					alert.setHeaderText(null);
					alert.setContentText("Player" + Status.chkWinner() + " is victorious.");
					alert.showAndWait();
					Animtimer.gamea.stop();
					Status.reset();
					RenderableHolder.reset();
					Main.tomain();
				});
				Status.phase = 9;
			} else {
				Status.turnOwner = 1 - Status.turnOwner;
				Status.phase = 0;
				Status.moveleft = 0;
			}
			break;
		default:
			break;
		}
	}

}
