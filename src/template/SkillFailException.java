/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package template;

@SuppressWarnings("serial")
public class SkillFailException extends Exception {

	private String description;

	public SkillFailException(String des) {
		this.description = des;
	}

	public String getMessage() {
		return description;
	}
}
