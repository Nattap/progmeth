/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import java.util.Random;

import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Komachi extends Character {

	public Komachi(int location) {
		this.location = location;
		this.name = "Onozuka Komachi";
		this.hp = 19500;// +++ approx 1500 per +
		this.atk = 96;// -- approx 15 per +
		this.rec = 96;// ++ approx 10 per +
		this.spd = 1;// - 1 per +
		this.mag = 3;// 0 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Last Farewell";
		this.skillDescriptionA = "Cost : 5\nDeal 10000 damage to a random enemy.";
		this.skillNameB = "Death by \"Your Name\"";
		this.skillDescriptionB = "Cost :5\nDeal 600 damage to this character's opponent for each letter it has in its name";
		this.cutin = new Image(ClassLoader.getSystemResource("Komachi_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Komachi_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Komachi_port.png").toString());
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
				Random rand = new Random();
				int a = rand.nextInt(3);
				while (Status.character[(1 - (location / 3)) * 3 + a].isdead()) {
					a = rand.nextInt(3);
				}
				Status.character[(1 - (location / 3)) * 3 + a].damaged(10000);
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
				Random rand = new Random();
				int a = rand.nextInt(3);
				while (Status.character[(1 - (location / 3)) * 3 + a].isdead()) {
					a = rand.nextInt(3);
				}
				Status.character[getOpponent()].damaged(Status.character[getOpponent()].getName().length() * 600);
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
