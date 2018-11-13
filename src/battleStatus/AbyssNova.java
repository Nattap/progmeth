/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class AbyssNova extends BattleStatus {

	public AbyssNova() {
		this.name = "Abyss Nova";
		this.description = "When this status's duration depletes deal 10000 damage to all enemies";
		this.duration = 5;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 8 && this.duration == 1 && Status.turnOwner == location / 3) {
			for (int i = 0; i < 3; i++) {
				if (!Status.character[(1 - (location / 3)) * 3 + i].isdead()) {
					Status.character[(1 - (location / 3)) * 3 + i].damaged(8000);
				}
			}
		}
	}

}
