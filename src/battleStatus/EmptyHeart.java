/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class EmptyHeart extends BattleStatus {

	public EmptyHeart() {
		this.name = "Empty Heart";
		this.description = "Deal 50% less damage";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == (location / 3)) {
			Status.character[location].atkmulti(0.5);
		}
	}

}
