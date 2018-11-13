/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class MVampire extends BattleStatus {

	public MVampire() {
		this.name = "Millennium Vampire";
		this.description = "All other orbs gain 50% effect of Magic orb";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == location / 3) {
			int other = 0;
			for (int i = 0; i < 8; i++) {
				if (i != 6) {
					other += Status.dissolved[i];
				}
			}
			Status.dissolved[6] += (int) (0.5 * other);
		}
	}

}
