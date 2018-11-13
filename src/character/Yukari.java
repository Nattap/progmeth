/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import java.util.Random;

import battleStatus.GhostlyButterfly;
import battleStatus.ManiacalRift;
import battleStatus.OldStation;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Yukari extends Character {

	public Yukari(int location) {
		this.location = location;
		this.name = "Yakumo Yukari";
		this.hp = 18000;// ++ approx 1500 per +
		this.atk = 123;// 0 approx 15 per +
		this.rec = 75;// 0 approx 10 per +
		this.spd = 1;// - 1 per +
		this.mag = 4;// + 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Trip to the old station";
		this.skillDescriptionA = "Cost : 5\nDeal 5000 damage to all enemies, can't attack this turn.";
		this.skillNameB = "Maniacal Rift";
		this.skillDescriptionB = "Cost : 0\nChange random orb type to another, can't use in succession.";
		this.cutin = new Image(ClassLoader.getSystemResource("Yukari_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Yukari_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Yukari_port.png").toString());
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
						Status.character[(1 - (location / 3)) * 3 + i].damaged(5000);
					}
				}
				atkmulti(0);
				addstatus(new OldStation());
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
			if (statusHolder.get(i) instanceof ManiacalRift) {
				throw new SkillFailException("Already used this turn.");
			}
		}
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Random rand = new Random();
				int n5f = rand.nextInt(8);
				int n5t = rand.nextInt(8);
				Status.orbchange(n5f, n5t);
				addstatus(new ManiacalRift());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
