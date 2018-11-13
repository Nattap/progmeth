/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import java.util.Random;

import battleStatus.LeafFan;
import battleStatus.LotusPalm;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Meiling extends Character {

	public Meiling(int location) {
		this.location = location;
		this.name = "Hong Meiling";
		this.hp = 16400;// + approx 1500 per +
		this.atk = 110;// + approx 15 per +
		this.rec = 65;// - approx 10 per +
		this.spd = 3;// + 1 per +
		this.mag = 3;// 0 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Lotus Palm";
		this.skillDescriptionA = "Cost : 3\nThis character's opponent attack -10% for 3 turns, after 3 turns deal 2000 damage.";
		this.skillNameB = "Colorful sign";
		this.skillDescriptionB = "Cost : 5\nReshuffle board, it will not have recovery orb.";
		this.cutin = new Image(ClassLoader.getSystemResource("Meiling_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Meiling_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Meiling_port.png").toString());
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
				Status.character[getOpponent()].addstatus(new LotusPalm());
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
				int to;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++) {
						to = rand.nextInt(5);
						if (to > 2) {
							to += 3;
						}
						Status.orbchangebylocation(i, j, to);
					}
				}
				addstatus(new LeafFan());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
