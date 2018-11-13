/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class Nap extends BattleStatus {

	public Nap() {
		this.name = "Frog Nap";
		this.description = "Can't attack, HP +1000 and Mana+0.5 at the end of this turn";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == (location / 3)) {
			Status.character[location].atkmulti(0);
		}
		if (Status.phase == 8 && Status.turnOwner == location / 3) {
			Status.character[location].recover(1000);
			Status.magInstarefill(location / 3, 50);
		}
	}

}
