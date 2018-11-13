/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import java.util.Random;

import battleStatus.Elixir1;
import battleStatus.Elixir2;
import battleStatus.Elixir3;
import battleStatus.Elixir4;
import battleStatus.ElixirOverload;
import battleStatus.EmptyHeart;
import battleStatus.GhostlyButterfly;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Reisen extends Character {

	public Reisen(int location) {
		this.location = location;
		this.name = "Reisen Udongein Inaba";
		this.hp = 15200;// 0 approx 1500 per +
		this.atk = 163;// ++ approx 15 per +
		this.rec = 88;// + approx 10 per +
		this.spd = 2;// 0 1 per +
		this.mag = 2;// - 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Life Elixir";
		this.skillDescriptionA = "Cost : 3\nReceive a permanent buff, repetitive usage give more effect but have risk of negative effect.";
		this.skillNameB = "Empty Heart";
		this.skillDescriptionB = "Cost : 3\nThis card's opponent deal 50% less damage next turn.";
		this.cutin = new Image(ClassLoader.getSystemResource("Reisen_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Reisen_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Reisen_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		int elixir = 0;
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 300) {
			throw new SkillFailException("Not enough mana.");
		}
		for (int i = statusHolder.size() - 1; i >= 0; i--) {
			if (statusHolder.get(i) instanceof Elixir1) {
				if (elixir < 1) {
					elixir = 1;
				}
			}
			if (statusHolder.get(i) instanceof Elixir2) {
				if (elixir < 2) {
					elixir = 2;
				}
			}
			if (statusHolder.get(i) instanceof Elixir3) {
				if (elixir < 3) {
					elixir = 3;
				}
			}
			if (statusHolder.get(i) instanceof Elixir4) {
				if (elixir < 4) {
					elixir = 4;
				}
			}
			if (statusHolder.get(i) instanceof ElixirOverload) {
				throw new SkillFailException("You have no elixir left.");
			}
		}
		final int elixirf = elixir;
		Status.magic[this.location / 3] -= 300;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Status.character[getOpponent()].addstatus(new EmptyHeart());
				Random rand = new Random();
				int advancement = rand.nextInt(4) + 1;
				for (int i = 1; i < advancement + 1; i++) {
					if (i + elixirf < 6) {
						switch (i + elixirf) {
						case (1):
							addstatus(new Elixir1(1));
							break;
						case (2):
							addstatus(new Elixir2(1));
							break;
						case (3):
							addstatus(new Elixir3(1));
							break;
						case (4):
							addstatus(new Elixir4(1));
							break;
						case (5):
							addstatus(new ElixirOverload(1));
							break;
						}
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
				Status.character[getOpponent()].addstatus(new EmptyHeart());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
