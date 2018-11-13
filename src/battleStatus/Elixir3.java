/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package battleStatus;

import status.Status;
import template.BattleStatus;

public class Elixir3 extends BattleStatus {

	public Elixir3(int duration) {
		this.name = "Elixir effect 3";
		this.description = "Regenerate 2000 health every turn";
		this.duration = duration;
	}

	@Override
	public void act(int location) {
		if (Status.phase == 8 && Status.turnOwner == location / 3) {
			Status.character[location].recover(2000);
		}
		if (Status.phase == 8 && this.duration == 1 && Status.turnOwner == location / 3) {
			Status.character[location].addstatus(new Elixir3(2));
		}
	}

}