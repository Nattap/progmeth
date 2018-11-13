/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class Hakurouken extends BattleStatus {

	public Hakurouken() {
		this.name = "Hakurouken";
		this.description = "all attacking orb have this character's attack effect";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == location / 3) {
			int other = 0;
			for (int i = 0; i < 3; i++) {
				other += Status.dissolved[i];
			}
			Status.dissolved[location % 3] += other - Status.dissolved[location % 3];
			Status.powerupdate();
		}
	}

}
