/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class ElixirOverload extends BattleStatus {

	public ElixirOverload(int duration) {
		this.name = "Elixir overload";
		this.description = "Attack -50% and damage taken +50% permanently";
		this.duration = duration;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 6 && Status.turnOwner == (location / 3)) {
			Status.character[location].atkmulti(0.5);
		}
		if (Status.phase == 6 && Status.turnOwner == 1 - (location / 3)) {
			Status.character[location].defmulti(1.5);
		}
		if (Status.phase == 8 && this.duration == 1 && Status.turnOwner == location / 3) {
			Status.character[location].addstatus(new ElixirOverload(2));
		}
	}

}
