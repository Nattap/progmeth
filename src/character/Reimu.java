/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.FantasyHeaven;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Reimu extends Character {

	public Reimu(int location) {
		this.location = location;
		this.name = "Hakurei Reimu";
		this.hp = 16500;// +
		this.atk = 137;// +
		this.rec = 67;// -
		this.spd = 2;// 0
		this.mag = 4;// +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Fantasy Heaven";
		this.skillDescriptionA = "Cost : 5\nIf all 7 orb types dissolved this turn deal 7000 damage to every enemies";
		this.skillNameB = "Hakurei Amulet";
		this.skillDescriptionB = "Cost : 1\nDeal 1000 damage to this character's opponent";
		this.cutin = new Image(ClassLoader.getSystemResource("Reimu_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Reimu_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Reimu_port.png").toString());
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
				addstatus(new FantasyHeaven());
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
				Status.character[getOpponent()].damaged(1000);
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
