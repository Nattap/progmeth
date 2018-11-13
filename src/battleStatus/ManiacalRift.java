/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import template.BattleStatus;

public class ManiacalRift extends BattleStatus {

	public ManiacalRift() {
		this.name = "Maniacal Rift";
		this.description = "Can't use Maniacal Rift for this turn";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		// TODO Auto-generated method stub

	}

}
