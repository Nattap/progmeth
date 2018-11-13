/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * 
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class Elixir2 extends BattleStatus {

	public Elixir2(int duration) {
		this.name = "Elixir effect 2";
		this.description = "Took -20% damage permanently";
		this.duration = duration;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == 1 - (location / 3)) {
			Status.character[location].defmulti(0.8);
		}
		if (Status.phase == 8 && this.duration == 1 && Status.turnOwner == location / 3) {
			Status.character[location].addstatus(new Elixir2(2));
		}
	}

}
