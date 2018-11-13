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

public class Sakuya extends Character {

	public Sakuya(int location) {
		this.location = location;
		this.name = "Izayoi Sakuya";
		this.hp = 15000;// 0 approx 1500 per +
		this.atk = 125;// 0 approx 15 per +
		this.rec = 75;// 0 approx 10 per +
		this.spd = 4;// ++ 1 per +
		this.mag = 3;// 0 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Luna Dial";
		this.skillDescriptionA = "Cost : 2\nChange all self recovery orb to wild orb";
		this.skillNameB = "Sakuya's World";
		this.skillDescriptionB = "Cost : 3\nDeal 2000 damage to all enemies";
		this.cutin = new Image(ClassLoader.getSystemResource("Sakuya_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Sakuya_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Sakuya_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 200) {
			throw new SkillFailException("Not enough mana.");
		}
		boolean haverec = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Status.board[i][j].getidentity() == 3 + (location % 3)) {
					haverec = true;
				}
			}
		}
		if (!haverec) {
			throw new SkillFailException("No self recovery orb.");
		}
		Status.magic[this.location / 3] -= 200;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Status.orbchange(3 + (location % 3), 7);
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
		if (Status.magic[this.location / 3] < 300) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 300;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				for (int i = 0; i < 3; i++) {
					if (!Status.character[(1 - (location / 3)) * 3 + i].isdead()) {
						Status.character[(1 - (location / 3)) * 3 + i].damaged(2000);
					}
				}
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
