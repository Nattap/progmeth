/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class Elixir4 extends BattleStatus {

	public Elixir4(int duration) {
		this.name = "Elixir effect 4";
		this.description = "Attack +20%";
		this.duration = duration;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == (location / 3)) {
			Status.character[location].atkmulti(1.2);
		}
		if (Status.phase == 8 && this.duration == 1 && Status.turnOwner == location / 3) {
			Status.character[location].addstatus(new Elixir4(2));
		}
	}

}
