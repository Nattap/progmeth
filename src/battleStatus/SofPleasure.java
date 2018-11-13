/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import template.BattleStatus;

public class SofPleasure extends BattleStatus {

	public SofPleasure() {
		this.name = "Sword of Pleasure";
		this.description = "Can't use Sword of Pleasure for this turn";
		this.duration = 1;
	}

	@Override
	public void act(int location) {
		// TODO Auto-generated method stub

	}

}
