/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.MVampire;
import drawing.RenderableHolder;
import drawing.SkillCutIn;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Remilia extends Character {

	public Remilia(int location) {
		this.location = location;
		this.name = "Remilia Scarlet";
		this.hp = 16666;// +
		this.atk = 166;// ++
		this.rec = 66;// -
		this.spd = 2;// 0
		this.mag = 3;// 0
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Bad Lady Scramble";
		this.skillDescriptionA = "Cost : 5\nChange all recovery orb to red attack orb";
		this.skillNameB = "Millennium Vampire";
		this.skillDescriptionB = "Cost : 1\nAll other orbs gain 50% effect of Magic orb";
		this.cutin = new Image(ClassLoader.getSystemResource("Remilia_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Remilia_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Remilia_port.png").toString());
		// this.powerPlayName = "Seven Stars - Divas";
		// this.powerPlayDescription = "When dissolve 7 of each recovery orbs
		// (not counting wild), deals 300 dmg to all enemies.";
	}

	@Override
	public void skillB() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 100) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 100;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				addstatus(new MVampire());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 500) {
			throw new SkillFailException("Not enough mana.");
		}
		boolean haverec = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Status.board[i][j].getidentity() <= 5 && Status.board[i][j].getidentity() >= 3) {
					haverec = true;
				}
			}
		}
		if (!haverec) {
			throw new SkillFailException("No recovery orb");
		}
		Status.magic[this.location / 3] -= 500;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Status.orbchange(3, 0);
				Status.orbchange(4, 0);
				Status.orbchange(5, 0);
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

	// @Override
	/*
	 * public boolean checkPowerPlay() { return
	 * Status.dissolved[1]>=7&&Status.dissolved[3]>=7&&Status.dissolved[5]>=7; }
	 */

	/*
	 * @Override public void PowerPlay() { if(this.location<3){
	 * Status.hp[3]-=300; Status.hp[4]-=300; Status.hp[5]-=300;
	 * Status.checkdead(); } else{ Status.hp[0]-=300; Status.hp[1]-=300;
	 * Status.hp[2]-=300; Status.checkdead(); } }
	 */

}
