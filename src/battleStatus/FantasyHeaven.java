/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class FantasyHeaven extends BattleStatus {

	public FantasyHeaven() {
		this.name = "Fantasy Heaven";
		this.description = "If all 7 orb types dissolved this turn deal 5000 damage to every enemies";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6) {
			for (int i = 0; i < 8; i++) {
				if (Status.dissolved[i] == 0) {
					return;
				}
				if (i == Status.wildIndicate) {
					if (Status.dissolved[i] - Status.dissolved[7] == 0) {
						return;
					}
				}
			}
			for (int i = 0; i < 3; i++) {
				if (!Status.character[(1 - (location / 3)) * 3 + i].isdead()) {
					Status.character[(1 - (location / 3)) * 3 + i].damaged(7000);
				}
			}
		}
	}

}
