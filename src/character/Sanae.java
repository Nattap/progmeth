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

public class Sanae extends Character {

	public Sanae(int location) {
		this.location = location;
		this.name = "Kochiya Sanae";
		this.hp = 10700;// ---
		this.atk = 107;// -
		this.rec = 87;// +
		this.spd = 2;// 0
		this.mag = 8;// +++++
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Omikuji Bomb";
		this.skillDescriptionA = "Cost : 1\nRandom effects";
		this.skillNameB = "Omikuji Barrage";
		this.skillDescriptionB = "Cost : 5\n10 Omikuji Bombs";
		this.cutin = new Image(ClassLoader.getSystemResource("Sanae_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Sanae_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Sanae_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 100) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 100;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				Random rand = new Random();
				int i = rand.nextInt(7);
				switch (i) {
				case (0):
					int n0 = rand.nextInt(101) + 100;
					Status.magInstarefill(location / 3, n0);
					break;
				case (1):
					int n1 = rand.nextInt(1001);
					for (int j = 0; j < 3; j++) {
						if (!Status.character[(1 - (location / 3)) * 3 + j].isdead()) {
							Status.character[(1 - (location / 3)) * 3 + j].damaged(n1);
						}
					}
					break;
				case (2):
					int n2 = rand.nextInt(1001);
					for (int j = 0; j < 3; j++) {
						if (!Status.character[((location / 3)) * 3 + j].isdead()) {
							Status.character[((location / 3)) * 3 + j].recover(n2);
						}
					}
					break;
				case (3):
					int n3 = rand.nextInt(2501);
					int n31 = rand.nextInt(3);
					if (!Status.character[((location / 3)) * 3 + n31].isdead()) {
						Status.character[((location / 3)) * 3 + n31].recover(n3);
					}
					break;
				case (4):
					int n4 = rand.nextInt(2501);
					int n41 = rand.nextInt(3);
					if (!Status.character[(1 - (location / 3)) * 3 + n41].isdead()) {
						Status.character[(1 - (location / 3)) * 3 + n41].damaged(n4);
					}
					break;
				case (5):
					int n5f = rand.nextInt(8);
					int n5t = rand.nextInt(8);
					Status.orbchange(n5f, n5t);
					break;
				case (6):
					break;
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
				int i;
				for (int z = 0; z < 10; z++) {
					i = rand.nextInt(7);
					switch (i) {
					case (0):
						int n0 = rand.nextInt(101) + 100;
						Status.magInstarefill(location / 3, n0);
						break;
					case (1):
						int n1 = rand.nextInt(1001);
						for (int j = 0; j < 3; j++) {
							if (!Status.character[(1 - (location / 3)) * 3 + j].isdead()) {
								Status.character[(1 - (location / 3)) * 3 + j].damaged(n1);
							}
						}
						break;
					case (2):
						int n2 = rand.nextInt(1001);
						for (int j = 0; j < 3; j++) {
							if (!Status.character[((location / 3)) * 3 + j].isdead()) {
								Status.character[((location / 3)) * 3 + j].recover(n2);
							}
						}
						break;
					case (3):
						int n3 = rand.nextInt(2501);
						int n31 = rand.nextInt(3);
						if (!Status.character[((location / 3)) * 3 + n31].isdead()) {
							Status.character[((location / 3)) * 3 + n31].recover(n3);
						}
						break;
					case (4):
						int n4 = rand.nextInt(2501);
						int n41 = rand.nextInt(3);
						if (!Status.character[(1 - (location / 3)) * 3 + n41].isdead()) {
							Status.character[(1 - (location / 3)) * 3 + n41].damaged(n4);
						}
						break;
					case (5):
						int n5f = rand.nextInt(8);
						int n5t = rand.nextInt(8);
						Status.orbchange(n5f, n5t);
						break;
					case (6):
						break;
					}
					Thread.sleep(200);
				}
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
