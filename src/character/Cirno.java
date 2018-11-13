/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.PerfectFreeze;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Cirno extends Character {

	public Cirno(int location) {
		this.location = location;
		this.name = "Cirno";
		this.hp = 9999;// ---
		this.atk = 159;// ++
		this.rec = 99;// ++
		this.spd = 2;// 0
		this.mag = 4;// +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Perfect Freeze";
		this.skillDescriptionA = "Cost : 3\nFor 1 turn, take -50% damage from attack";
		this.skillNameB = "Icicle Fall";
		this.skillDescriptionB = "Cost : 0\nThis character's opponent recover by 2000. Mana +1.50.";
		this.cutin = new Image(ClassLoader.getSystemResource("Cirno_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Cirno_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Cirno_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 300) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 300;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				addstatus(new PerfectFreeze());
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
		if (Status.character[getOpponent()].getHp() - Status.character[getOpponent()].getCurhp() < 2000) {
			throw new SkillFailException("This character's opponent have too much hp.");
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Status.magInstarefill(location / 3, 150);
				Status.character[getOpponent()].recover(2000);
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
