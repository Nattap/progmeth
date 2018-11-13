/* Prog Meth 2110215
 * Name : Tower of Gensokyo
 * Nuttapod Liknapichitkul 5831019221
 * Jakkraraj Chaleowpanya 5831006021
 * Last Edited 14/12/2559
*/
package drawing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;

public class RenderableHolder {
	private static RenderableHolder instance = new RenderableHolder();

	private List<IRenderable> entities;
	private List<IRenderable> tobeadded;
	private Comparator<IRenderable> comparator;
	public static Image orbatk1;
	public static Image orbatk2;
	public static Image orbatk3;
	public static Image orbrec1;
	public static Image orbrec2;
	public static Image orbrec3;
	public static Image orbmag;
	public static Image orbwild;
	public static Image frame1;
	public static Image frame2;
	public static Image frame3;
	public static Image background;
	public static AudioClip menu;
	public static AudioClip draft;
	public static AudioClip game;
	public static AudioClip dissolve;
	public static AudioClip charge;
	public static AudioClip spell;
	public static AudioClip dead;
	public static AudioClip attack;
	static {
		loadResource();
	}

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		tobeadded = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public static RenderableHolder getInstance() {
		return instance;
	}

	public static void loadResource() {
		orbatk1 = new Image(ClassLoader.getSystemResource("Atk1.png").toString());
		orbatk2 = new Image(ClassLoader.getSystemResource("Atk2.png").toString());
		orbatk3 = new Image(ClassLoader.getSystemResource("Atk3.png").toString());
		orbrec1 = new Image(ClassLoader.getSystemResource("Rec1.png").toString());
		orbrec2 = new Image(ClassLoader.getSystemResource("Rec2.png").toString());
		orbrec3 = new Image(ClassLoader.getSystemResource("Rec3.png").toString());
		orbmag = new Image(ClassLoader.getSystemResource("Mag.png").toString());
		orbwild = new Image(ClassLoader.getSystemResource("Wild.png").toString());
		frame1 = new Image(ClassLoader.getSystemResource("Frame1.png").toString());
		frame2 = new Image(ClassLoader.getSystemResource("Frame2.png").toString());
		frame3 = new Image(ClassLoader.getSystemResource("Frame3.png").toString());
		background = new Image(ClassLoader.getSystemResource("bgb.png").toString());
		menu = new AudioClip(ClassLoader.getSystemResource("menu.wav").toString());
		draft = new AudioClip(ClassLoader.getSystemResource("deck.wav").toString());
		game = new AudioClip(ClassLoader.getSystemResource("song.wav").toString());
		dissolve = new AudioClip(ClassLoader.getSystemResource("Dissolve.wav").toString());
		charge = new AudioClip(ClassLoader.getSystemResource("SpellDraw.wav").toString());
		spell = new AudioClip(ClassLoader.getSystemResource("SpellCard.wav").toString());
		dead = new AudioClip(ClassLoader.getSystemResource("Dead.wav").toString());
		attack = new AudioClip(ClassLoader.getSystemResource("Attack.wav").toString());
		menu.setCycleCount(AudioClip.INDEFINITE);
		draft.setCycleCount(AudioClip.INDEFINITE);
		game.setCycleCount(AudioClip.INDEFINITE);
	}

	public void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);

	}

	public void addlater(IRenderable entity) {
		tobeadded.add(entity);
	}

	public void update() {
		while (!tobeadded.isEmpty()) {
			RenderableHolder.getInstance().add(tobeadded.get(0));
			tobeadded.remove(0);
		}
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i).isDestroyed()) {
				entities.remove(i);
			}
		}
	}

	public List<IRenderable> getEntities() {
		return entities;
	}

	public boolean noDamage() {
		for (IRenderable r : entities) {
			if (r instanceof Damage) {
				return false;
			}
		}
		return true;
	}

	public boolean noCutin() {
		for (int i = entities.size() - 1; i >= 0; i--) {
			if (entities.get(i) instanceof SkillCutIn) {
				return false;
			}
		}
		return true;
	}

	public static void reset() {
		instance = new RenderableHolder();
	}
}
