/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.AbyssNova;
import battleStatus.PerfectFreeze;
import drawing.Orb;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Utsuho extends Character {

	public Utsuho(int location) {
		this.location = location;
		this.name = "Reiuji Utsuho";
		this.hp = 16543;// +
		this.atk = 142;// +
		this.rec = 73;// 0
		this.spd = 0;// --
		this.mag = 5;// ++
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Abyss Nova";
		this.skillDescriptionA = "Cost : 5\nAfter 5 turns, deal 8000 damage to all enemies";
		this.skillNameB = "Artificial Sun";
		this.skillDescriptionB = "Cost : ?\nConsume all mana.Change the board to red attacking orb according to mana consumed.";
		this.cutin = new Image(ClassLoader.getSystemResource("Utsuho_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Utsuho_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Utsuho_port.png").toString());
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
				addstatus(new AbyssNova());
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
		int consumed = Status.magic[this.location / 3] / 100;
		Status.magic[this.location / 3] -= consumed * 100;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						if (Math.abs(4 - i) + Math.abs(4 - j) < consumed) {
							Status.orbchangebylocation(i, j, 0);
						}
					}
				}
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
