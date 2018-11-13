/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.MagicPotion;
import battleStatus.Mishaguchi;
import battleStatus.Nap;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Suwako extends Character {

	public Suwako(int location) {
		this.location = location;
		this.name = "Moriya Suwako";
		this.hp = 13300;// - approx 1500 per +
		this.atk = 113;// - approx 15 per +
		this.rec = 100;// ++ approx 10 per +
		this.spd = 3;// + 1 per +
		this.mag = 4;// + 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Frog Nap";
		this.skillDescriptionA = "Cost : 0\nCan't attack, HP +1000 and Mana+0.5 at the end of this turn";
		this.skillNameB = "Mishaguchi";
		this.skillDescriptionB = "Cost : 5\nDeal 2000 damage to all enemies for 3 turns";
		this.cutin = new Image(ClassLoader.getSystemResource("Suwako_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Suwako_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Suwako_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			if (statusHolder.get(i) instanceof Nap) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				addstatus(new Nap());
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
		if (Status.magic[this.location / 3] < 500) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 500;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				for (int i = 0; i < 3; i++) {
					if (!Status.character[(1 - (location / 3)) * 3 + i].isdead()) {
						Status.character[(1 - (location / 3)) * 3 + i].addstatus(new Mishaguchi());
					}
				}
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
