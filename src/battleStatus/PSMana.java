/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class PSMana extends BattleStatus {

	public PSMana() {
		this.name = "Photosynthesis mana regeneration";
		this.description = "Mana +1.0 at the end of every turn";
		this.duration = 3;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 8 && Status.turnOwner == location / 3) {
			Status.magInstarefill(location / 3, 100);
		}
	}

}
