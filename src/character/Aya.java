/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import java.util.Random;

import battleStatus.GhostlyButterfly;
import battleStatus.LeafFan;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Aya extends Character {

	public Aya(int location) {
		this.location = location;
		this.name = "Shameimaru Aya";
		this.hp = 14700;// 0 approx 1500 per +
		this.atk = 130;// 0 approx 15 per +
		this.rec = 68;// 0 approx 10 per +
		this.spd = 5;// +++ 1 per +
		this.mag = 2;// - 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Daymare";
		this.skillDescriptionA = "Cost : 4\nDeal 300 damage to random other character(Including allies) 40 times.";
		this.skillNameB = "Maple Leaf Fan";
		this.skillDescriptionB = "Cost : 0\nReroll the board, once per turn.";
		this.cutin = new Image(ClassLoader.getSystemResource("Aya_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Aya_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Aya_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 400) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 400;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Random rand = new Random();
				int n41;
				for (int i = 0; i < 40; i++) {
					n41 = rand.nextInt(6);
					if (!Status.character[n41].isdead() && n41 != location) {
						Status.character[n41].damaged(300);
						Thread.sleep(100);
					} else {
						i--;
					}
				}
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
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			if (statusHolder.get(i) instanceof LeafFan) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Random rand = new Random();
				int to;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						to = rand.nextInt(8);
						Status.orbchangebylocation(i, j, to);
					}
				}
				addstatus(new LeafFan());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
