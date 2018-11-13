/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package status;

public class InputAssist {
	public static int startx = -1, starty = -1, currentx = -1, currenty = -1, endx = -1, endy = -1;
	public static boolean turnending = false;
	public static boolean mousedown = false;
	public static int indicateto = -1;
	public static boolean wildselecting = false;
	public static int mousex, mousey;
	public static boolean mouseonscreen = false;

	public static boolean xismoving() {
		if (Math.abs(currentx - startx) > Math.abs(currenty - starty)) {
			return true;
		} else {
			return false;
		}
	}

	public static void reinitialize() {
		startx = -1;
		starty = -1;
		currentx = -1;
		currenty = -1;
		endx = -1;
		endy = -1;
	}

	public static boolean inrange(int minx, int maxx, int miny, int maxy) {
		return InputAssist.mousex < maxx && InputAssist.mousex > minx && InputAssist.mousey < maxy
				&& InputAssist.mousey > miny;
	}

	/*
	 * public static int[] move(){ if(endx == -1){ int[] ans = {-1,-1,-1,-1};
	 * return ans; } else{ int a = startx/56; int b = starty/56; int c =
	 * a+((endx-startx+23)/56); int d = b+((endy-starty+23)/56);
	 * if(Math.abs(endx-startx)>Math.abs(endy-starty)){ d=b; } else{ c=a; }
	 * if(c<0){ c = 9+c; } if(d<0){ d = 9+d; } if(a==c&&b==d){ int[] ans =
	 * {-1,-1,-1,-1}; return ans; } else{ int[] ans = {a,b,c,d}; return ans; } }
	 * }
	 */
}
