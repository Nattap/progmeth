/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class PSAtk extends BattleStatus {

	public PSAtk() {
		this.name = "Photosynthesis attack incrased";
		this.description = "Attack increased by 10%";
		this.duration = 3;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == (location / 3)) {
			Status.character[location].atkmulti(1.1);
		}
	}

}
