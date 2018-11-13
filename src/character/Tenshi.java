/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.SkyofS;
import battleStatus.SofPleasure;
import battleStatus.Veil;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Tenshi extends Character {

	public Tenshi(int location) {
		this.location = location;
		this.name = "Himawari Tenshi";
		this.hp = 18200;// ++ approx 1500 per +
		this.atk = 108;// - approx 15 per +
		this.rec = 86;// + approx 10 per +
		this.spd = 2;// 0 1 per +
		this.mag = 3;// 0 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Sword of Pleasure";
		this.skillDescriptionA = "Cost : 4\nMana +5.0, once per turn";
		this.skillNameB = "Sky of Scarlet";
		this.skillDescriptionB = "Cost : 0\nRed attack orb have 33% effect of Magic orb but Move-1, can be used once per turn.";
		this.cutin = new Image(ClassLoader.getSystemResource("Tenshi_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Tenshi_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Tenshi_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 400) {
			throw new SkillFailException("Not enough mana.");
		}
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			if (statusHolder.get(i) instanceof SofPleasure) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Status.magic[this.location / 3] -= 400;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Status.magInstarefill(location / 3, 500);
				addstatus(new SofPleasure());
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
			if (statusHolder.get(i) instanceof SkyofS) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				addstatus(new SkyofS());
				Status.moveleft--;
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
