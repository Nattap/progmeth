/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package template;

public abstract class BattleStatus {
	protected String name;
	protected String description;
	public int duration;

	public abstract void act(int location);

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
}
