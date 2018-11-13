/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.PSAtk;
import battleStatus.PSMana;
import battleStatus.PStone;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Patchouli extends Character {

	public Patchouli(int location) {
		this.location = location;
		this.name = "Patchouli Knowledge";
		this.hp = 13500;// - approx 1500 per +
		this.atk = 110;// - approx 15 per +
		this.rec = 65;// - approx 10 per +
		this.spd = 1;// - 1 per +
		this.mag = 9;// ++++++ 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Photosynthesis";
		this.skillDescriptionA = "Cost : 3\nFor 3 turns, team's attack increased by 10% and regenerate 1.0 Mana every turn";
		this.skillNameB = "Philosopher's Stone";
		this.skillDescriptionB = "Cost : 1\nAttack increased by 3% permanently";
		this.cutin = new Image(ClassLoader.getSystemResource("Patchouli_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Patchouli_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Patchouli_port.png").toString());
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
				for (int j = 0; j < 3; j++) {
					if (!Status.character[((location / 3)) * 3 + j].isdead()) {
						Status.character[((location / 3)) * 3 + j].addstatus(new PSAtk());
					}
				}
				addstatus(new PSMana());
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
				addstatus(new PStone(1));
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
