/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import java.util.Random;

import battleStatus.GhostlyButterfly;
import battleStatus.MagicPotion;
import drawing.Orb;
import drawing.RenderableHolder;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Yuyuko extends Character {

	public Yuyuko(int location) {
		this.location = location;
		this.name = "Saigyouji Yuyuko";
		this.hp = 16888;// + approx 1500 per +
		this.atk = 141;// + approx 15 per +
		this.rec = 76;// 0 approx 10 per +
		this.spd = 1;// - 1 per +
		this.mag = 4;// + 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Ghostly Butterfly";
		this.skillDescriptionA = "Cost : 0\nChange 5 random orbs to wild orb, can't use in succession.";
		this.skillNameB = "Sense of Cherry Blossom";
		this.skillDescriptionB = "Cost : 2\nChange orb in fixed location to Magic orb";
		this.cutin = new Image(ClassLoader.getSystemResource("Yuyuko_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Yuyuko_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Yuyuko_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			if (statusHolder.get(i) instanceof GhostlyButterfly) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Random rand = new Random();
				int x, y;
				for (int i = 0; i < 5; i++) {
					x = rand.nextInt(9);
					y = rand.nextInt(9);
					Status.orbchangebylocation(x, y, 7);
				}
				addstatus(new GhostlyButterfly());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

	@Override
	public void skillB() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 200) {
			throw new SkillFailException("Not enough mana.");
		}
		boolean haverec = false;
		for (int i = 0; i < 9; i += 2) {
			for (int j = 0; j < 9; j += 2) {
				if (Status.board[i][j].getidentity() == 6) {
					haverec = true;
				}
			}
		}
		if (!haverec) {
			throw new SkillFailException("Orbs in those location are all Magic orb.");
		}
		Status.magic[this.location / 3] -= 200;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				for (int i = 0; i < 9; i += 2) {
					for (int j = 0; j < 9; j += 2) {
						Status.orbchangebylocation(i, j, 6);
					}
				}
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
