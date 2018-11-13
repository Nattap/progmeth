/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Suika extends Character {

	public Suika(int location) {
		this.location = location;
		this.name = "Ibuki Suika";
		this.hp = 18500;// ++
		this.atk = 170;// ++
		this.rec = 47;// --
		this.spd = 2;// 0
		this.mag = 3;// 0
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Ibuki Gourd";
		this.skillDescriptionA = "Cost : 1\nTake 3000 damage. Mana increased by 2.0.";
		this.skillNameB = "Pandemonium";
		this.skillDescriptionB = "Cost : 5\nChange all orbs to self attack orb, This turn attack -50%.";
		this.cutin = new Image(ClassLoader.getSystemResource("Suika_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Suika_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Suika_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 100) {
			throw new SkillFailException("Not enough mana.");
		}
		if (curhp <= 3000) {
			throw new SkillFailException("HP is too low.");
		}
		Status.magic[this.location / 3] -= 100;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				this.damaged(3000);
				Status.magInstarefill(Status.turnOwner, 200);
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
		boolean haveother = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (Status.board[i][j].getidentity() != (location % 3)) {
					haveother = true;
				}
			}
		}
		if (!haveother) {
			throw new SkillFailException("All orbs are self attack already.");
		}
		Status.magic[this.location / 3] -= 500;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameB, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Status.orbchange(0, (location % 3));
				Status.orbchange(1, (location % 3));
				Status.orbchange(2, (location % 3));
				Status.orbchange(3, (location % 3));
				Status.orbchange(4, (location % 3));
				Status.orbchange(5, (location % 3));
				Status.orbchange(6, (location % 3));
				Status.orbchange(7, (location % 3));
				atkmulti(0.5);
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
