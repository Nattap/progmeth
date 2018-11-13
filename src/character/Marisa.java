/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.MagicPotion;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Marisa extends Character {

	public Marisa(int location) {
		this.location = location;
		this.name = "Kirisame Marisa";
		this.hp = 11500;// -- approx 1500 per +
		this.atk = 155;// ++ approx 15 per +
		this.rec = 50;// -- approx 10 per +
		this.spd = 3;// + 1 per +
		this.mag = 6;// +++ 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Master Spark";
		this.skillDescriptionA = "Cost : 5\nDeal 4000 damage to all enemies";
		this.skillNameB = "Magic Potion";
		this.skillDescriptionB = "Cost : 0\nAt the end of this turn Mana +1.0, can't be used in the same turn.";
		this.cutin = new Image(ClassLoader.getSystemResource("Marisa_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Marisa_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Marisa_port.png").toString());
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
				for (int i = 0; i < 3; i++) {
					if (!Status.character[(1 - (location / 3)) * 3 + i].isdead()) {
						Status.character[(1 - (location / 3)) * 3 + i].damaged(4000);
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
			if (statusHolder.get(i) instanceof MagicPotion) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				addstatus(new MagicPotion());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
