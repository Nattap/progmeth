/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class PStone extends BattleStatus {

	public PStone(int countdown) {
		this.name = "Philosopher's Stone";
		this.description = "Attack increased by 3% permanently";
		this.duration = countdown;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == (location / 3)) {
			Status.character[location].atkmulti(1.03);
		}
		if (Status.phase == 8 && this.duration == 1 && Status.turnOwner == location / 3) {
			Status.character[location].addstatus(new PStone(2));
		}
	}

}
