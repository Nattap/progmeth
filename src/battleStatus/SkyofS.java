/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class SkyofS extends BattleStatus {

	public SkyofS() {
		this.name = "Sky of Scarlet";
		this.description = "Red attack orb have effect of Magic orb";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == location / 3) {
			Status.dissolved[6] += (int) (Status.dissolved[0] / 3.0);
		}

	}

}
