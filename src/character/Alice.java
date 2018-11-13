/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import java.util.Random;

import battleStatus.SacrifiralDoll;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Alice extends Character {

	public Alice(int location) {
		this.location = location;
		this.name = "Alice Margatroid";
		this.hp = 13500;// - approx 1500 per +
		this.atk = 141;// + approx 15 per +
		this.rec = 85;// + approx 10 per +
		this.spd = 2;// 0 1 per +
		this.mag = 4;// + 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Lemmings' Parade";
		this.skillDescriptionA = "Cost : 5\nDeal 300 damage to random enemy 40 times.";
		this.skillNameB = "Sacrificial Doll";
		this.skillDescriptionB = "Cost : 1\nEntire team took 10% less damage for 3 turns.";
		this.cutin = new Image(ClassLoader.getSystemResource("Alice_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Alice_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Alice_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 500) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 500;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Random rand = new Random();
				int n41;
				for (int i = 0; i < 50; i++) {
					n41 = rand.nextInt(3);
					if (!Status.character[(1 - (location / 3)) * 3 + n41].isdead()) {
						Status.character[(1 - (location / 3)) * 3 + n41].damaged(400);
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
				for (int i = 0; i < 3; i++) {
					if (!Status.character[((location / 3)) * 3 + i].isdead()) {
						Status.character[((location / 3)) * 3 + i].addstatus(new SacrifiralDoll());
					}
				}
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
