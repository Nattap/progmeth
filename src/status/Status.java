/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import drawing.Orb;
import drawing.RenderableHolder;
import template.BattleStatus;
import template.Character;

public class Status {
	public static Character[] character = new Character[6];
	// public static int hp[] = new int[6];
	// public static ArrayList<ArrayList<BattleStatus>> status = new
	// ArrayList<ArrayList<BattleStatus>>(6);
	// public static double atkmultiplier[] = new double[6];
	// public static double recmultiplier[] = new double[6];
	// public static double defmultiplier[] = new double[6];
	// public static int damageDealt[] = new int[6];
	// public static int displayedDamage[] = new int[6];
	// public static int damageReceived[] = new int[6];
	public static int combo[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
	public static int dissolved[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
	public static int phase = -1;
	public static int magic[] = { 0, 0 };
	// public static int skillChar = -1;
	// public static int skillCall = -1;
	public static int wildIndicate = 0;
	public static int breaker[][] = new int[9][9];
	/*
	 * 0 = before skill activation 1 = during main phase 2 = during orb spinning
	 * 3 = orb breaking check 4=orb breaking 5 = orb fall&refill 6 = after
	 * multiply 7 = (attacking) 8 = at the end of turn
	 * 
	 */
	public static Orb board[][] = new Orb[9][9];
	public static int moveleft = 0;
	// public static boolean turnend = false;
	public static int turnOwner = 0;

	/*
	 * public static void checkdone() { if (logicDone && animationDone) {
	 * phaseShift(); } }
	 */

	/*
	 * public static void phaseShift() { phase += 1; if (phase == 6) { turnOwner
	 * = 1 - turnOwner; phase = 0; } }
	 */
	// public static void initBoard() {
	// Arrays.fill(board, 0);
	// }

	/*
	 * public static void boardFill() { Random rand = new Random(); for (int i =
	 * 0; i < 9; i++) { for (int j = 0; j < 9; j++) { if (board[i][j] == 0) {
	 * board[i][j] = rand.nextInt(8) + 1; } } } }
	 */
	public static void boardRelocate() {
		Orb[][] boardassist = new Orb[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				try {
					boardassist[((board[i][j].getX()) - 260) / 56][((board[i][j].getY()) - 72) / 56] = board[i][j];
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(i + " " + j);
				}
			}
		}
		board = boardassist;
	}

	// public static void boardMove(int xbefore, int ybefore, int xafter, int
	// yafter) {
	// Orb[] boardassist = new Orb[9];
	// if (xbefore == xafter) {
	// int ydiff = ybefore - yafter;
	// if (ydiff < 0) {
	// ydiff = ydiff + 9;
	// }
	// for (int i = 0; i < 9; i++) {
	// boardassist[(i + ydiff) % 9] = board[xbefore][i];
	// }
	// for (int i = 0; i < 9; i++) {
	// board[xbefore][i] = boardassist[i];
	// }
	// } else {
	// int xdiff = xbefore - xafter;
	// if (xdiff < 0) {
	// xdiff = xdiff + 9;
	// }
	// for (int i = 0; i < 9; i++) {
	// boardassist[(i + xdiff) % 9] = board[i][ybefore];
	// }
	// for (int i = 0; i < 9; i++) {
	// board[i][ybefore] = boardassist[i];
	// }
	// }
	// }

	public static boolean orbbreak() {
		int i, j, m, n;
		boolean havematch = false;
		int[][] detection = new int[9][9];
		int chkcounter;
		int matchcounter = 0;
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				detection[i][j] = 0;
				breaker[i][j] = 0;
			}
		} // initialize detection
		havematch = false;
		chkcounter = 1;
		for (i = 0; i < 9; i++) {
			for (j = 0; j < 9; j++) {
				if (detection[i][j] == 0) {
					detection[i][j] = chkcounter;
					chkcounter++;
				} // check if not already hit
				if (i != 8) {
					if (board[i][j].getidentity() == board[i + 1][j].getidentity())// check
																					// if
																					// matched
																					// with
					// the next column
					{
						if (detection[i + 1][j] != 0 && detection[i + 1][j] != detection[i][j])
						// already matched with other group
						{
							for (m = 0; m < 9; m++)// go for whole thing for
													// changing detection
							{
								for (n = 0; n < 9; n++) {
									if (detection[m][n] == detection[i + 1][j]) {
										detection[m][n] = detection[i][j];
									}
								}
							}
						} else// new
								// match
						{
							detection[i + 1][j] = detection[i][j];
						}
					}
				}
				if (j != 8) {
					if (board[i][j].getidentity() == board[i][j + 1].getidentity())// check
																					// if
					// matched with
					// // the next
					// row
					{
						if (detection[i][j + 1] != 0 && detection[i][j + 1] != detection[i][j])// already
																								// //
																								// matched
																								// //
																								// with
						// other // group
						{
							for (m = 0; m < 9; m++)// go for whole thing for
													// //changing detection
							{
								for (n = 0; n < 9; n++) {
									if (detection[m][n] == detection[i][j + 1]) {
										detection[m][n] = detection[i][j];
									}
								}
							}
						} else// new match
						{
							detection[i][j + 1] = detection[i][j];
						}
					}
				}
			}
		}
		for (chkcounter = 1; chkcounter < 81; chkcounter++) {
			for (i = 0; i < 9; i++) {
				for (j = 0; j < 9; j++) {
					if (detection[i][j] == chkcounter) {
						matchcounter++;
					}
				}
			}
			if (matchcounter >= 3) {
				boolean comboadded = false;
				havematch = true;
				for (i = 0; i < 9; i++) {
					for (j = 0; j < 9; j++) {
						if (detection[i][j] == chkcounter) {
							// if (!comboadded) {
							// combo[board[i][j].getidentity()]++;
							// comboadded = true;
							// }
							breaker[i][j] = chkcounter;
							// board[i][j].dead();
						}
					}
				}
			}
			matchcounter = 0;
		}
		return havematch;
	}

	public static void orbfall() {
		int m, i, j;
		for (m = 0; m < 9; m++) {
			for (i = 0; i < 9; i++) {
				for (j = 0; j < 8; j++) {
					if (board[i][j + 1] == null) {
						board[i][j + 1] = board[i][j];
						board[i][j] = null;
					}
				}
			}
		}
	}

	// public static void boardrefill() {
	// int i, j, tobefill, n;
	// Orb filler;
	// boolean rerandom = false;
	// Random rand = new Random();
	// for (i = 8; i >= 0; i--) {
	// for (j = 8; j >= 0; j--) {
	// if (board[i][j] == null) {
	// tobefill = rand.nextInt(8);
	// switch (tobefill) {
	// case (0):
	// filler = new Orb(RenderableHolder.orbatk1, 260 + i * 56, 72 + j * 56, 0);
	// break;
	// case (1):
	// filler = new Orb(RenderableHolder.orbatk2, 260 + i * 56, 72 + j * 56, 1);
	// break;
	// case (2):
	// filler = new Orb(RenderableHolder.orbatk3, 260 + i * 56, 72 + j * 56, 2);
	// break;
	// case (3):
	// filler = new Orb(RenderableHolder.orbrec1, 260 + i * 56, 72 + j * 56, 3);
	// break;
	// case (4):
	// filler = new Orb(RenderableHolder.orbrec2, 260 + i * 56, 72 + j * 56, 4);
	// break;
	// case (5):
	// filler = new Orb(RenderableHolder.orbrec3, 260 + i * 56, 72 + j * 56, 5);
	// break;
	// case (6):
	// filler = new Orb(RenderableHolder.orbmag, 260 + i * 56, 72 + j * 56, 6);
	// break;
	// default:
	// filler = new Orb(RenderableHolder.orbwild, 260 + i * 56, 72 + j * 56, 7);
	// break;
	// }
	// for (n = 0; n < 2 && rerandom; n++) {
	// if (i != 8) {
	// if (board[i + 1][j] != null) {
	// if (board[i + 1][j].getidentity() == filler.getidentity()) {
	// rerandom = true;
	// }
	// }
	// }
	// if (j != 8) {
	// if (board[i][j + 1] != null) {
	// if (board[i][j + 1].getidentity() == filler.getidentity()) {
	// rerandom = true;
	// }
	// }
	// }
	// if (j != 0) {
	// if (board[i][j - 1].getidentity() == filler.getidentity()) {
	// rerandom = true;
	// }
	// }
	// if (rerandom) {
	// tobefill = rand.nextInt(8) + 1;
	// switch (tobefill) {
	// case (0):
	// filler = new Orb(RenderableHolder.orbatk1, 260 + i * 56, 72 + j * 56, 0);
	// break;
	// case (1):
	// filler = new Orb(RenderableHolder.orbatk2, 260 + i * 56, 72 + j * 56, 1);
	// break;
	// case (2):
	// filler = new Orb(RenderableHolder.orbatk3, 260 + i * 56, 72 + j * 56, 2);
	// break;
	// case (3):
	// filler = new Orb(RenderableHolder.orbrec1, 260 + i * 56, 72 + j * 56, 3);
	// break;
	// case (4):
	// filler = new Orb(RenderableHolder.orbrec2, 260 + i * 56, 72 + j * 56, 4);
	// break;
	// case (5):
	// filler = new Orb(RenderableHolder.orbrec3, 260 + i * 56, 72 + j * 56, 5);
	// break;
	// case (6):
	// filler = new Orb(RenderableHolder.orbmag, 260 + i * 56, 72 + j * 56, 6);
	// break;
	// default:
	// filler = new Orb(RenderableHolder.orbwild, 260 + i * 56, 72 + j * 56, 7);
	// break;
	// }
	// }
	// }
	// board[i][j] = filler;
	// RenderableHolder.getInstance().add(filler);
	// rerandom = false;
	// }
	// }
	// }
	// }

	public static void refill() {
		int i, j, tobefill, n;
		Orb filler;
		boolean rerandom = false;
		Random rand = new Random();
		for (i = 8; i >= 0; i--) {
			for (j = 8; j >= 0; j--) {
				if (board[i][j] == null) {
					tobefill = rand.nextInt(8);
					switch (tobefill) {
					case (0):
						filler = new Orb(RenderableHolder.orbatk1, 260 + i * 56, 18, 0);
						break;
					case (1):
						filler = new Orb(RenderableHolder.orbatk2, 260 + i * 56, 18, 1);
						break;
					case (2):
						filler = new Orb(RenderableHolder.orbatk3, 260 + i * 56, 18, 2);
						break;
					case (3):
						filler = new Orb(RenderableHolder.orbrec1, 260 + i * 56, 18, 3);
						break;
					case (4):
						filler = new Orb(RenderableHolder.orbrec2, 260 + i * 56, 18, 4);
						break;
					case (5):
						filler = new Orb(RenderableHolder.orbrec3, 260 + i * 56, 18, 5);
						break;
					case (6):
						filler = new Orb(RenderableHolder.orbmag, 260 + i * 56, 18, 6);
						break;
					default:
						filler = new Orb(RenderableHolder.orbwild, 260 + i * 56, 18, 7);
						break;
					}
					for (n = 0; n < 5 && rerandom; n++) {
						if (i != 8) {
							if (board[i + 1][j] != null) {
								if (board[i + 1][j].getidentity() == filler.getidentity()) {
									rerandom = true;
								}
							}
						}
						if (j != 8) {
							if (board[i][j + 1] != null) {
								if (board[i][j + 1].getidentity() == filler.getidentity()) {
									rerandom = true;
								}
							}
						}
						if (j != 0) {
							if (board[i][j - 1].getidentity() == filler.getidentity()) {
								rerandom = true;
							}
						}
						if (rerandom) {
							tobefill = rand.nextInt(8);
							switch (tobefill) {
							case (0):
								filler = new Orb(RenderableHolder.orbatk1, 260 + i * 56, 72 + j * 56, 0);
								break;
							case (1):
								filler = new Orb(RenderableHolder.orbatk2, 260 + i * 56, 72 + j * 56, 1);
								break;
							case (2):
								filler = new Orb(RenderableHolder.orbatk3, 260 + i * 56, 72 + j * 56, 2);
								break;
							case (3):
								filler = new Orb(RenderableHolder.orbrec1, 260 + i * 56, 72 + j * 56, 3);
								break;
							case (4):
								filler = new Orb(RenderableHolder.orbrec2, 260 + i * 56, 72 + j * 56, 4);
								break;
							case (5):
								filler = new Orb(RenderableHolder.orbrec3, 260 + i * 56, 72 + j * 56, 5);
								break;
							case (6):
								filler = new Orb(RenderableHolder.orbmag, 260 + i * 56, 72 + j * 56, 6);
								break;
							default:
								filler = new Orb(RenderableHolder.orbwild, 260 + i * 56, 72 + j * 56, 7);
								break;
							}
						}
					}
					board[i][j] = filler;
					RenderableHolder.getInstance().add(filler);
					rerandom = false;
				}
			}
		}
	}

	public static int totalCombo() {
		return combo[0] + combo[1] + combo[2] + combo[3] + combo[4] + combo[5] + combo[6] + combo[7];
	}

	// public static void setCapHP() {
	// for (int i = 0; i < 6; i++) {
	// if (hp[i] > character[i].getHp()) {
	// hp[i] = character[i].getHp();
	// }
	// }
	// }

	public static int getTotalMag(int owner) {
		return character[owner * 3].getMag() + character[owner * 3 + 1].getMag() + character[owner * 3 + 2].getMag();
	}

	public static int chkWinner() {
		if (character[0].getCurhp() <= 0 && character[1].getCurhp() <= 0 && character[2].getCurhp() <= 0) {
			return 2;
		}
		if (character[3].getCurhp() <= 0 && character[4].getCurhp() <= 0 && character[5].getCurhp() <= 0) {
			return 1;
		}
		return 0;
	}

	public static void boardresnap() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j].setX(260 + i * 56);
				board[i][j].setY(72 + j * 56);
			}
		}
	}

	public static void orbdrop() {
		for (int i = 0; i < 9; i++) {
			for (int j = 8; j >= 0; j--) {
				if (j == 8) {
					if (Status.board[i][j].getY() < 520) {
						Status.board[i][j].setY(Status.board[i][j].getY() + 7);
						if (Status.board[i][j].getY() > 520) {
							Status.board[i][j].setY(520);
						}
						// moved = true;
					}
					// else{
					// System.out.println(i+" "+j+"
					// "+Status.board[i][j].getY());
					// }
				} else {
					if (Status.board[i][j + 1].getY() - Status.board[i][j].getY() > 56) {
						Status.board[i][j].setY(Status.board[i][j].getY() + 7);
						if (Status.board[i][j + 1].getY() - Status.board[i][j].getY() < 56) {
							Status.board[i][j].setY(Status.board[i][j + 1].getY() - 56);
						}
						// moved = true;
					}
				}
			}
		}
	}

	public static void powerupdate() {
		for (int i = 0 + turnOwner * 3; i < 3 + turnOwner * 3; i++) {
			character[i].updatepower();
		}
	}

	public static void killOrb() {
		boolean someonekilled = false;
		// boolean someonedead = false;
		boolean first = false;
		int id = 0;
		for (int b = 1; b < 82; b++) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (breaker[i][j] == b) {
						if (board[i][j].getOpacity() == 100) {
							first = true;
							id = board[i][j].getidentity();
						}
						board[i][j].dying();
						if (board[i][j].isDestroyed()) {
							if (board[i][j].getidentity() != 7) {
								dissolved[board[i][j].getidentity()]++;
							} else {
								dissolved[7]++;
								dissolved[wildIndicate]++;
							}
							board[i][j] = null;
							powerupdate();
							// RenderableHolder.getInstance().getEntities().remove(board[i][j]);
							breaker[i][j] = 0;
						}
						someonekilled = true;
					}
				}
			}
			if (first) {
				RenderableHolder.dissolve.play();
				combo[id]++;
			}
			if (someonekilled) {
				// System.out.println(b);
				break;
			}
		}
	}

	public static boolean allkilled() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (breaker[i][j] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public static boolean alldrop() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getY() != 72 + j * 56) {
					return false;
				}
			}
		}
		return true;
	}

	public static void orbchange(int form, int to) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j].getidentity() == form) {
					board[i][j].instakill();
					switch (to) {
					case (0):
						board[i][j] = new Orb(RenderableHolder.orbatk1, 260 + i * 56, 72 + j * 56, 0);
						break;
					case (1):
						board[i][j] = new Orb(RenderableHolder.orbatk2, 260 + i * 56, 72 + j * 56, 1);
						break;
					case (2):
						board[i][j] = new Orb(RenderableHolder.orbatk3, 260 + i * 56, 72 + j * 56, 2);
						break;
					case (3):
						board[i][j] = new Orb(RenderableHolder.orbrec1, 260 + i * 56, 72 + j * 56, 3);
						break;
					case (4):
						board[i][j] = new Orb(RenderableHolder.orbrec2, 260 + i * 56, 72 + j * 56, 4);
						break;
					case (5):
						board[i][j] = new Orb(RenderableHolder.orbrec3, 260 + i * 56, 72 + j * 56, 5);
						break;
					case (6):
						board[i][j] = new Orb(RenderableHolder.orbmag, 260 + i * 56, 72 + j * 56, 6);
						break;
					default:
						board[i][j] = new Orb(RenderableHolder.orbwild, 260 + i * 56, 72 + j * 56, 7);
						break;
					}
					RenderableHolder.getInstance().addlater(board[i][j]);
				}
			}
		}
	}

	public static void orbchangebylocation(int i, int j, int to) {
		board[i][j].instakill();
		switch (to) {
		case (0):
			board[i][j] = new Orb(RenderableHolder.orbatk1, 260 + i * 56, 72 + j * 56, 0);
			break;
		case (1):
			board[i][j] = new Orb(RenderableHolder.orbatk2, 260 + i * 56, 72 + j * 56, 1);
			break;
		case (2):
			board[i][j] = new Orb(RenderableHolder.orbatk3, 260 + i * 56, 72 + j * 56, 2);
			break;
		case (3):
			board[i][j] = new Orb(RenderableHolder.orbrec1, 260 + i * 56, 72 + j * 56, 3);
			break;
		case (4):
			board[i][j] = new Orb(RenderableHolder.orbrec2, 260 + i * 56, 72 + j * 56, 4);
			break;
		case (5):
			board[i][j] = new Orb(RenderableHolder.orbrec3, 260 + i * 56, 72 + j * 56, 5);
			break;
		case (6):
			board[i][j] = new Orb(RenderableHolder.orbmag, 260 + i * 56, 72 + j * 56, 6);
			break;
		default:
			board[i][j] = new Orb(RenderableHolder.orbwild, 260 + i * 56, 72 + j * 56, 7);
			break;
		}
		RenderableHolder.getInstance().addlater(board[i][j]);
	}

	public static void magInstarefill(int owner, int amount) {
		magic[owner] += amount;
		if (magic[owner] > 500) {
			magic[owner] = 500;
		}
	}

	public static void statuschk() {
		for (int i = 0; i < 6; i++) {
			if (!character[i].isdead()) {
				character[i].statusact();
			}
		}
	}

	public static void reset() {
		character = new Character[6];
		phase = -1;
		Arrays.fill(magic, 0);
		wildIndicate = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != null) {
					board[i][j].instakill();
				}
			}
		}
		board = new Orb[9][9];
	}

}
