/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class SacrifiralDoll extends BattleStatus {

	public SacrifiralDoll() {
		this.name = "Sacrifiral Doll";
		this.description = "Took 10% less damage";
		this.duration = 4;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == 1 - (location / 3)) {
			Status.character[location].defmulti(0.9);
		}
	}

}
