/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package character;

import battleStatus.Hakurouken;
import battleStatus.SoEF;
import drawing.SkillCutIn;
import javafx.scene.image.Image;
import status.Status;
import template.Character;
import template.SkillFailException;

public class Youmu extends Character {

	public Youmu(int location) {
		this.location = location;
		this.name = "Konpaku Youmu";
		this.hp = 14600;// 0 approx 1500 per +
		this.atk = 155;// ++ approx 15 per +
		this.rec = 63;// - approx 10 per +
		this.spd = 6;// ++++ 1 per +
		this.mag = 0;// --- 1 per +
		this.curhp = this.hp;
		this.displayedhp = this.hp;
		this.skillNameA = "Hakurouken";
		this.skillDescriptionA = "Cost : 2\nFor 1 turn, all attacking orb have this character's attack effect.";
		this.skillNameB = "Slash of the Eternal Future";
		this.skillDescriptionB = "Cost : 3\nIn this turn, attack x2.";
		this.cutin = new Image(ClassLoader.getSystemResource("Youmu_cutin.png").toString());
		this.dead = new Image(ClassLoader.getSystemResource("Youmu_dead.png").toString());
		this.sprite = new Image(ClassLoader.getSystemResource("Youmu_port.png").toString());
	}

	@Override
	public void skillA() throws SkillFailException {
		if (this.isdead) {
			throw new SkillFailException("This character is dead.");
		}
		if (Status.magic[this.location / 3] < 200) {
			throw new SkillFailException("Not enough mana.");
		}
		Status.magic[this.location / 3] -= 200;
		Thread t = new Thread(() -> {
			try {
				SkillCutIn s = new SkillCutIn(cutin, this.skillNameA, this.location > 2);
				synchronized (s) {
					s.wait();
				}
				addstatus(new Hakurouken());
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
				atkmulti(2);
				addstatus(new SoEF());
			} catch (InterruptedException e) {

			}
		});
		t.start();
	}

}
