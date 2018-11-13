/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package template;

import java.util.ArrayList;

import draft.DraftScreen;
import drawing.Damage;
import drawing.IRenderable;
import drawing.RenderableHolder;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import status.Status;

public abstract class Character implements IRenderable {

	protected String name;
	protected int hp, atk, rec, spd, mag, location = -1;// location 0-5, -1 for
														// character selection
	protected int curhp, displayedhp;
	protected int attackingpower = 0;
	protected int recoveringpower = 0;
	protected boolean isdead = false;
	protected Image cutin, sprite, dead;
	protected double atkmod, defmod, recmod;
	protected ArrayList<BattleStatus> statusHolder = new ArrayList<BattleStatus>();
	protected String skillDescriptionA, skillNameA;
	protected String skillDescriptionB, skillNameB;

	public void addstatus(BattleStatus b) {
		statusHolder.add(b);
	}

	public void statusact() {
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			statusHolder.get(i).act(location);
		}
		if (Status.phase == 8 && Status.turnOwner == location / 3) {
			for (int i = statusHolder.size() - 1; i >= 0; i--) {
				statusHolder.get(i).duration--;
				if (statusHolder.get(i).duration == 0) {
					statusHolder.remove(i);
				}
			}
		}
	}

	public String getStatus() {
		String out = "";
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			out += "- ";
			out += statusHolder.get(i).getName();
			out += "\n";
			out += statusHolder.get(i).getDescription();
			out += "\nDuration : ";
			out += Integer.toString(statusHolder.get(i).duration);
			if (i != 0) {
				out += "\n";
			}
		}
		return out;
	}

	public double getAtkmod() {
		return atkmod;
	}

	public double getDefmod() {
		return defmod;
	}

	public double getRecmod() {
		return recmod;
	}

	public void atkmulti(double multi) {
		atkmod = atkmod * multi;
	}

	public void defmulti(double multi) {
		defmod = defmod * multi;
	}

	public void recmulti(double multi) {
		recmod = recmod * multi;
	}

	public void turnreset() {
		atkmod = 1;
		defmod = 1;
		recmod = 1;
		attackingpower = 0;
		recoveringpower = 0;
	}

	public void updatepower() {
		if (!isdead()) {
			attackingpower = Status.dissolved[location % 3] * atk;
			recoveringpower = Status.dissolved[3 + (location % 3)] * rec;
		}
	}

	public void comboboost(double combovalue) {
		attackingpower = (int) (attackingpower * (1 + (combovalue * (Status.totalCombo() - 1))));
		recoveringpower = (int) (recoveringpower * (1 + (combovalue * (Status.totalCombo() - 1))));
	}

	public int getpower() {
		return attackingpower;
	}

	public int getrecpower() {
		return recoveringpower;
	}

	public int getLocation() {
		return location;
	}

	public int getSpd() {
		return spd;
	}

	public int getMag() {
		return mag;
	}

	public String getSkillNameA() {
		return skillNameA;
	}

	public String getSkillNameB() {
		return skillNameB;
	}

	/*
	 * public String getPowerPlayName() { return powerPlayName; }
	 */

	/*
	 * public String getImgName() { return imgName; }
	 */
	// protected String powerPlayDescription, powerPlayName;
	// protected String imgName;

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}

	public int getAtk() {
		return atk;
	}

	public int getRec() {
		return rec;
	}

	public int getCurhp() {
		return curhp;
	}

	public String getSkillDescriptionA() {
		return skillDescriptionA;
	}

	public String getSkillDescriptionB() {
		return skillDescriptionB;
	}

	/*
	 * public String getPowerPlayDescription() { return powerPlayDescription; }
	 */
	public boolean isDestroyed() {
		return false;
	}

	public abstract void skillA() throws SkillFailException;

	public abstract void skillB() throws SkillFailException;

	public int getdisplayedhp() {
		return displayedhp;
	}

	public void damaged(int value) {
		if (value > 0) {
			RenderableHolder.attack.play();
			new Damage(location, value, true);
		}
		curhp -= value;
		if (curhp < 0) {
			curhp = 0;
		}
	}

	public void recover(int value) {
		if (value > 0) {
			new Damage(location, value, false);
		}
		this.curhp += value;
		if (curhp > hp) {
			curhp = hp;
		}
	}

	public boolean isdead() {
		if (isdead) {
			return true;
		} else {
			if (displayedhp <= 0) {
				RenderableHolder.dead.play();
				isdead = true;
				return true;
			}
		}
		return false;
	}

	// public abstract boolean checkPowerPlay();

	// public abstract void PowerPlay();

	public int getOpponent() {
		switch (location) {
		case (0):
			if (Status.character[3].isdead()) {
				if (Status.character[4].isdead()) {
					return 5;
				} else
					return 4;
			} else
				return 3;
		case (1):
			if (Status.character[4].isdead()) {
				if (Status.character[5].isdead()) {
					return 3;
				} else
					return 5;
			} else
				return 4;
		case (2):
			if (Status.character[5].isdead()) {
				if (Status.character[4].isdead()) {
					return 3;
				} else
					return 4;
			} else
				return 5;
		case (3):
			if (Status.character[0].isdead()) {
				if (Status.character[1].isdead()) {
					return 2;
				} else
					return 1;
			} else
				return 0;
		case (4):
			if (Status.character[1].isdead()) {
				if (Status.character[2].isdead()) {
					return 0;
				} else
					return 2;
			} else
				return 1;
		case (5):
			if (Status.character[2].isdead()) {
				if (Status.character[1].isdead()) {
					return 0;
				} else
					return 1;
			} else
				return 2;
		default:
			return -1;
		}
	}

	public void draw(GraphicsContext gc) {
		if (this.location == -1) {
			return;
		}
		if (this.isdead()) {
			switch (this.location) {
			case (0):
			case (3):
				gc.drawImage(dead, (this.location / 3) * 764, 72);
				gc.drawImage(RenderableHolder.frame1, (this.location / 3) * 764, 72);
				break;
			case (1):
			case (4):
				gc.drawImage(dead, (this.location / 3) * 764, 72 + 80);
				gc.drawImage(RenderableHolder.frame2, (this.location / 3) * 764, 72 + 80);
				break;
			case (2):
			case (5):
				gc.drawImage(dead, (this.location / 3) * 764, 72 + 160);
				gc.drawImage(RenderableHolder.frame3, (this.location / 3) * 764, 72 + 160);
				break;
			}
			return;
		}
		switch (this.location) {
		case (0):
		case (3):
			gc.drawImage(sprite, (this.location / 3) * 764, 72);
			gc.drawImage(RenderableHolder.frame1, (this.location / 3) * 764, 72);
			break;
		case (1):
		case (4):
			gc.drawImage(sprite, (this.location / 3) * 764, 72 + 80);
			gc.drawImage(RenderableHolder.frame2, (this.location / 3) * 764, 72 + 80);
			break;
		case (2):
		case (5):
			gc.drawImage(sprite, (this.location / 3) * 764, 72 + 160);
			gc.drawImage(RenderableHolder.frame3, (this.location / 3) * 764, 72 + 160);
			break;
		}
		gc.setGlobalAlpha(0.7);
		gc.setFill(Color.RED);
		if (displayedhp < curhp) {
			displayedhp += 40;
			if (displayedhp > curhp) {
				displayedhp = curhp;
			}
		}
		if (displayedhp > curhp) {
			displayedhp -= 40;
			if (displayedhp < curhp) {
				displayedhp = curhp;
			}
		}
		gc.fillRect((this.location / 3) * 764, 72 + (80 * (this.location % 3)),
				(this.hp - this.displayedhp) / (double) (this.hp) * 260.00, 80);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 50));
		gc.setGlobalAlpha(1);
		switch (location) {
		case (0):
		case (3):
			gc.setFill(Color.PINK);
			if (attackingpower > 0) {
				gc.fillText(Integer.toString(attackingpower), (location / 3) * 764, 132);
			}
			break;
		case (1):
		case (4):
			gc.setFill(Color.BLUE);
			if (attackingpower > 0) {
				gc.fillText(Integer.toString(attackingpower), (location / 3) * 764, 132 + 80);
			}
			break;
		case (2):
		case (5):
			gc.setFill(Color.GREEN);
			if (attackingpower > 0) {
				gc.fillText(Integer.toString(attackingpower), (location / 3) * 764, 132 + 160);
			}
			break;
		}
		// System.out.println((this.location / 3) * 764+" "+(80 * (this.location
		// % 3))+" "+(this.hp - Status.hp[this.location]) / (double)(this.hp) *
		// 260.00);
	}

	public int getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean hpmoving() {
		return displayedhp == curhp;
	}

	public void drawdraft(GraphicsContext gc, int position) {
		int isselected = -1;
		int ishovered = -1;
		gc.setFill(Color.WHITE);
		gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 30));
		for (int i = 0; i < 10; i++) {
			if (DraftScreen.pickorder[i] == position) {
				isselected = i;
			}
			if (DraftScreen.hovering == position && ishovered == -1 && DraftScreen.pickorder[i] == -1) {
				ishovered = i;
			}
		}
		gc.setGlobalAlpha(1);
		if (isselected != -1) {
			if (position > 17) {
				gc.drawImage(dead, 288.5 + (position % 18) * 317, 445, 130, 40);
			} else {
				gc.drawImage(dead, 288.5 + (position % 3) * 158.5, 91 + (position / 3) * 59, 130, 40);
			}
			if (isselected == 2 || isselected == 3 || isselected == 6 || isselected == 7) {
				gc.drawImage(dead, 764 * (1 - (isselected % 2)), (isselected / 2) * 80 + 72);
			} else {
				gc.drawImage(sprite, 764 * (isselected % 2), (isselected / 2) * 80 + 72);
			}
		} else {
			if (position > 17) {
				gc.drawImage(sprite, 288.5 + (position % 18) * 317, 445, 130, 40);
			} else {
				gc.drawImage(sprite, 288.5 + (position % 3) * 158.5, 91 + (position / 3) * 59, 130, 40);
			}
			if (ishovered != -1) {
				String sa = skillDescriptionA.replaceAll("\n", " - ");
				String sb = skillDescriptionB.replaceAll("\n", " - ");
				if (ishovered == 2 || ishovered == 3 || ishovered == 6 || ishovered == 7) {
					gc.drawImage(dead, 764 * (1 - (ishovered % 2)), (ishovered / 2) * 80 + 72);
					gc.fillText(name, 15, 511);
					gc.fillText("HP : " + hp + " ATK : " + atk + " REC : " + rec + " SPD : " + spd + " MAG : " + mag,
							315, 511);
					gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 20));
					gc.fillText("Skill A : " + skillNameA + " - " + sa, 15, 535);
					gc.fillText("Skill B : " + skillNameB + " - " + sb, 15, 561);
				} else {
					gc.drawImage(sprite, 764 * (ishovered % 2), (ishovered / 2) * 80 + 72);
					gc.fillText("Name : " + name, 15, 511);
					gc.fillText("HP : " + hp + " ATK : " + atk + " REC : " + rec + " SPD : " + spd + " MAG : " + mag,
							315, 511);
					gc.setFont(Font.font("BigNoodleTooOblique", FontPosture.ITALIC, 20));
					gc.fillText("Skill A : " + skillNameA + " - " + sa, 15, 535);
					gc.fillText("Skill B : " + skillNameB + " - " + sb, 15, 561);
				}
			}
		}
		if (position > 17) {
			gc.drawImage(DraftScreen.draftframe, 288.5 + (position % 18) * 317, 445, 130, 40);
		} else {
			gc.drawImage(DraftScreen.draftframe, 288.5 + (position % 3) * 158.5, 91 + (position / 3) * 59, 130, 40);
		}
	}

	public void setLocation(int location) {
		this.location = location;
	}
}
