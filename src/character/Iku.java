/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.GhostlyButterfly;
import battleStatus.Stratosphere;
import battleStatus.Veil;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Iku extends Character {

	public Iku(int location) {
		this.location = location;
		this.name = "Nagae Iku";
		this.hp = 13400;// - approx 1500 per +
		this.atk = 109;// - approx 15 per +
		this.rec = 65;// - approx 10 per +
		this.spd = 1;// - 1 per +
		this.mag = 9;// ++++++ 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Stratosphere";
		this.skillDescriptionA = "Cost : 0\nDeal 200 damage to all enemies. Heal all allies by 200, can be used once per turn.";
		this.skillNameB = "Veils Like Sky";
		this.skillDescriptionB = "Cost : 0\nMana+1.0, all allies atk decreased by 10%, can be used once per turn.";
		this.cutin = new Image(ClassLoader.getSystemResource("Iku_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Iku_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Iku_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			if (statusHolder.get(i) instanceof Stratosphere) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				for (int i = 0; i < 3; i++) {
					if (!Status.character[(1 - (location / 3)) * 3 + i].isdead()) {
						Status.character[(1 - (location / 3)) * 3 + i].damaged(200);
					}
					if (!Status.character[((location / 3)) * 3 + i].isdead()) {
						Status.character[((location / 3)) * 3 + i].recover(200);
					}
				}
				addstatus(new Stratosphere());
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
			if (statusHolder.get(i) instanceof Veil) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Status.magInstarefill(location / 3, 100);
				for (int i = 0; i < 3; i++) {
					if (!Status.character[((location / 3)) * 3 + i].isdead()) {
						Status.character[((location / 3)) * 3 + i].addstatus(new Veil());
						Status.character[((location / 3)) * 3 + i].atkmulti(0.9);
					}
				}
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
